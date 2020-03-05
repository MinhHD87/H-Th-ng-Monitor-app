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
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.db.DBPool;

import vn.htcjsc.brandname.voice.model.SystemMonitor;

/**
 *
 * @author hoan
 */
//@Repository("systemMonitorDao")
public class SystemMonitorDaoImpl implements SystemMonitorDaoIF {

    static final Logger logger = Logger.getLogger(SystemMonitorDaoImpl.class);

    @Override

    public ArrayList<SystemMonitor> getInfo() {
        ArrayList<SystemMonitor> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM system ORDER BY ID DESC";
        try {
            conn = DBPool.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SystemMonitor one = new SystemMonitor();
                one.setId(rs.getInt("ID"));
                one.setName(rs.getString("NAME"));
                one.setIp(rs.getString("IP"));
                one.setIp(rs.getString("CONTENT"));
                one.setTimecheck(rs.getTimestamp("TIMECHECK"));
                one.setType(rs.getInt("TYPE"));
                one.setType(rs.getInt("STATUS"));

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

}
