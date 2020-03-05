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
import vn.htcjsc.brandname.voice.dao.MessageMonitorDaoIF;

import vn.htcjsc.brandname.voice.model.MessageMonitor;

/**
 *
 * @author hoan
 */
@Service("messageMonitorSV")
public class MessageMonitorServiceImpl implements MessageMonitorServiceIF {

    static final Logger logger = Logger.getLogger(MessageMonitorServiceImpl.class);

    @Autowired
    MessageMonitorDaoIF messageMonitorDao;



    @Override
    public ArrayList<MessageMonitor> listMessageMonitor() {
        return messageMonitorDao.listMessageMonitor();
    }

    @Override
    public ArrayList<MessageMonitor> view(int page, int maxRow, String name, String ip, int status) {
        return messageMonitorDao.view(page, maxRow, name, ip, status);
    }

    @Override
    public MessageMonitor findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(MessageMonitor one) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageMonitor update(MessageMonitor one) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageMonitor delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(String key, String ip, String sento, int status) {
        return messageMonitorDao.count(key, ip, sento, status);
    }

}
