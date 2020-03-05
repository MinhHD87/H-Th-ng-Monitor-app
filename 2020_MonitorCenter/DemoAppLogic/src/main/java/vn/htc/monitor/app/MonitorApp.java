package vn.htc.monitor.app;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CHECK;
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.PoolMng;
import vn.htc.monitor.entity.Account;
import static vn.htc.monitor.entity.Account.CACHE;
import vn.htc.monitor.entity.MoniterStatus;
import vn.htc.monitor.entity.SystemMonitor;
import vn.htc.monitor.entity.TransActionBilling;
import vn.htc.monitor.entity.model.Infomation;
import vn.htc.monitor.primarywork.Queue;
import vn.htc.monitor.service.Handling;
import vn.htc.monitor.service.ServerService;
import vn.htc.monitor.thread.LogTransactionBilling;
import vn.htc.monitor.thread.MonitorThread;
import vn.htc.monitor.thread.SendTelegram;
import vn.htc.monitor.thread.SendVoice;
import vn.htc.monitor.thread.Sendmessega_fromapp;
import vn.htc.monitor.thread.Sendmessegafromapp;
import vn.htc.monitor.thread.Sendsms;
import vn.htc.monitor.thread.SocketCall;
//import vn.htc.monitor.thread.SocketCall;

public class MonitorApp extends Sendmessega_fromapp {

    private static final Logger logger = Logger.getLogger(MonitorApp.class);
    public static boolean isRuning = true;
    public static int TPS_LOG = 100;
    public static int port = 8111;
    public static Queue<SystemMonitor> QUEUE_MONITOR_STATUS = new Queue("QUEUE_MONITOR_STATUS");
    public static Queue<Sendsms> QUEUE_SENDSMS = new Queue("QUEUE_SENDSMS");
    //public static Account accountApp = new Account();

    static {
        try {
            // Log4j
            MyConfig.initLog4j();
            // Load Config
            MyConfig.loadConfig();
            // -- 

            //***********KHOI TAO ConnectionPoolManager**************
            if (!PoolMng.CreatePool()) {
                Tool.out("Khong khoi tao duoc ket noi DB");
                System.exit(1);
            }

            //-----------------------------------------
        } catch (Exception ex) {
            logger.error("Thong so gateway chua du..." + Tool.getLogMessage(ex));
            System.exit(1);
        }
    }

    public static WebServer websever;

    public static void appStop() {
        if (websever != null) {
            websever.stop();
        }

        Account.updateDB4Cache();

    }

    public static void reloadCnf() {
        MyConfig.loadConfig();
    }

    public static void main(String[] args) {
        try {
//            Sendsms sendsms = new Sendsms();
//            sendsms.start();

//            SendVoice sendvoice = new SendVoice();
//            sendvoice.start();
//            SendTelegram tele = new SendTelegram();
//            tele.start();

//            MoniterStatus moniterStatus = new MoniterStatus();
//            ArrayList<MoniterStatus> all = moniterStatus.findAll();
//            
//            for ( int i = 0; i < all.size(); i++) {
//                if(all.get(i).getType() == 0) {
//                    new SocketCall(all.get(i).getName()).start();
//                }
//            }
//            
//            MonitorThread monitorThread = new MonitorThread();
//            monitorThread.start();
//            Webserver
            websever = new WebServer();
            websever.start();
            System.out.println("Ung dung duoc khoi dong tai day");

//Hàm chạy thử gửi sms 
//            MonitorApp http = new MonitorApp();
//            System.out.println("Testing - Send Http POST request");
//            Sendmessegafromapp send = new Sendmessegafromapp();
//            String jsParams = "{\"phone\":\"" + "0374710096" + "\","
//                    + "\"mess\":\"" + "TTC2TT" + "\","
//                    + "\"user\":\"" + "brandhtc" + "\","
//                    + "\"pass\":\"" + "BranHtc7" + "\","
//                    + "\"tranId\":\"" + "TKYTT" + "\","
//                    + "\"brandName\":\"" + "HTC" + "\","
//                    + "\"dataEncode\":\"" + "0" + "\","
//                    + "\"sendTime\":\"" + "" + "\","
//                    + "\"telcoCode\":\"" + "" + "\"}";
//            String jsParams = "{\"user\":\"" + "admin" + "\","
//                    + "\"pass\":\"" + "TTC2TT" + "\","
//                    + "\"type\":\"" + "1" + "\","
//                    + "\"content\":\"" + "TTC2TK" + "\"}";
//            send.sendSMS("http://api.brand1.xyz:8080/service/sms_api", jsParams);
//            System.out.println("++++++INFO SEND ++++++++" + send.sendSMS("http://api.brand1.xyz:8080/service/sms_api", jsParams));
//            send.sendtoApp("http://localhost:9913/monitor/sendinfo", jsParams);
//            System.out.println("RESULT BACK:" + json2Objec(jsParams));
        } catch (Exception e) {
            logger.error("Appstart.main:" + Tool.getLogMessage(e));
        }
    }
}
