/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.socket;

import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import javax.management.MBeanServerConnection;
import vn.htc.monitor.entity.SystemForm;
 
public class ServerProgram {
 
   public static void main(String args[]) throws Exception {
       ServerSocket listener = null;
       System.out.println("Server is waiting to accept user...");
       int clientNumber = 0;
        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
         OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
         mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
         long cpuBefore = osMBean.getProcessCpuTime();
         long nanoBefore = System.nanoTime();
       
       try {
           listener = new ServerSocket(7777);
       } catch (IOException e) {
           System.out.println(e);
           System.exit(1);
       }
        
       try {
           while (true) {
               Socket socketOfServer = listener.accept();
                long cpuAfter = osMBean.getProcessCpuTime();
                long nanoAfter = System.nanoTime();
                ServiceThread serviceThread = new ServiceThread(socketOfServer, clientNumber++, cpuBefore, nanoBefore, cpuAfter, nanoAfter); 
                if(clientNumber%17280 == 0) {
                    clientNumber = 0;
                }
               serviceThread.start();
           }
       } finally {
           listener.close();
       }
       
   }
 
   private static void log(String message) {
       System.out.println(message);
   }
 
   private static class ServiceThread extends Thread {
 
       private int clientNumber;
       private Socket socketOfServer;
       private long nanoBefore;
       private long cpuBefore;
       private long nanoAfter;
       private long cpuAfter;
       private String name;
       private String ip;

        public ServiceThread() {
            
        }
        
        public ServiceThread(Socket socketOfServer, int clientNumber, long cpuBefore, long nanoBefore, long cpuAfter, long nanoAfter) throws Exception {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;
            this.nanoBefore = nanoBefore;
            this.cpuBefore = cpuBefore;
            this.nanoAfter = nanoAfter;
            this.cpuAfter = cpuAfter;
            FileReader reader = new FileReader("../config/dbpool.properties");
 
            // crate properties object
            Properties properties = new Properties();
            properties.load(reader);
            this.name = properties.getProperty("monitor.name");
            this.ip = properties.getProperty("monitor.ip");
            // Log
            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
            
        }
       
        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            // Log
            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
        }
 
       @Override
       public void run() {
 
           try {
               // Mở luồng vào ra trên Socket tại Server.
               BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
               BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
 
               while (true) {
                   String line = is.readLine();
                   if (line != null) {
                        os.write(line);
                        os.newLine();
                        os.flush();
                        if(clientNumber%1 == 0) {                            
                            double memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
                            double memoryFree = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
                            float memory = (float) ((float)(memorySize - memoryFree) / memorySize * 100);
                            SystemForm systemForm1 = new SystemForm(name, ip, 1, (int)memory+"");
                            String message1 = SystemForm.toStringJson(systemForm1);
                            System.out.println("Memory usage: "+(int)memory+"%");
                            
                            os.write(message1);
                            os.newLine();
                            os.flush();
                               
                            long percent;
                            if (nanoAfter > nanoBefore)
                             percent = ((cpuAfter-cpuBefore)*100L)/
                               (nanoAfter-nanoBefore);
                            else percent = 0;
                            SystemForm systemForm3 = new SystemForm(name, ip, 3, percent+"");
                            String message3 = SystemForm.toStringJson(systemForm3);
                            System.out.println("Cpu usage: "+percent+"%");
                            
                            os.write(message3);
                            os.newLine();
                            os.flush();
                        }
                        
                        if(clientNumber%17280 == 0) { 
                            File root = new File("/");
                            double totalSpace = (double)root.getTotalSpace() /1073741824;
                            double freeSpace = (double)root.getFreeSpace() /1073741824;
                            float space = (float) ((float)(totalSpace - freeSpace) / totalSpace * 100);
                            SystemForm systemForm2 = new SystemForm(name, ip, 2, (int)space+"");
                            String message2 = SystemForm.toStringJson(systemForm2);
                            System.out.println("HDD usage: "+(int)space+"%");
                            
                            os.write(message2);
                            os.newLine();
                            os.flush();
                        }
                        
                            os.write("OK");
                            os.newLine();
                            os.flush();
                   }
                   break;
               }
 
           } catch (IOException e) {
               System.out.println(e);
               e.printStackTrace();
           }
       }
   }
}