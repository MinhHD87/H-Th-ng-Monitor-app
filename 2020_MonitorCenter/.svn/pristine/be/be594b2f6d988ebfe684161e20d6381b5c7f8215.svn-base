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

import vn.htcjsc.brandname.voice.model.MessageMonitor;

/**
 *
 * @author hoan
 */
//@Repository("messageMonitorDao")
public class MessageMonitorDaoImpl implements MessageMonitorDaoIF {

    static final Logger logger = Logger.getLogger(MessageMonitorDaoImpl.class);



    @Override
    public ArrayList<MessageMonitor> getInfo() {
                ArrayList<MessageMonitor> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM message ORDER BY ID DESC";
        try {
            conn = DBPool.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MessageMonitor one = new MessageMonitor();
                one.setId(rs.getInt("ID"));
                one.setName(rs.getString("NAME"));
                one.setIp(rs.getString("IP"));
                one.setContent(rs.getString("CONTENT"));
                one.setCode(rs.getString("CODE"));
                one.setSento(rs.getString("SENDTO"));
                one.setStatus(rs.getInt("STATUS"));
                one.setType(rs.getInt("TYPE"));
                one.setTimesend(rs.getTimestamp("TIMESEND"));

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
