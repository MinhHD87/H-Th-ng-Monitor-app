/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.primarywork.MonitorWorker;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class UpdateAccInfo extends Thread {

    static Logger logger = Logger.getLogger(UpdateAccInfo.class);

    public UpdateAccInfo() {
        this.setName("Update Account information [" + DateProc.createTimestamp() + "]");
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
                Thread.sleep(5*1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(UpdateAccInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            Account.updateDB4Cache();
        }
    }
}
