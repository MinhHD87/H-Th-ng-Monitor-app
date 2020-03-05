/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import vn.htc.monitor.primarywork.Queue;
import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.dao.SmsQueueDao;
import vn.htc.monitor.db.DBPool;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.entity.Monitor;
import vn.htc.monitor.primarywork.MonitorWorker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import vn.htc.monitor.entity.MessageMonitor;

/**
 *
 * @author TUANPLA
 */
public class LogMonitor extends Thread {

    private final Logger logger = Logger.getLogger(LogMonitor.class);

    private boolean stop = false;

//    public void run() {
//        while (AppStart.isRuning && !stop) {
//            try {
//                Message queueOne = AppStart.QUEUE_Message.dequeue();
//                log2Databases(queueOne);
//                Thread.sleep(1000 / AppStart.TPS_LOG);
//            } catch (Exception e) {
//                logger.error(Tool.getLogMessage(e));
//            }
//        }
//    }
    public static boolean log2Databases(Monitor one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO monitor(NAME,DESCRIPTION,IP,POS,USERNAME,PASSWORD,STATUS,TYPE,PARENT,TIMEDOWN,TIMEUP,CHECK_STATUS,PHONE,EMAIL,TELEGRAM,VOICE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getName());
            pstm.setString(i++, one.getDescription());
            pstm.setString(i++, one.getIp());
            pstm.setString(i++, one.getPos());
            pstm.setString(i++, one.getUsername());
            pstm.setString(i++, one.getPassword());
            pstm.setInt(i++, one.getStatus());
            pstm.setInt(i++, one.getType());
            pstm.setInt(i++, one.getParent());
            pstm.setTimestamp(i++, one.getTimedown());
            pstm.setTimestamp(i++, one.getTimeup());
            pstm.setString(i++, one.getCheck_status());
            pstm.setString(i++, one.getPhone());
            pstm.setString(i++, one.getEmail());
            pstm.setString(i++, one.getTelegram());
            pstm.setString(i++, one.getVoice());

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

}
