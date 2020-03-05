package vn.htc.monitor.bootstrap.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import vn.htc.monitor.entity.model.Infomation;


public class ReadProperties {

    public static Map<String, String> CHECK = new HashMap<String, String>();
    public static ArrayList<Infomation> CACHE = new ArrayList();

    public static Properties readInfo() {
        File directory = new File("");
//        String fileInfo = directory.getAbsolutePath() + "\\src\\main\\java\\com\\develperstack\\config\\info.properties";
        String fileInfo = "../config/info.properties";
        FileInputStream input = null;
        Properties prop = null;
        try {
            prop = new Properties();
            input = new FileInputStream(fileInfo);
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static ArrayList<Infomation> readConfig(String user) {
        Infomation info = null;
        Properties prop = ReadProperties.readInfo();
        String pass = prop.getProperty(user + ".pass");
        if (pass != null) {
            CHECK.put(user, user);
            String ip = prop.getProperty(user + ".ip");
            if (ip != null) {
                String[] tach = ip.split(",");
                for (String a : tach) {
//                    System.out.println("a: " + a);
                    info = new Infomation();
                    info.setUser(user);
                    info.setPassword(pass);
                    info.setIp(a);
                    CACHE.add(info);
                }
            }
        }
        return CACHE;
    }

//    public static String securityLogin(String userLogin, String passLogin, String ipLogin, String chatid, String text) {
//        String strJson = null;
//        Map<String, String> chiduong = ReadProperties.getMap(userLogin);
//        if (chiduong.size() > 0) {
//            Set<Entry<String, String>> entries = chiduong.entrySet();
//            for (Entry<String, String> entry : entries) {
//                strJson = new ObjectJson(1, "Account or password not incorrect").toJSONString();
//                if (entry.getValue().equals(passLogin)) {
//                    if (entry.getKey().equals(ipLogin)) {
//                        strJson = Telegram.sendMessage(chatid, text);
//                        break;
//                    } else {
//                        strJson = new ObjectJson(3, "Invalid ip address").toJSONString();
//                    }
//                }
//            }
//        } else {
//            strJson = new ObjectJson(0, "Account does not exist").toJSONString();
//        }
//        System.out.println("strJson: "+strJson);
//        return strJson;
//    }
//    public static Properties readInfo1() {
//        File directory = new File("");
//        String fileName = directory.getAbsolutePath() + "\\src\\main\\java\\com\\develperstack\\config\\info.properties";
//        FileInputStream input = null;
//        Properties prop = null;
//        try {
//            prop = new Properties();
//            input = new FileInputStream(fileName);
//            prop.load(input);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return prop;
//    }
//    public static void main(String[] args) {
//
//        File directory = new File("");
//        String fileName = directory.getAbsolutePath() + "\\src\\main\\java\\com\\develperstack\\config\\info.properties";
//        System.out.println("path: " + directory.getAbsolutePath() + "\\src\\main\\java\\com\\develperstack\\config\\info.properties");
//
//        Properties prop = new Properties();
//        InputStream input = null;
//        try {
//            input = new FileInputStream(fileName);
//            // load a properties file
//            prop.load(input);
////            ArrayList<String> mang = ReadProperties.getArrList("brand");
//            Map<String, String> chiduong = ReadProperties.getMap("brand");
//
//            if (chiduong.isEmpty()) {
//                System.out.println("null roi:D ");
//            } else {
//                Set<Entry<String, String>> entries = chiduong.entrySet();
//
//                for (Entry<String, String> entry : entries) {
////                System.out.println("ip: " + entry.getKey() + " pass: " + entry.getValue());
//                    if (entry.getValue().equals("123456")) {
//                        System.out.println("entry.getValue: " + entry.getValue());
//                        System.out.println("entry.getKey: " + entry.getKey());
//                        System.out.println("mat khau dung");
//                        if (entry.getKey().equals("192.168.10.10")) {
//                            System.out.println("dia chi dung");
//                            break;
//                        } else {
//                            System.out.println("dia chi ko dung");
//                        }
//                    }
//                }
//            }
////            for (String a : mang) {
////                if (a.equals("192.168.0.10")) {
////                    System.out.println("ok");
////                }
////            }
////            System.out.println("fail");
//            // get the property value and print it out
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
