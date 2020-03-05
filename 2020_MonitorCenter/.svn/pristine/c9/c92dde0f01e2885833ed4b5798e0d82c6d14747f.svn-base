package vn.htc.monitor.service;

import vn.htc.monitor.bootstrap.config.ReadProperties;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CACHE;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CHECK;
import vn.htc.monitor.entity.model.Infomation;
import vn.htc.monitor.entity.model.ObjectJson;
import vn.htc.monitor.entity.model.Telegram;


public class ProcessingMessage {

    public static String securityLogin(String userLogin, String passLogin, String ipLogin, String chatid, String text) {
        String strJson = new ObjectJson(404, "Not Found").toJSONString();
//        System.out.println("CHECK " + CHECK.size());
//        System.out.println("CACKE " + CACHE.size());
        if (!CHECK.containsKey(userLogin)) {
//            System.out.println("chuwa ton tai");
            CACHE = ReadProperties.readConfig(userLogin);
        }
//        System.out.println("CHECK " + CHECK.size());
//        System.out.println("CACHE " + CACHE.size());
//        System.out.println("da ton tai");
        for (Infomation infomation : CACHE) {
//            System.out.println("ipProperties " + infomation.getIp());
//            System.out.println("ip " + infomation.getIp().equals(ipLogin));
//            System.out.println("pass " + infomation.getPassword().equals(passLogin));
//            System.out.println("user " + infomation.getUser().equals(userLogin));
            if (infomation.getIp().equals(ipLogin) && infomation.getUser().equals(userLogin) && infomation.getPassword().equals(passLogin)) {
                strJson = Telegram.sendMessage(chatid, text);
                break;
            } else if (!infomation.getIp().equals(ipLogin) && infomation.getUser().equals(userLogin) && infomation.getPassword().equals(passLogin)) {
                strJson = new ObjectJson(3, "Invalid ip address").toJSONString();
            } else if (infomation.getIp().equals(ipLogin) && !infomation.getUser().equals(userLogin) && infomation.getPassword().equals(passLogin)) {
                strJson = new ObjectJson(1, "Account or password not incorrect").toJSONString();
            } else if (infomation.getIp().equals(ipLogin) && infomation.getUser().equals(userLogin) && !infomation.getPassword().equals(passLogin)) {
                strJson = new ObjectJson(1, "Account or password not incorrect").toJSONString();
            }
        }

//        boolean checkKey = CACHE.containsKey("0:0:0:0:0:0:0:1");
//        if (!checkKey) {
//            strJson = new ObjectJson(3, "Invalid ip address").toJSONString();
//        } else {
//            Infomation test3 = (Infomation) CACHE.get("0:0:0:0:0:0:0:1");
//            Set<Entry<String, Object>> entries = CACHE.entrySet();
//            for (Entry<String, Object> entry : entries) {
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
//        }
//
//        strJson = new ObjectJson(0, "Account does not exist").toJSONString();
        System.out.println("strJson: " + strJson);
        return strJson;
    }
}
