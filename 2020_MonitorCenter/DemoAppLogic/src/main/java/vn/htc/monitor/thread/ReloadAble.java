/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.DoWork;
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.MyLog;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.dao.SmsQueueDao;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.primarywork.MonitorWorker;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class ReloadAble extends Thread {

    static Logger logger = Logger.getLogger(ReloadAble.class);

    public ReloadAble() {
        this.setName("ReloadAble [" + DateProc.createTimestamp() + "]");
        MonitorWorker.addDemonName(this.getName());
    }
    private static boolean REMOVE_LOG = false;
    private static boolean INSERT_KPI_REQ = false;
    private static boolean INSERT_KPI_SUBMIT = false;
    boolean stop = false;

    public void shutDown() {
        stop = true;
    }

    @Override
    public void run() {
        MonitorWorker.removeDemonName(this.getName());
        while(AppStart.isRuning){
            try {
                Thread.sleep(900*1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ReloadAble.class.getName()).log(Level.SEVERE, null, ex);
            }
            Account.reloadCache();
        }
    }
}
