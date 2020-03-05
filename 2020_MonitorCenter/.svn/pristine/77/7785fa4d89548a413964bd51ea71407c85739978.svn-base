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

public class Sendmessegafromapp extends Thread {

    //send Telegram
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

    //Send SMS
    static final ObjectMapper mapper = new ObjectMapper();

    public static Sendmessegafromapp json2Objec(String str) {
        Sendmessegafromapp result = null;
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            result = mapper.readValue(str, Sendmessegafromapp.class);
            if (result == null) {
                result = new Sendmessegafromapp();
            }
        } catch (Exception e) {
            result = new Sendmessegafromapp();
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
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }

    public static Sendmessegafromapp sendSMS(String urlSend, String jSonString) throws Exception {
        Sendmessegafromapp resultA = new Sendmessegafromapp();
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

    public static Sendmessegafromapp sendtoApp(String urlSend, String jSonString) throws Exception {
        Sendmessegafromapp resultA = new Sendmessegafromapp();
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

    //Send Email 
    static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public static String[] toEmail = {"tuanpla@ahp.vn", "hieuhd@ahp.vn", "ducta@ahp.vn"};

    public static boolean sendMail(String subject, String content, ArrayList<String> toEmail, String fromName) {
        boolean flag = false;
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", MyConfig.MAIL_HOST); //MyContext.MAIL_HOST
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", false);
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            // Get the default Session object.
            Session session = Session.getInstance(props);
            // Create a default MimeMessage object.
            MimeMessage messageSend = new MimeMessage(session);
            // Set the RFC 822 "From" header field using the
            // value of the InternetAddress.getLocalAddress method.
            messageSend.setFrom(new InternetAddress(MyConfig.MAIL_USER, fromName));

            Address[] addresses = new Address[toEmail.size()];
            for (int i = 0; i < toEmail.size(); i++) {
                Address address = new InternetAddress(toEmail.get(i));
                addresses[i] = address;
                // Add the given addresses to the specified recipient type.
                messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.get(i)));
            }
            // Set the "Subject" header field.
            messageSend.setSubject(subject, "utf-8");
            // Sets the given String as this part's content,
            // with a MIME type of "text/plain".
            Multipart mp = new MimeMultipart("alternative");
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            messageSend.setContent(mp);
            messageSend.saveChanges();
            // Send message
            Transport transport = session.getTransport("smtp");
//            transport.connect(MyConfig.MAIL_HOST, MyConfig.MAIL_USER, MyConfig.MAIL_PASS);
            transport.connect(MyConfig.MAIL_USER, MyConfig.MAIL_PASS);
            transport.sendMessage(messageSend, addresses);
            transport.close();
            flag = true;
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
            e.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<String> parseListMail(String input) {
        String[] arrMail = input.split(",|;|\\n|\\s");
        ArrayList<String> list = new ArrayList<>();
        for (String one : arrMail) {
            if (!Tool.checkNull(one) && one.contains("@")) {
                list.add(one.trim());
            }
        }
        return list;
    }

    //Send Voice 
    public static Sendmessegafromapp sendVoice(String urlSend, String jSonString) throws Exception {
        Sendmessegafromapp resultB = new Sendmessegafromapp();
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
            resultB = json2Objec(result);
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultB;
    }

}
