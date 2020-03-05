/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.DoWork;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.primarywork.MonitorWorker;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.log4j.Logger;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 *
 * @author TUANPLA
 */
public class AlertNotifyBalance_task extends Thread {

    static Logger logger = Logger.getLogger(AlertNotifyBalance_task.class);

    public AlertNotifyBalance_task() {
        this.setName("NotifyKios [" + DateProc.createTimestamp() + "]");
        MonitorWorker.addDemonName(this.getName());
    }
    
    boolean stop = false;

    public void shutDown() {
        stop = true;
    }
    
    private long getFileCreationEpoch (File file) {
        return file.lastModified();
    }

    @Override
    public void run() {
        Tool.out("|===> " + this.getName() + " is started...");
        DoWork working = new DoWork();
        double hm = DateProc.getTimer();
        String date = "";
        //shutDown();
        while (AppStart.isRuning && !stop) {
            try {
                

                Thread.sleep(10 * 1000);
                long distance = working.done();
                if (distance / 1000 > 900) {
                    //Check thu muc o day va notify di
//                    String[] listAccountNoti = 
                    String[] listAccountNoti = Account.getUserPrepaidActive();//new String[]{"tiglong", "messbird", "chinasms", "chinaskyline"};
                    boolean notifyHasFile = false;
                    for(int i=0; i<listAccountNoti.length;i++){
                        Account currentAcc = Account.getAccount4Cache(listAccountNoti[i]);
                        
                        if(currentAcc.getBalance()<currentAcc.getAlarm4Db(listAccountNoti[i])){
                            //Thuc hien send mail
                            String accountName = currentAcc.getAccountName();
                            
                            //Notify sang telegram
                            String urlSend = "http://124.158.6.199:8111/message/sendMessage?"
                                + "user=brandname"
                                + "&pass=" + URLEncoder.encode("brandHtc123@", "UTF-8")
                                + "&chatid=BRANDNAME"
                                + "&text=" + URLEncoder.encode("[THÔNG BÁO]: Tài khoản của khách hàng " + accountName.toUpperCase() + " sắp hết tiền với số dư là "+currentAcc.getBalance()+", Nghiệp Vụ, Kinh doanh vui lòng kiểm tra và thông báo cho khách hàng. cảm ơn!", "UTF-8");
                            //desktopco.browse(new URI(urlSend));
                            excuteGET(urlSend);
//                                String sendTele = excuteGET(urlSend);
//                                System.out.println("TELE RESPONSE="+sendTele);
                        }
                    }
                    //Sau khi quet toan bo thu muc, neu co file moi thi push len telegram
//                    if(notifyHasFile){
//                        String urlSend = "http://124.158.6.199:8111/message/sendMessage?"
//                                + "user=zonesmslongcode"
//                                + "&pass=" + URLEncoder.encode("zonesmsHtc123@", "UTF-8")
//                                + "&chatid=ZONESMS"
//                                + "&text=" + URLEncoder.encode("[THÔNG BÁO]: Khách hàng Kios vừa upload file chiến dịch lên, Nghiệp Vụ vui lòng kiểm tra trên hệ thống để xử lí. cảm ơn!", "UTF-8");
//                        //desktopco.browse(new URI(urlSend));
//                        excuteGET(urlSend);
////                        String sendTele = excuteGET(urlSend);
////                        System.out.println("TELE RESPONSE="+sendTele);
////                        sendTele = null;
////                        urlSend = null;
//                    }
                    
                    
                    //Ket thuc vong xu li working nay de vao chu ky moi
                    working.doneCycle();
                }
                
                hm = DateProc.getTimer();
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
        MonitorWorker.removeDemonName(this.getName());
    }
    
    
    private void excuteGET(String urlParameters) {
        try{
            URL url = new URL(urlParameters);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                    System.out.println(inputLine);
            }
            br.close();

            //System.out.println("Done send Telegram");
        }catch(Exception exx){
            exx.printStackTrace();
            System.out.println("Faile send Telegram");
        }
        
    }
}
