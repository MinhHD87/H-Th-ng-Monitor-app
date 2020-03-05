/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import org.apache.log4j.Logger;
import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.app.MonitorApp;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.entity.MoniterStatus;
import vn.htc.monitor.entity.SystemMonitor;

/**
 *
 * @author HTC-PC
 */
public class MonitorThread extends Thread {
    private final Logger logger = Logger.getLogger(LogTransactionBilling.class);

    private boolean stop = false;

    
    public void run() {
        while (MonitorApp.isRuning && !stop) {
            try {
                SystemMonitor queueOne = MonitorApp.QUEUE_MONITOR_STATUS.dequeue();
                SystemMonitor.addnew(queueOne);
                Thread.sleep(1000 / AppStart.TPS_LOG);
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
    }
}
