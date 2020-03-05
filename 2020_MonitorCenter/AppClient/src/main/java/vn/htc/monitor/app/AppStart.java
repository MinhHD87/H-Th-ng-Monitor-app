package vn.htc.monitor.app;

import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.PoolMng;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.entity.TransActionBilling;
import vn.htc.monitor.primarywork.Queue;
import vn.htc.monitor.thread.AlertNotifyBalance_task;
import vn.htc.monitor.thread.LogTransactionBilling;
import vn.htc.monitor.thread.MoniterApp;
import vn.htc.monitor.thread.MonthlyCharg_task;
import vn.htc.monitor.thread.ReloadAble;
import java.util.LinkedList;
import org.apache.log4j.Logger;
//Run via commandline: java -Xmx1024M -classpath "pathTo/Billing_HTC-1.0-bootstrap.jar" vn.htc.monitor.bootstrap.Bootstrap
/**
 * *
 * Lop Main Start xu ly
 *
 * @author PLATUAN
 */
public class AppStart {

    private static final Logger logger = Logger.getLogger(AppStart.class);
    public static boolean isRuning = true;
    public static int TPS_LOG = 100;
    public static Queue<TransActionBilling> QUEUE_TRANSACTION_BILLING = new Queue("QUEUE_TRANSACTION_BILLING");
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
            //Webserver
            websever = new WebServer();
            websever.start();
            Account acc = new Account();//Giu cho vui 
            
            AlertNotifyBalance_task notifyBalance = new AlertNotifyBalance_task();
            notifyBalance.start();
            
            MonthlyCharg_task monthlyCharg = new MonthlyCharg_task();
            monthlyCharg.start();
            
            MoniterApp moa = new MoniterApp();
            moa.start();
            
            ReloadAble reload = new ReloadAble();
            reload.start();
            
            LogTransactionBilling logBilling = new LogTransactionBilling();
            logBilling.start();
                
            
        } catch (Exception e) {
            logger.error("Appstart.main:" + Tool.getLogMessage(e));
        }
    }

}
