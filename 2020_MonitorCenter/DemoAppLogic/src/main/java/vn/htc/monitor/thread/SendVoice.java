package vn.htc.monitor.thread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.DoWork;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.DBPool;
import vn.htc.monitor.entity.MessageMonitor;
import vn.htc.monitor.primarywork.MonitorWorker;
import static vn.htc.monitor.thread.Sendmessegafromapp.json2Objec;

public class SendVoice extends Thread {

    private final Logger logger = Logger.getLogger(SendVoice.class);

    public SendVoice() {
        this.setName("SendVoice" + DateProc.createTimestamp() + "]");
        MonitorWorker.addDemonName(this.getName());
    }

    private boolean stop = false;

    @Override
    public void run() {
        Tool.out("|===> " + this.getName() + " is started...");
        DoWork working = new DoWork();
        double hm = DateProc.getTimer();
        String date = "";
        while (AppStart.isRuning && !stop) {
            try {
                Thread.sleep(10 * 1000);
                long distance = working.done();
                if (distance / 1000 > 900) {
                    Sendmessegafromapp send = new Sendmessegafromapp();
                    String jsParams = "{\"phone\":\"" + "0374710096" + "\","
                            + "\"mess\":\"" + "TTC2TT" + "\","
                            + "\"user\":\"" + "brandhtc" + "\","
                            + "\"pass\":\"" + URLEncoder.encode("BranHtc7", "UTF-8") + "\","
                            + "\"tranId\":\"" + "TKYTT" + "\","
                            + "\"brandName\":\"" + "HTC" + "\","
                            + "\"dataEncode\":\"" + "0" + "\","
                            + "\"sendTime\":\"" + "" + "\","
                            + "\"telcoCode\":\"" + "" + "\"}";
                    System.out.println("++++++INFO SEND ++++++++" + send.sendSMS("http://api.brand1.xyz:8080/service/sms_api", jsParams));
                    System.out.println("RESULT BACK:" + json2Objec(jsParams));
                    working.doneCycle();
                }

                hm = DateProc.getTimer();
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
        MonitorWorker.removeDemonName(this.getName());
    }

    public static boolean log2Databases(MessageMonitor one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO message(NAME,IP,CONTENT,STATUS,CODE,TIMESEND,TYPE,SENDTO) VALUES(?,?,?,?,?,NOW(),?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getName());
            pstm.setString(i++, one.getIp());
            pstm.setString(i++, one.getContent());
            pstm.setInt(i++, one.getStatus());
            pstm.setString(i++, one.getCode());
            pstm.setInt(i++, one.getType());
            pstm.setString(i++, one.getSento());
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }

        } catch (Exception e) {
            System.out.println(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public void shutDown() {
        stop = true;
    }
}
