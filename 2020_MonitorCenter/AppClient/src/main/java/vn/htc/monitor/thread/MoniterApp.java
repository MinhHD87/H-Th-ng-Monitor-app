/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.MyConfig;
import vn.htc.monitor.common.MyLog;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.dao.SmsQueueDao;
import vn.htc.monitor.db.DBPool;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.primarywork.MonitorWorker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class MoniterApp extends Thread {

    final Logger logger = Logger.getLogger(MoniterApp.class);

    public MoniterApp() {
        this.setName("MoniterApp [" + DateProc.createTimestamp() + "]");
        MonitorWorker.addDemonName(this.getName());
    }

    @Override
    public void run() {
        Tool.out("|===> " + this.getName() + " is started...");
        while (true) {
            try {
                InputStreamReader inR = new InputStreamReader(System.in);
                BufferedReader bR = new BufferedReader(inR);
                String input = bR.readLine();
                if (input != null) {
                    if (input.equalsIgnoreCase("reload")) {
                        AppStart.reloadCnf();
                        Tool.out("Reloaded Account Completed....");
                    } else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                        AppStart.appStop();
                    } else if (input.equalsIgnoreCase("show acc")) {
                        Account.showAcc4Cache();
                    }else {
                        Tool.out("-------------\n");
                        Tool.out("An: Q de thoat khoi chuong trinh...");
                        Tool.out("An: [reload] de load lai config file...");
                        Tool.out("An: [show acc] de hien thi thong tin cac tai khoan...");
                    }
                }
            } catch (Exception ex) {
                logger.error(Tool.getLogMessage(ex));
            }
        }
    }

    
}
