package vn.htcjsc.brandname.voice.services;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vn.htcjsc.brandname.voice.dao.ChartDaoIF;
import vn.htcjsc.brandname.voice.model.Monitor;

public interface ChartService extends ChartDaoIF {

    public Monitor getSysMonitorLogin(HttpSession session);

    public String getUserName(HttpServletRequest request);

    public Monitor getSysMonitorLogin(HttpServletRequest request);

    public boolean updateIsdelete(int accID);
    
    public Monitor findIdAndTime(int id,Timestamp timesreach);
}
