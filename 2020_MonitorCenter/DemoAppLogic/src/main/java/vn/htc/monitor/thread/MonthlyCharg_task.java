/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.thread;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.DoWork;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.entity.Account;
import vn.htc.monitor.primarywork.MonitorWorker;
//import static vn.htc.monitor.thread.AlertNotifyBalance_task.logger;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.log4j.Logger;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author @author LienHoa(CongNX)
 */
public class MonthlyCharg_task extends Thread{
    static Logger logger = Logger.getLogger(AlertNotifyBalance_task.class);
    public MonthlyCharg_task() {
        this.setName("MoneyCharg_task [" + DateProc.createTimestamp() + "]");
        MonitorWorker.addDemonName(this.getName());
    }
    
    boolean stop = false;

    public void shutDown() {
        stop = true;
    }
    
    @Override
    public void run() {
        Tool.out("|===> " + this.getName() + " is started...");
        DoWork working = new DoWork();
        MonitorWorker.removeDemonName(this.getName());
        boolean charged=false;
        while (AppStart.isRuning && !stop) {
            try {
                Thread.sleep(1000 * 1000);
                long distance = working.done();
                if (distance / 1000 > 10000) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.get(Calendar.DAY_OF_MONTH);
//                    Date date = new Date();
//                    int hour = date.getHours();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);;//date.getDay();
                    if(day==1 && charged==false){
                        //Thuc hien tru cuoc, thuc hien xong gan charg = true
                        ArrayList<Account> accountList = Account.getAccount4Cache();
                        for(int i=0; i<accountList.size();i++){
                            Account one = accountList.get(i);
                            String usernameOne = one.getAccountName();
                            if(one.getPrepaid()==1){
                                int reCharg = Account.chargMonthly(usernameOne);
                                
                            }
                        }
                        charged=true;
                        
                    }else{
                        //Khong thuc hien tru cuoc va gan charrged=false neu day khac 1
                        if(day!=1){
                            charged=false;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
    }
}
