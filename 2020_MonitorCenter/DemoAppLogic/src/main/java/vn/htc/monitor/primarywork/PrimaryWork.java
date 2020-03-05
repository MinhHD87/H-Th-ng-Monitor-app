package vn.htc.monitor.primarywork;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.DateProc;
import vn.htc.monitor.common.MyLog;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.dao.SmsQueueDao;
import org.apache.log4j.Logger;

public class PrimaryWork extends WorkRunnable {

    static final Logger logger = Logger.getLogger(PrimaryWork.class);
    //
    
    private String name;
    static final String SUCCESS_SEND = "1";
    static final String FAIL_SEND = "0";

    public PrimaryWork() {
        this.name = "PrimaryWork [" + DateProc.createTimestamp() + "]";
    }

    public static enum TYPE_SEND {

        QC, CSKH, QC_GROUP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        
    }

}
