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
import vn.htc.monitor.entity.TransActionBilling;
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
public class LogMessageMonitor extends Thread {

    private final Logger logger = Logger.getLogger(LogMessageMonitor.class);

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


}
