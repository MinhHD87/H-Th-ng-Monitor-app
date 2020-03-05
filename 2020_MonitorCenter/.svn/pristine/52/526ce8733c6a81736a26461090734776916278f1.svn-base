/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.db.DBPool;


import vn.htcjsc.brandname.voice.model.SystemMonitor;

/**
 *
 * @author hoan
 */
@Repository("systemMonitorDao")
public class SystemMonitorDaoImpl implements SystemMonitorDaoIF {

    static final Logger logger = Logger.getLogger(SystemMonitorDaoImpl.class);

    @Override
    public ArrayList<SystemMonitor> listSystemMonitor() {
        ArrayList<SystemMonitor> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM SYSTEM ORDER BY ID DESC";
        try {
            conn = DBPool.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SystemMonitor one = new SystemMonitor();
                one.setId(rs.getInt("ID"));
                one.setName(rs.getString("NAME"));
                one.setIp(rs.getString("IP"));
                one.setContent(rs.getString("CONTENT"));
                one.setStatus(rs.getInt("STATUS"));
                one.setType(rs.getInt("TYPE"));
                one.setTimecheck(rs.getTimestamp("TIMECHECK"));

                list.add(one);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, ps, conn);
        }
        return list;
    }

    @Override
    public ArrayList<SystemMonitor> view(int page, int maxRow, String key, String ip, int status) {
        
        ArrayList<SystemMonitor> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.* FROM SYSTEM A WHERE 1=1  ";
        if (!Tool.checkNull(key)) {
            sql += "AND   NAME like ?";
        }
        if (!Tool.checkNull(ip)) {
            sql += " AND IP = ? ";
        }
        if (status != MyConfig.STATUS.ALL.val) {
            sql += " AND STATUS = ? ";
        }
        sql += " ORDER BY A.ID DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%"+key+"%");
            }
            if (!Tool.checkNull(ip)) {
                pstm.setString(i++, ip);
            }
            if (status != MyConfig.STATUS.ALL.val) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, (page - 1) * maxRow);
            pstm.setInt(i++, maxRow);
            rs = pstm.executeQuery();
            while (rs.next()) {
                 SystemMonitor one = new SystemMonitor();
                one.setId(rs.getInt("ID"));
                one.setName(rs.getString("NAME"));
                one.setIp(rs.getString("IP"));
                one.setContent(rs.getString("CONTENT"));
               
                one.setStatus(rs.getInt("STATUS"));
                one.setType(rs.getInt("TYPE"));
                one.setTimecheck(rs.getTimestamp("TIMECHECK"));
                
                result.add(one);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
//    
    }

    @Override
    public int count(String key, String ip, String content, int status) {
       int result = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM SYSTEM A WHERE 1=1";
        if (!Tool.checkNull(key)) {
            sql += " AND NAME like ?  ";
        }
        if (!Tool.checkNull(ip)) {
            sql += " AND IP = ? ";
        }
        if (!Tool.checkNull(content)) {
            sql += " AND CONTENT = ? ";
        }
        if (status != MyConfig.STATUS.ALL.val) {
            sql += " AND STATUS = ? ";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
               
            }
            if (!Tool.checkNull(ip)) {
                pstm.setString(i++, ip);
            }
            if (!Tool.checkNull(content)) {
                pstm.setString(i++, content);
            }
            if (status != MyConfig.STATUS.ALL.val) {
                pstm.setInt(i++, status);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
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
