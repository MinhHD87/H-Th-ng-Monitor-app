package vn.htc.monitor.entity.model;

import com.sun.jersey.api.client.ClientResponse;
import vn.htc.monitor.bootstrap.config.ReadProperties;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CHECK;


public class Telegram {

//    private static String token = ReadProperties.readInfo().getProperty("bot");
//    private static String link = "https://api.telegram.org/bot" + token;
    public static void getSystemProperties(String id_group) {
        if (!CHECK.containsKey(id_group)) {
            CHECK.put(id_group, ReadProperties.readInfo().getProperty(id_group));
        }
        if (!CHECK.containsKey("bot")) {
            CHECK.put("bot", ReadProperties.readInfo().getProperty("bot"));
        }
    }

    public static String sendMessage(String id_group, String text) {
        String strJson = null;
        ObjectJson oJson = null;
        String id = null;
        String token = null;
        try {
            Telegram.getSystemProperties(id_group);
            token = CHECK.get("bot");
            id = CHECK.get(id_group);
            String link = "https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + id + "&text=" + text;
            ClientResponse response = ConvertResultWeb.ReturnJson(link);
//            String oJson = response.getEntity(String.class);
            strJson = response.getEntity(String.class);
//            System.out.println("rsJson: " + strJson);
//            URL url = new URL(link);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            oJson = new ObjectJson(conn.getResponseCode(), conn.getResponseMessage());
//            strJson = oJson.toJSONString();
        } catch (Exception ex) {
//            Logger.getLogger(TelegramSender.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return strJson;
    }
}
