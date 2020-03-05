package vn.htc.monitor.common;

import java.io.File;
import org.apache.log4j.BasicConfigurator;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.net.SMTPAppender;
import org.jconfig.Configuration;
import org.jconfig.ConfigurationManager;
import org.jconfig.ConfigurationManagerException;
import org.jconfig.handler.XMLFileHandler;

public class MyConfig {

    static Logger logger = Logger.getLogger(MyConfig.class);
    private static final ConfigurationManager CFM = ConfigurationManager.getInstance();
    public static Configuration config;

    //------------
    public static String PATH_CACHE_LOG_MSG_BR_INCOME;      // Log income DB Fail --> log file
    public static String PATH_CACHE_LOG_MSG_BR_INCOME_ADS;  // Log income ADS DB Fail --> log file
    public static String PATH_CACHE_LOG_MSG_BR_SUBMIT;      // Log submit DB Fail --> log file
    public static String PATH_CACHE_BRAND_SEND;             // Log Queue Send Fail --> log file
    //-- Web Service
    public static String contextPath;
    public static int web_port;
    public static String LB_NODE = "";              // NODE Process
    public static boolean DEBUG = false;
    public static boolean SV_ALERT = false;
    public static boolean NODE_DEV = false;
    public static boolean BUID_CDR = false;
    //--
    public static String REJECT_ACC = "";
    // SMPP CLIENT VAS VIETTEL BULK
    //    public static String VAS_VT_IP;
    //    public static int VAS_VT_PORT;
    //    public static String VAS_VT_USER;
    //    public static String VAS_VT_PASS;
    //--SOUTH BANK
    public static String SOUTH_IP;
    public static int SOUTH_PORT;
    public static String SOUTH_USER;
    public static String SOUTH_PASS;
    
    //--CMC BANKSIM QT
    public static String CMC_IP;
    public static int CMC_PORT;
    public static String CMC_USER;
    public static String CMC_PASS;
    
    // REDIS CACHE
    public static String REDIS_MASTER_IP = "127.0.0.1";
    public static int REDIS_MASTER_PORT = 13679;
    public static String REDIS_MASTER_PASS = "d2feb4794c7dfca3701e6ebf1602efd34b38f0db0f2227cc64df6f1b2592dd24";
// -- MAIL CONFIG ---
    public static String MAIL_USER;
    public static String MAIL_PASS;
    public static String MAIL_DEBUG;
    public static String MAIL_HOST;
    SMTPAppender appender = new SMTPAppender();
    
    //INFOR BANK1 SMS INTERNATIONAL
    public static String banksms1_internation_DEVICE_USER;
    public static String banksms1_internation_DEVICE_PASS;
    public static String banksms1_internation_DEVICE_IP;
    public static int banksms1_internation_DEVICE_PORT;
    public static String banksms1_internation_SIM_PORT;
    public static String banksms1_internation_NATION;
    public static String banksms1_internation_TELCO;
    
    //INFOR BANK1 SMS VIETNAM VTE
    public static String banksms1_vte_DEVICE_USER;
    public static String banksms1_vte_DEVICE_PASS;
    public static String banksms1_vte_DEVICE_IP;
    public static int banksms1_vte_DEVICE_PORT;
    public static String banksms1_vte_SIM_PORT;
    public static String banksms1_vte_NATION;
    public static String banksms1_vte_TELCO;
    
    //INFOR BANK1 SMS VIETNAM GPC
    public static String banksms1_gpc_DEVICE_USER;
    public static String banksms1_gpc_DEVICE_PASS;
    public static String banksms1_gpc_DEVICE_IP;
    public static int banksms1_gpc_DEVICE_PORT;
    public static String banksms1_gpc_SIM_PORT;
    public static String banksms1_gpc_NATION;
    public static String banksms1_gpc_TELCO;

    //INFOR BANK1 SMS VIETNAM VMS
    public static String banksms1_vms_DEVICE_USER;
    public static String banksms1_vms_DEVICE_PASS;
    public static String banksms1_vms_DEVICE_IP;
    public static int banksms1_vms_DEVICE_PORT;
    public static String banksms1_vms_SIM_PORT;
    public static String banksms1_vms_NATION;
    public static String banksms1_vms_TELCO;
    
    //INFOR BANK1 SMS VIETNAM VNM
    public static String banksms1_vnm_DEVICE_USER;
    public static String banksms1_vnm_DEVICE_PASS;
    public static String banksms1_vnm_DEVICE_IP;
    public static int banksms1_vnm_DEVICE_PORT;
    public static String banksms1_vnm_SIM_PORT;
    public static String banksms1_vnm_NATION;
    public static String banksms1_vnm_TELCO;
    
    //INFOR BANK1 SMS VIETNAM BL
    public static String banksms1_bl_DEVICE_USER;
    public static String banksms1_bl_DEVICE_PASS;
    public static String banksms1_bl_DEVICE_IP;
    public static int banksms1_bl_DEVICE_PORT;
    public static String banksms1_bl_SIM_PORT;
    public static String banksms1_bl_NATION;
    public static String banksms1_bl_TELCO;
    
    public static String billing_USERNAME;
    public static String billing_PASSWORD;
    public static String billing_IP;
    
    
    
    // Log Mail ------------->>
    public MyConfig() {
        try {
            appender.setTo("congnx81@gmail.com");
            appender.setFrom("smpp@ahp.vn");
            appender.setSMTPHost("mail.mailbox.com");
            appender.setLocationInfo(true);
            appender.setSubject("Test Mail From Log4J");
            appender.setLayout(new PatternLayout());
            appender.activateOptions();
            logger.addAppender(appender);
            logger.error("Hello World");
        } catch (Exception e) {
            logger.error("Printing ERROR Statements", e);
        }
    }

    public static void loadConfig() {
        File file = new File("../config/config.xml");
        MyLog.logDebug("Config File:" + file.getName());
        XMLFileHandler handler = new XMLFileHandler();
        handler.setFile(file);
        try {
            MyLog.logDebug("trying to load file config");
            CFM.load(handler, "engineConfig");
            MyLog.logDebug("file config successfully processed");
            config = ConfigurationManager.getConfiguration("engineConfig");
            //-- Read MyConfig WebServer
            contextPath = config.getProperty("contextPath", "/", "webService");
            web_port = config.getIntProperty("port", 6688, "webService");
            //--
            MAIL_USER = getString("MAIL_USER", "11alert@ahp.vn", "EMAIL");
            MAIL_PASS = getString("MAIL_PASS", "11ahp@alert.vn", "EMAIL");
            MAIL_HOST = getString("MAIL_HOST", "11mail.ahp.vn", "EMAIL");
            MAIL_DEBUG = getString("MAIL_DEBUG", "false", "EMAIL");
            //----------
            PATH_CACHE_LOG_MSG_BR_INCOME = MyConfig.getString("PATH_CACHE_LOG_MSG_BR_INCOME", "", "appconfig");
            PATH_CACHE_LOG_MSG_BR_INCOME_ADS = MyConfig.getString("PATH_CACHE_LOG_MSG_BR_INCOME_ADS", "", "appconfig");
            PATH_CACHE_LOG_MSG_BR_SUBMIT = MyConfig.getString("PATH_CACHE_LOG_MSG_BR_SUBMIT", "", "appconfig");

            PATH_CACHE_BRAND_SEND = MyConfig.getString("PATH_CACHE_BRAND_SEND", "", "appconfig");
            //-- VAS BULK SMPP
//            VAS_VT_IP = MyConfig.getString("VAS_VT_IP", "", "smppVasViettel");
//            VAS_VT_PORT = MyConfig.getInt("VAS_VT_PORT", 9988, "smppVasViettel");
//            VAS_VT_USER = MyConfig.getString("VAS_VT_USER", "", "smppVasViettel");
//            VAS_VT_PASS = MyConfig.getString("VAS_VT_PASS", "", "smppVasViettel");
            //--South SMPP
            SOUTH_IP = MyConfig.getString("SOUTH_IP", "", "smpp_South");
            SOUTH_PORT = MyConfig.getInt("SOUTH_PORT", 9696, "smpp_South");
            SOUTH_USER = MyConfig.getString("SOUTH_USER", "", "smpp_South");
            SOUTH_PASS = MyConfig.getString("SOUTH_PASS", "", "smpp_South");
            
            //--CMC SMPP Banksim QT
            CMC_IP = MyConfig.getString("CMC_IP", "", "smpp_CMC");
            CMC_PORT = MyConfig.getInt("CMC_PORT", 9696, "smpp_CMC");
            CMC_USER = MyConfig.getString("CMC_USER", "", "smpp_CMC");
            CMC_PASS = MyConfig.getString("CMC_PASS", "", "smpp_CMC");
            
            
            // Redis Cache
            REDIS_MASTER_IP = MyConfig.getString("REDIS_MASTER_IP", "", "redisCache");
            REDIS_MASTER_PORT = MyConfig.getInt("REDIS_MASTER_PORT", 13679, "redisCache");
            REDIS_MASTER_PASS = MyConfig.getString("REDIS_MASTER_PASS", "", "redisCache");
            //--
            LB_NODE = MyConfig.getString("LB_NODE", "NOTEx", "appconfig");
            SV_ALERT = MyConfig.getBoolean("SV_ALERT", false, "appconfig");
            NODE_DEV = MyConfig.getBoolean("NODE_DEV", false, "appconfig");
            BUID_CDR = MyConfig.getBoolean("BUID_CDR", false, "appconfig");
            REJECT_ACC = MyConfig.getString("REJECT_ACC", "", "appconfig");
            //-- SMPP SERVER
//            SMPP_SV_PORT = getInt("SMPP_SV_PORT", 7777, "smppSever");
//            SMPP_SV_PROCESSOR_DEGREE = getInt("SMPP_SV_PROCESSOR_DEGREE", 25, "smppSever");
//            SMPP_SV_MAX_WAIT_BIND = getInt("SMPP_SV_MAX_WAIT_BIND", 10, "smppSever");
        
            //-- SMS BANKS1 INTERNATIONAL
            banksms1_internation_DEVICE_USER = MyConfig.getString("banksms1_internation", "nono", "DEVICE_USER");
            banksms1_internation_DEVICE_PASS = MyConfig.getString("banksms1_internation", "nono", "DEVICE_PASS");
            banksms1_internation_DEVICE_IP = MyConfig.getString("banksms1_internation", "127.0.0.10", "DEVICE_IP");
            banksms1_internation_DEVICE_PORT = MyConfig.getInt("banksms1_internation", 0, "DEVICE_PORT");
            banksms1_internation_SIM_PORT = MyConfig.getString("banksms1_internation", "", "SIM_PORT");
            banksms1_internation_NATION = MyConfig.getString("banksms1_internation", "", "NATION");
            banksms1_internation_TELCO = MyConfig.getString("banksms1_internation", "", "TELCO");
            
            //-- SMS BANKS1 VIETNAM VTE
            banksms1_vte_DEVICE_USER = MyConfig.getString("banksms1_vte", "nono", "DEVICE_USER");
            banksms1_vte_DEVICE_PASS = MyConfig.getString("banksms1_vte", "nono", "DEVICE_PASS");
            banksms1_vte_DEVICE_IP = MyConfig.getString("banksms1_vte", "127.0.0.10", "DEVICE_IP");
            banksms1_vte_DEVICE_PORT = MyConfig.getInt("banksms1_vte", 0, "DEVICE_PORT");
            banksms1_vte_SIM_PORT = MyConfig.getString("banksms1_vte", "", "SIM_PORT");
            banksms1_vte_NATION = MyConfig.getString("banksms1_vte", "", "NATION");
            banksms1_vte_TELCO = MyConfig.getString("banksms1_vte", "", "TELCO");
            
            //-- SMS BANKS1 VIETNAM GPC
            banksms1_gpc_DEVICE_USER = MyConfig.getString("banksms1_gpc", "nono", "DEVICE_USER");
            banksms1_gpc_DEVICE_PASS = MyConfig.getString("banksms1_gpc", "nono", "DEVICE_PASS");
            banksms1_gpc_DEVICE_IP = MyConfig.getString("banksms1_gpc", "127.0.0.10", "DEVICE_IP");
            banksms1_gpc_DEVICE_PORT = MyConfig.getInt("banksms1_gpc", 0, "DEVICE_PORT");
            banksms1_gpc_SIM_PORT = MyConfig.getString("banksms1_gpc", "", "SIM_PORT");
            banksms1_gpc_NATION = MyConfig.getString("banksms1_gpc", "", "NATION");
            banksms1_gpc_TELCO = MyConfig.getString("banksms1_gpc", "", "TELCO");
            
            //-- SMS BANKS1 VIETNAM VMS
            banksms1_vms_DEVICE_USER = MyConfig.getString("banksms1_vms", "nono", "DEVICE_USER");
            banksms1_vms_DEVICE_PASS = MyConfig.getString("banksms1_vms", "nono", "DEVICE_PASS");
            banksms1_vms_DEVICE_IP = MyConfig.getString("banksms1_vms", "127.0.0.10", "DEVICE_IP");
            banksms1_vms_DEVICE_PORT = MyConfig.getInt("banksms1_vms", 0, "DEVICE_PORT");
            banksms1_vms_SIM_PORT = MyConfig.getString("banksms1_vms", "", "SIM_PORT");
            banksms1_vms_NATION = MyConfig.getString("banksms1_vms", "", "NATION");
            banksms1_vms_TELCO = MyConfig.getString("banksms1_vms", "", "TELCO");
            
            //-- SMS BANKS1 VIETNAM VNM
            banksms1_vnm_DEVICE_USER = MyConfig.getString("banksms1_vnm", "nono", "DEVICE_USER");
            banksms1_vnm_DEVICE_PASS = MyConfig.getString("banksms1_vnm", "nono", "DEVICE_PASS");
            banksms1_vnm_DEVICE_IP = MyConfig.getString("banksms1_vnm", "127.0.0.10", "DEVICE_IP");
            banksms1_vnm_DEVICE_PORT = MyConfig.getInt("banksms1_vnm", 0, "DEVICE_PORT");
            banksms1_vnm_SIM_PORT = MyConfig.getString("banksms1_vnm", "", "SIM_PORT");
            banksms1_vnm_NATION = MyConfig.getString("banksms1_vnm", "", "NATION");
            banksms1_vnm_TELCO = MyConfig.getString("banksms1_vnm", "", "TELCO");
            
            //- SMS BANKS1 VIETNAM BL
            banksms1_bl_DEVICE_USER = MyConfig.getString("banksms1_bl", "nono", "DEVICE_USER");
            banksms1_bl_DEVICE_PASS = MyConfig.getString("banksms1_bl", "nono", "DEVICE_PASS");
            banksms1_bl_DEVICE_IP = MyConfig.getString("banksms1_bl", "127.0.0.10", "DEVICE_IP");
            banksms1_bl_DEVICE_PORT = MyConfig.getInt("banksms1_bl", 0, "DEVICE_PORT");
            banksms1_bl_SIM_PORT = MyConfig.getString("banksms1_bl", "", "SIM_PORT");
            banksms1_bl_NATION = MyConfig.getString("banksms1_bl", "", "NATION");
            banksms1_bl_TELCO = MyConfig.getString("banksms1_bl", "", "TELCO");
            
        } catch (ConfigurationManagerException e) {
            logger.error("can not load file config!");
            logger.error(Tool.getLogMessage(e));
            System.exit(0);
        }
    }

    public static void initLog4j() {
        String log4jPath = "../config/log4j.properties";
        //--
        File fileLog4j = new File(log4jPath);
        if (fileLog4j.exists()) {
            Tool.out("====>Initializing Log4j:" + log4jPath);
            PropertyConfigurator.configure(log4jPath);
        } else {
            System.err.println("=====> *** " + log4jPath + " file not found, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        }
    }

    //--------------------------------
    public static int getInt(String properties, int defaultVal, String categoryName) {
        try {
            return Integer.parseInt(config.getProperty(properties, defaultVal + "", categoryName));
        } catch (NumberFormatException e) {
            logger.error(Tool.getLogMessage(e));
            return defaultVal;
        }
    }

    public static boolean getBoolean(String properties, boolean defaultVal, String categoryName) {
        try {
            return Integer.parseInt(config.getProperty(properties, 1 + "", categoryName)) == 1;
        } catch (NumberFormatException e) {
            logger.error(Tool.getLogMessage(e));
            return defaultVal;
        }
    }

    public static long getLong(String properties, long defaultVal, String categoryName) {
        try {
            return Long.parseLong(config.getProperty(properties, defaultVal + "", categoryName));
        } catch (NumberFormatException e) {
            logger.error(Tool.getLogMessage(e));
            return defaultVal;
        }
    }

    public static Double getDouble(String properties, Double defaultVal, String categoryName) {
        try {
            return Double.parseDouble(config.getProperty(properties, defaultVal + "", categoryName));
        } catch (NumberFormatException e) {
            logger.error(Tool.getLogMessage(e));
            return defaultVal;
        }
    }

    public static String getString(String properties, String defaultVal, String categoryName) {
        try {
            String val = config.getProperty(properties, defaultVal, categoryName);
            MyLog.logDebug(properties + ": " + val);
            return val;
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
            return defaultVal;
        }
    }

}
