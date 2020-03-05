/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.services;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.htcjsc.brandname.voice.dao.SystemMonitorDaoIF;

import vn.htcjsc.brandname.voice.model.SystemMonitor;

/**
 *
 * @author hoan
 */
@Service("systemMonitorSV")
public class SystemMonitorServiceImpl implements SystemMonitorServiceIF {

    static final Logger logger = Logger.getLogger(SystemMonitorServiceImpl.class);

    @Autowired
   SystemMonitorDaoIF systemmMonitorDao;

    @Override
    public ArrayList<SystemMonitor> listSystemMonitor() {
        return systemmMonitorDao.listSystemMonitor();
    }

    @Override
    public ArrayList<SystemMonitor> view(int page, int maxRow, String name, String ip, int status) {
        return systemmMonitorDao.view(page, maxRow, name, ip, status);
    }

    @Override
    public int count(String key, String ip, String sento, int status) {
       return systemmMonitorDao.count(key, ip, sento, status);
    }

    @Override
    public SystemMonitor findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(SystemMonitor one) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemMonitor update(SystemMonitor one) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemMonitor delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



   
}
