package vn.htc.monitor.bootstrap.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import vn.htc.monitor.entity.model.Api;

public class ReadApi {

    public static Map<String, String> CHECK = new HashMap<String, String>();
    public static ArrayList<Api> CACHE = new ArrayList();

    public static Properties readInfo() {
        File directory = new File("");
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

    public static ArrayList<Api> readConfig(String user) {
        Api info = null;
        Properties prop = ReadApi.readInfo();
        String pass = prop.getProperty(user + ".pass");
        if (pass != null) {
            CHECK.put(user, user);
            info = new Api();
            info.setUser(user);
            info.setPass(pass);
            CACHE.add(info);
        }
        return CACHE;
    }

}
