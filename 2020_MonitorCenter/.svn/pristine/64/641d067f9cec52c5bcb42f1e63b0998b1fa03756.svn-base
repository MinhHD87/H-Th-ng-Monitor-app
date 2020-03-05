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
import vn.htc.monitor.entity.SystemMonitor;
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
public class LogSystemMonitor extends Thread {

    private final Logger logger = Logger.getLogger(LogSystemMonitor.class);

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
    public static boolean log2Databases(SystemMonitor one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO system(NAME,IP,TIMECHECK,TYPE,STATUS,CONTENT) VALUES(?,?,NOW(),?,?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getName());
            pstm.setString(i++, one.getIp());
            pstm.setInt(i++, one.getType());
            pstm.setInt(i++, one.getStatus());
            pstm.setString(i++, one.getContent());
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
