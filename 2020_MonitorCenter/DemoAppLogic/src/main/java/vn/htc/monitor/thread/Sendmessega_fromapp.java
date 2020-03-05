package vn.htc.monitor.thread;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.IOUtils;
import vn.htc.monitor.bootstrap.config.ReadProperties;
import static vn.htc.monitor.bootstrap.config.ReadProperties.CHECK;
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.entity.model.ConvertResultWeb;
import static vn.htc.monitor.thread.AlertNotifyBalance_task.logger;

public class Sendmessega_fromapp extends Thread {

    //Send APIinfo
    static final ObjectMapper mapper = new ObjectMapper();

    public static Sendmessega_fromapp json2Objec(String str) {
        Sendmessega_fromapp result = null;
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            result = mapper.readValue(str, Sendmessega_fromapp.class);
            if (result == null) {
                result = new Sendmessega_fromapp();
            }
        } catch (Exception e) {
            result = new Sendmessega_fromapp();
//            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public String toJson() {
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            String jsonInString = mapper.writeValueAsString(this);
            return jsonInString;
        } catch (JsonProcessingException e) {
//            logger.error(Tool.getLogMessage(e));
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }

    public static Sendmessega_fromapp sendtoApp(String urlSend, String jSonString) throws Exception {
        Sendmessega_fromapp resultA = new Sendmessega_fromapp();
        try {
            URL url = new URL(urlSend);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(jSonString.getBytes("UTF-8"));
            os.close();
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            resultA = json2Objec(result);
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultA;
    }

    public Sendmessega_fromapp(String user, String pass, String type, String content) {
        this.user = user;
        this.pass = pass;
        this.type = type;
        this.content = content;
    }

    public Sendmessega_fromapp() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Sendmessega_fromapp{" + "user=" + user + ", pass=" + pass + ", type=" + type + ", content=" + content + '}';
    }

    String user;
    String pass;
    String type;
    String content;

}
