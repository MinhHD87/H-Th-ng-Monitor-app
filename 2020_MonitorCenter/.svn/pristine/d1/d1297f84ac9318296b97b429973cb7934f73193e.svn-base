package vn.htcjsc.brandname.voice.services;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.htcjsc.brandname.voice.dao.MonitorDaoIF;
import vn.htcjsc.brandname.voice.model.Monitor;

/**
 *
 * @author Private
 */
@Service("monService")
@Transactional
public class MonitorServiceImpl implements MonitorService {

    static final Logger logger = Logger.getLogger(MonitorServiceImpl.class);

    @Autowired
    MonitorDaoIF monDao;

    @Override
    public ArrayList<Monitor> view(int page, int maxRow, String name, int parent, String ip, String pos) {
        return monDao.view(page, maxRow, name, parent, ip, pos);
    }

    @Override
    public int count(String name, int parent, String ip, String pos) {
        return monDao.count(name, parent, ip, pos);
    }

    @Override
    public Monitor findById(int id) {
        return monDao.findById(id);
    }

    @Override
    public int create(Monitor one) {
        return monDao.create(one);
    }

    @Override
    public Monitor update(Monitor one) {
        return monDao.update(one);
    }

    @Override
    public Monitor delete(int id) {
        return monDao.delete(id);
    }

}
