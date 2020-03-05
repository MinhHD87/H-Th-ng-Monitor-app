package vn.htc.monitor.thread;

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
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.Tool;
import static vn.htc.monitor.thread.AlertNotifyBalance_task.logger;

public class SendMail extends Thread{

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

}
