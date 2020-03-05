/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.dao.ChartDaoIF;
import vn.htcjsc.brandname.voice.model.Monitor;

/**
 *
 * @author Private
 */
@Service("chartService")
@Transactional
public class ChartServiceImpl implements ChartService {

    static final Logger logger = Logger.getLogger(ChartServiceImpl.class);

    @Autowired
    ChartDaoIF chartDao;
    
    @Override
    public ArrayList<Monitor> view(int page, int maxRow, String key, String phone, String email, int status) {
        return chartDao.view(page, maxRow, key, phone, email, status);
    }

    @Override
    public int count(String key, String phone, String email, int status) {
        return chartDao.count(key, phone, email, status);
    }

    @Override
    public Monitor getSysMonitorLogin(HttpSession session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserName(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor getSysMonitorLogin(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateIsdelete(int accID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor checkLoginDB(String user, String pass) {
        return chartDao.checkLoginDB(user, pass);
    }

    @Override
    public Monitor undoDelete(int accID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor deleteForEver(int accID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor findById(int id) {
        return chartDao.findById(id);
    }

    @Override
    public int create(Monitor one) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor update(Monitor one) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Monitor findIdAndTime(int id, Timestamp timesreach) {
        return chartDao.findIdAndTime(id, timesreach);
    }

}
