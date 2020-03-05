package vn.htc.monitor.service;

import vn.htc.monitor.bootstrap.config.ReadApi;
import static vn.htc.monitor.bootstrap.config.ReadApi.CACHE;
import static vn.htc.monitor.bootstrap.config.ReadApi.CHECK;
import vn.htc.monitor.entity.model.Api;
import vn.htc.monitor.entity.model.ObjectJson;

public class ProcessingAPI {

    public static String securityLogin(String userLogin, String passLogin, String content, String type) {
        String strJson = new ObjectJson(404, "Not Found").toJSONString();
        if (!CHECK.containsKey(userLogin)) {
            CACHE = ReadApi.readConfig(userLogin);
        }
        for (Api api : CACHE) {

            if (api.getUser().equals(userLogin) && api.getPass().equals(passLogin)) {
                strJson = new ObjectJson(1, "Send Api Success").toJSONString();
            } else if (!api.getUser().equals(userLogin) && api.getPass().equals(passLogin)) {
                strJson = new ObjectJson(1, "Account or password not incorrect").toJSONString();
            } else if (api.getUser().equals(userLogin) && !api.getPass().equals(passLogin)) {
                strJson = new ObjectJson(1, "Account or password not incorrect").toJSONString();
            }
        }
        System.out.println("strJson: " + strJson);
        return strJson;
    }
}
