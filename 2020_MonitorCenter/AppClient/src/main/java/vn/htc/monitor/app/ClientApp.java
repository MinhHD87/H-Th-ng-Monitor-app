 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.app;

import com.sun.management.OperatingSystemMXBean;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServerConnection;
import org.apache.log4j.Logger;
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.PoolMng;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.primarywork.Queue;
import vn.htc.monitor.socket.ServerProgram;

/**
 *
 * @author HTC-PC
 */
public class ClientApp {
    private static final Logger logger = Logger.getLogger(ClientApp.class);
    public static boolean isRuning = true;
    public static int TPS_LOG = 100;

//    static {
//        try {
//            // Log4j
//            MyConfig.initLog4j();
//            // Load Config
//            MyConfig.loadConfig();
//            // -- 
//            
//            //***********KHOI TAO ConnectionPoolManager**************
//            if (!PoolMng.CreatePool()) {
//                Tool.out("Khong khoi tao duoc ket noi DB");
//                System.exit(1);
//            }
//            
//            //-----------------------------------------
//        } catch (Exception ex) {
//            logger.error("Thong so gateway chua du..." + Tool.getLogMessage(ex));
//            System.exit(1);
//        }
//    }
    
//    public static WebServer websever;
//    
//    public static void appStop() {
//        if (websever != null) {
//            websever.stop();
//        }
//        Account.updateDB4Cache();
//    }
    
//    public static void reloadCnf() {
//        MyConfig.loadConfig();
//    }
    
    

    public static void main(String[] args) throws IOException, Exception {
                
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        
//        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
//        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
//          method.setAccessible(true);
//          if (method.getName().startsWith("get")
//              && Modifier.isPublic(method.getModifiers())) {
//                  Object value;
//              try {
//                  value = method.invoke(operatingSystemMXBean);
//              } catch (Exception e) {
//                  value = e;
//              } // try
//              System.out.println(method.getName() + " = " + value);
//          } // if
//        } 
//        
//        System.out.println("------------------- Ổ cứng ---------------------");
//        
//        File root = new File("/");
//        System.out.println(String.format("Total space: %.2f GB", 
//        (double)root.getTotalSpace() /1073741824));
//        System.out.println(String.format("Free space: %.2f GB", 
//        (double)root.getFreeSpace() /1073741824));
//        System.out.println(String.format("Usable space: %.2f GB", 
//        (double)root.getUsableSpace() /1073741824));
//        
//        System.out.println("------------ Bộ nhớ heap -------------");
//        
//        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
//        System.out.println(String.format("Initial memory: %.2f GB", 
//          (double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824));
//        System.out.println(String.format("Used heap memory: %.2f GB", 
//          (double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824));
//        System.out.println(String.format("Max heap memory: %.2f GB", 
//          (double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824));
//        System.out.println(String.format("Committed memory: %.2f GB", 
//          (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824));
//        
//        System.out.println("------------ Bộ nhớ heap -------------");
//        
//        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
// 
//        for(Long threadID : threadMXBean.getAllThreadIds()) {
//            ThreadInfo info = threadMXBean.getThreadInfo(threadID);
//            System.out.println("Thread name: " + info.getThreadName());
//            System.out.println("Thread State: " + info.getThreadState());
//            System.out.println(String.format("CPU time: %s ns", 
//              threadMXBean.getThreadCpuTime(threadID)));
//        }
//        
//        System.out.println("dddddddddddddddddddddddddddđ");
//        
//        InetAddress ip;
//        try {
//
//            ip = InetAddress.getLocalHost();
//            System.out.println("Current host name : " + ip.getHostName());
//            System.out.println("Current IP address : " + ip.getHostAddress());
//            String nameOS= System.getProperty("os.name");
//            System.out.println("Operating system Name=>"+ nameOS);
//            String osType= System.getProperty("os.arch");
//            System.out.println("Operating system type =>"+ osType);
//            String osVersion= System.getProperty("os.version");
//            System.out.println("Operating system version =>"+ osVersion);
//
//            System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
//            System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
//            System.out.println(System.getenv("PROCESSOR_ARCHITEW6432"));
//            System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));
//            /* Total number of processors or cores available to the JVM */
//        System.out.println("Available processors (cores): " + 
//            Runtime.getRuntime().availableProcessors());
//
//        /* Total amount of free memory available to the JVM */
//        System.out.println("Free memory (bytes): " + 
//            Runtime.getRuntime().freeMemory());
//
//        /* This will return Long.MAX_VALUE if there is no preset limit */
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        /* Maximum amount of memory the JVM will attempt to use */
//        System.out.println("Maximum memory (bytes): " + 
//            (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
//
//        /* Total memory currently in use by the JVM */
//        System.out.println("Total memory (bytes): " + 
//            Runtime.getRuntime().totalMemory());
//
//
//            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
//
//            byte[] mac = network.getHardwareAddress();
//
//            System.out.print("Current MAC address : ");
//
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < mac.length; i++) {
//                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));     
//            }
//            System.out.println(sb.toString());
//
//        } catch (UnknownHostException e) {
//
//            e.printStackTrace();
//
//        } catch (SocketException e){
//
//            e.printStackTrace();
//
//        }
//        catch (Exception e){
//
//            e.printStackTrace();
//
//        }
//        
//        System.out.println("---------------");
//        
//        double diskSize = new File("/").getTotalSpace();
//        String userName = System.getProperty("user.name");
//        double maxMemory = Runtime.getRuntime().maxMemory();
//        double memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
//        double memoryFree = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
//        double memoryUsed = memorySize-memoryFree;
//        System.out.println("Size of C:="+diskSize/1073741824+" GBytes");
//        System.out.println("User Name="+userName);
//        System.out.println("Max Memory="+maxMemory/1073741824);
//
//        System.out.println("RAM Size="+memorySize/1073741824+" GBytes");
//        System.out.println("RAM used Size="+memoryUsed/1073741824+" GBytes");
//        System.out.println("RAM free Size="+memoryFree/1073741824+" GBytes");
//        
//        System.out.println("---------------");

//        MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
//
//        OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(
//        mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
//
//        long nanoBefore = System.nanoTime();
//        long cpuBefore = osMBean.getProcessCpuTime();
//
//        // Call an expensive task, or sleep if you are monitoring a remote process
//
//        long cpuAfter = osMBean.getProcessCpuTime();
//        long nanoAfter = System.nanoTime();
//
//        long percent;
//        if (nanoAfter > nanoBefore)
//         percent = ((cpuAfter-cpuBefore)*100L)/
//           (nanoAfter-nanoBefore);
//        else percent = 0;
//
//        System.out.println("Cpu usage: "+percent+"%");
        
        ServerProgram serverProgram = new ServerProgram();
        serverProgram.main(args);
    }
}
