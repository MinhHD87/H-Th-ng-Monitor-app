/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.model.ext.ModelEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import vn.htcjsc.brandname.voice.commons.AbstractConst;
import vn.htcjsc.brandname.voice.commons.DateProc;
import static vn.htcjsc.brandname.voice.config.WebInitializer.context;

/**
 *
 * @author TUANPLA
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
public class MyConfig {

    static Logger logger = Logger.getLogger(MyConfig.class);
    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        System.out.println("Myconfig Auto Start Time:" + DateProc.createTimestamp());
        try {
            //--- INIT CONSTANT
            DE_BUG = getBoolean("voicebrand.main.debug", DE_BUG);
            DEFAULT_LANG = getString("voicebrand.main.defaultLang", DEFAULT_LANG);
            USER_SESSION_NAME = getString("voicebrand.main.usersessionName", USER_SESSION_NAME);
            Tool.out("============> DE_BUG: " + DE_BUG);
            // Load Language
            loadLang(DEFAULT_LANG);
            // Push Constant
            Map<String, Integer> hasMap = new HashMap<>();
            for (MyConfig.STATUS one : MyConfig.STATUS.values()) {
                hasMap.put(one.toString(), one.val);
            }
            context.setAttribute("status", hasMap);
            context.setAttribute("lstatus", MyConfig.getStatus());
            context.setAttribute("ltelco", MyConfig.getTelco());
            context.setAttribute("dProc", new DateProc());
            
            //Đây là phần khởi tạo nhà mạng
//            for (MyConfig.TELCO one : MyConfig.TELCO.values()) {
//                hasMap.put(one.toString(), one.val);
//            }
//            context.setAttribute("telco", hasMap);
//            context.setAttribute("ltelco", MyConfig.getTelco());
//            context.setAttribute("dProc", new DateProc());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean DE_BUG;
    public static String DEFAULT_LANG = "vi";
    public static String USER_SESSION_NAME = "userlogin";
    public static boolean running = false;

    // ----Constants
    public static String SMTP_MAIL;
    public static String SMTP_PASS;
    public static String MAIL_HOST;
    public static String FROM_NAME;
    public static String MAIL_DEBUG;
    public static final int SEND_MAIL_FALSE = 1;
    // General
    public static int MAX_HOT;
    public static int MAX_TOP;
    public static String ARTICLES_IMG_THUMB_ALIAS;
    public static String ARTICLES_IMG_CACHE_ALIAS;
    // Noi cache Anh cua Content va Content
    public static int[] WIDTH_IMAGE_THUMBNAIL;
    public static int WIDTH_IMAGE_IN_CONTENT;
    public static int MAX_ROW_IN_CAT;
    public static final int USER_OR_EMAIL_EXISTS = -7;
    //***********
    //-------Define Exception
    public static int ADMIN_MAX_ROW = 20;
    //--Menu Type
    public static final int MENU_CAT = 0;
    public static final int MENU_LINK = 1;
    // IS DELL
    public static final boolean ISDEL = true;
    //------------STATUS CONTENT------------------------------
    public static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    int getInt(String properties, int defaultVal) {
        try {
            return Integer.parseInt(environment.getProperty(properties, defaultVal + ""));
        } catch (NumberFormatException e) {
            logger.error(e);
            return defaultVal;
        }
    }

    long getLong(String properties, long defaultVal, String categoryName) {
        try {
            return Long.parseLong(environment.getProperty(properties, defaultVal + ""));
        } catch (NumberFormatException e) {
            logger.error(e);
            return defaultVal;
        }
    }

    Double getDouble(String properties, Double defaultVal) {
        try {
            return Double.parseDouble(environment.getProperty(properties, defaultVal + ""));
        } catch (NumberFormatException e) {
            logger.error(e);
            return defaultVal;
        }
    }

    String getString(String properties, String defaultVal) {
        try {
            return environment.getProperty(properties, defaultVal);
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    boolean getBoolean(String properties, boolean defaultVal) {
        try {
            return environment.getProperty(properties, "0").equals("1") || environment.getProperty(properties, "false").equals("true");
        } catch (Exception e) {
            logger.error(e);
            return defaultVal;
        }
    }

    public static enum STATUS {
        ALL(127, AbstractConst.LANG.get("generic.status.all")),
        UNACTIVE(0, AbstractConst.LANG.get("generic.status.unactive")),
        ACTIVE(1, AbstractConst.LANG.get("generic.status.active")),
        LOCK(2, AbstractConst.LANG.get("generic.status.lock")),;
        //--
        public int val;
        public String desc;

        private STATUS(int val, String desc) {
            this.val = val;
            this.desc = desc;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public String getName() {
            return desc;
        }

        public void setName(String name) {
            this.desc = name;
        }
    }
    public static enum TELCO {
        ALL("","Chọn nhà mạng"),
        VTE("VTE", "VIETTEL"),
        GPC("GPC", "VINAPHONE"),
        VMS("VMS", "MOBIPHONE"),
        VNM("VNM", "VIETNAMMOBILE"),
        BL("BL", "GTEL"),
        DDG("DDG", "DONGDUONG"),;
        //--
        public String name;
        public String desc;

        private TELCO(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getVal() {
            return name;
        }

        public void setVal(String name) {
            this.name = name;
        }

        public String getName() {
            return desc;
        }

        public void setName(String name) {
            this.desc = name;
        }
    }
    
    public static ArrayList<ModelEnum> getTelco() {
        ArrayList<ModelEnum> _mytelco = new ArrayList<>();
        for (MyConfig.TELCO one : MyConfig.TELCO.values()) {
            ModelEnum _telco = new ModelEnum(one.name, one.desc);
            _mytelco.add(_telco);
        }
        return _mytelco;
    }
    
//    public static enum TELCO {
//        viettel("VTE", AbstractConst.LANG.get("generic.telco.viettel")),
//        mobifone("VMS", AbstractConst.LANG.get("generic.telco.mobifone")),
//        vietnammobile("VNM", AbstractConst.LANG.get("generic.telco.vietnammobile")),
//        vinaphone("GPC", AbstractConst.LANG.get("generic.telco.vinaphone")),
//        gtel("BL", AbstractConst.LANG.get("generic.telco.gtel")),;
//        //--
//        public String val;
//        public String desc;
//
//        private TELCO(String val, String desc) {
//            this.val = val;
//            this.desc = desc;
//        }
//
//        public String getVal() {
//            return val;
//        }
//
//        public void setVal(String val) {
//            this.val = val;
//        }
//
//        public String getName() {
//            return desc;
//        }
//
//        public void setName(String name) {
//            this.desc = name;
//        }
//    }

    public static ArrayList<ModelEnum> getStatus() {
        ArrayList<ModelEnum> _mystatus = new ArrayList<>();
        for (MyConfig.STATUS one : MyConfig.STATUS.values()) {
            ModelEnum _status = new ModelEnum(one.val, one.desc);
            _mystatus.add(_status);
        }
        return _mystatus;
    }
    
//    public static ArrayList<ModelEnum> getTelco() {
//        ArrayList<ModelEnum> _mytelco = new ArrayList<>();
//        for (MyConfig.TELCO one : MyConfig.TELCO.values()) {
//            ModelEnum _telco = new ModelEnum(one.val, one.desc);
//            _mytelco.add(_telco);
//        }
//        return _mytelco;
//    }

    /*
        You need to know the keys beforehand.
        ${map.key}
        The above gets the value of map.get("key").
        Or if the key contains dots
        ${map['key.with.dots']}
        This gets the value of map.get("key.with.dots").
        Or if it's a dynamic key
        ${map[dynamicKey]}
        This gets the value of map.get(dynamicKey).
     */
    public static void loadLang(String lang) {
        JsonObject json_lang = readLangConf(lang);
//        Set<Map.Entry<String, JsonElement>> entries = json_lang.entrySet();//will return members of your object
        // Lay Ra Key chinh
        Set<String> entries = json_lang.keySet();//will return members of your object
        entries.forEach((mainKey) -> {
            //            System.out.println("mainKey:" + mainKey);
            // Lay ra doi tuong chinh
            JsonElement objVal = json_lang.get(mainKey);
            if (objVal.isJsonObject()) {
                JsonObject one_conf = json_lang.getAsJsonObject(mainKey);
                // Lay ra sub key
                Set<String> sub_entries = one_conf.keySet();
                // Lay ra sub Value
                sub_entries.forEach((subKey) -> {
                    //                System.out.println("subKey:" + subKey);
                    String oneValue = one_conf.get(subKey).getAsString();
//                System.out.println("oneValue:" + oneValue);
//                LANG.put("mainKey", oneValue);
                    AbstractConst.LANG.put(mainKey + "." + subKey, oneValue);
                });
            }
        });
        context.setAttribute("Lang", AbstractConst.LANG);
    }

    public static JsonObject readLangConf(String lang) {
        JsonObject json = null;
        try {
            String path = WebInitializer.rootDir + "/res/lang/" + lang + ".json";
            try (FileInputStream inStream = new FileInputStream(path)) {
                InputStreamReader reader = new InputStreamReader(inStream, "UTF-8");
                Gson gson = new Gson();
                json = gson.fromJson(reader, JsonObject.class);
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
