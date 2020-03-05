package vn.htc.monitor.thread;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import vn.htc.monitor.bootstrap.config.ReadProperties;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CHECK;
import vn.htc.monitor.entity.model.ConvertResultWeb;

public class SendTelegram extends Thread {

    public static void getSystemProperties(String id_group) {
        if (!CHECK.containsKey(id_group)) {
            CHECK.put(id_group, ReadProperties.readInfo().getProperty(id_group));
        }
        if (!CHECK.containsKey("bot")) {
            CHECK.put("bot", ReadProperties.readInfo().getProperty("bot"));
        }
    }

    public static String sendTelegram(String id_group, String text) {
        String strJson = null;
        String id = null;
        String token = null;
        try {
            Sendmessegafromapp.getSystemProperties(id_group);
            token = CHECK.get("bot");
            id = CHECK.get(id_group);
            String link = "https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + id + "&text=" + text;
            ClientResponse response = ConvertResultWeb.ReturnJson(link);
            strJson = response.getEntity(String.class);
        } catch (ClientHandlerException | UniformInterfaceException ex) {
        }
        return strJson;
    }
}
