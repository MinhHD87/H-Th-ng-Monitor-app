/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import vn.htc.monitor.app.MonitorApp;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.entity.MoniterStatus;
import vn.htc.monitor.entity.SystemForm;
import vn.htc.monitor.entity.SystemMonitor;

/**
 *
 * @author HTC-PC
 */
public class SocketCall extends Thread {
    final Logger logger = Logger.getLogger(SocketCall.class);
    private String monitorName;

    public SocketCall() {
        this.monitorName = "NONE";
        this.setName("SocketCall [" + DateProc.createTimestamp() + "]");
    }
    
    public SocketCall(String name) {
        this.monitorName = name;
        this.setName("SocketCall [" + DateProc.createTimestamp() + "]");
    }
    
    @Override
    public void run() {
       // Địa chỉ máy chủ.
       MoniterStatus monitorStatus = new MoniterStatus();
       MoniterStatus mnt = monitorStatus.findByName(this.monitorName);
       String serverHost = mnt.getIp();
//            System.out.println(mnt.toString());
       
       Socket socketOfClient = null;
       BufferedWriter os = null;
       BufferedReader is = null;
 
       try {
           // Gửi yêu cầu kết nối tới Server đang lắng nghe
           // trên máy 'localhost' cổng 7777.
           socketOfClient = new Socket(mnt.getIp(), Tool.getInt(mnt.getPos()));
           // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
           os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
           // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
           is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
       } catch (UnknownHostException e) {
           System.err.println("Don't know about host " + serverHost);
           return;
       } catch (IOException e) {
            //System.err.println("Couldn't get I/O for the connection to " + this.monitorName + " --- " + serverHost);
            boolean aaa = mnt.setMonitorStatus(this.monitorName, "0");
            if(mnt.getTimedown().before(mnt.getTimeup())) {
                mnt.setTimeUpOrDown(this.monitorName, 0);
                mnt.setTimeUpOrDown2(this.monitorName, 0);
            }
            System.out.println(mnt.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(SocketCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.run();
       }
       
 
       try {
            mnt.setMonitorStatus(this.monitorName, "1"); 
            mnt.setSend(false);
            if(mnt.getTimeup().before(mnt.getTimedown())) {
                mnt.setTimeUpOrDown(this.monitorName, 1);
            }
            os.write("1");
            os.newLine();
            os.flush();
               
               String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    if (responseLine.equals("1")) {
                        System.out.println(mnt.toString());
                    } else {
                        if (!responseLine.equals("OK")){
                            SystemForm sf = SystemForm.json2Object(responseLine);
                            SystemMonitor sm = new SystemMonitor(sf.getName(), sf.getIp(), sf.getContent(), 1, sf.getType());
                            MonitorApp.QUEUE_MONITOR_STATUS.enqueue(sm);
                            if (sm.getType() == 1 && Tool.getInt(sm.getContent()) > 80) {
                                System.out.println("Ram tăng cao trên 80%");
                            }
                            if (sm.getType() == 3 && Tool.getInt(sm.getContent()) > 80) {
                                System.out.println("CPU tăng cao trên  80%");
                            }
                            if (sm.getType() == 2 && Tool.getInt(sm.getContent()) > 90) {
                                System.out.println("HDD đã sử dụng trên 80%");
                            }
                        }
                    }
                    
                    if (responseLine.indexOf("OK") != -1) {
                        break;
                    }
                }
            os.close();
            is.close();
            socketOfClient.close();
       } catch (UnknownHostException e) {
           System.err.println("Trying to connect to unknown host: " + e);
       } catch (IOException e) {
           System.err.println("IOException:  " + e);
            boolean aaa = mnt.setMonitorStatus(this.monitorName, "0");
            
               if (mnt.isSend()) {
                    if(mnt.getTimedown().before(mnt.getTimeup())) {
                        mnt.setTimeUpOrDown(this.monitorName, 0);
                        mnt.setTimeUpOrDown2(this.monitorName, 0);
                    }
               }
            System.out.println(mnt.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(SocketCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.run();
       }
       
       
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.run();
    }
}
