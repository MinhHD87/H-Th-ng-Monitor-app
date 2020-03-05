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

/**
 *
 * @author TUANPLA
 */
public class LogTransactionBilling  extends Thread{

    private final Logger logger = Logger.getLogger(LogTransactionBilling.class);

    private boolean stop = false;

    
    public void run() {
        while (AppStart.isRuning && !stop) {
            try {
                TransActionBilling queueOne = AppStart.QUEUE_TRANSACTION_BILLING.dequeue();
                log2Databases(queueOne);
                Thread.sleep(1000 / AppStart.TPS_LOG);
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
    }
    
    private static boolean log2Databases(TransActionBilling one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO transaction_billing(BILLING_TRANS,TYPE_TRANS,MONEY_TRANS,ACCOUNT_NAME,ACTION_ACCOUNT,UNIT_PRICE,NUMBER_UNIT,TELCO,TIME_ACTION,BALANCE_TRANS,BALANCE_BEFORE,COMMENT_NOTE) VALUES(?,?,?,?,?,?,?,?,NOW(),?,?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getBilling_trans());
            pstm.setString(i++, one.getType_trans());
            pstm.setInt(i++, one.getMoney_trans());
            pstm.setString(i++, one.getAccount_name());
            pstm.setString(i++, one.getAction_account());
            pstm.setInt(i++, one.getUnit_price());
            pstm.setInt(i++, one.getNumber_unit());
            pstm.setString(i++, one.getTelco());
            pstm.setInt(i++, one.getBalance_trans());
            pstm.setInt(i++, one.getBalance_before());
            pstm.setString(i++, one.getComment_note());
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
