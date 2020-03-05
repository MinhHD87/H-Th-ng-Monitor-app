/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity;

import vn.htc.monitor.app.AppStart;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.DBPool;
import vn.htc.monitor.resource.http.NotifyMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.log4j.Logger;
import vn.htc.monitor.app.MonitorApp;

/**
 *
 * @author Admin
 */
public class SystemMonitor {

    final Logger logger = Logger.getLogger(SystemMonitor.class);
    public static final HashMap<String, SystemMonitor> CACHE = new HashMap<>();

    private int id;
    private String name;
    private String ip;
    private String content;
    private Timestamp timecheck;
    private int status;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimecheck() {
        return timecheck;
    }

    public void setTimecheck(Timestamp timecheck) {
        this.timecheck = timecheck;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SystemMonitor() {
    }

    public SystemMonitor(String name, String ip, String content, int status, int type) {
        this.name = name;
        this.ip = ip;
        this.content = content;
        this.status = status;
        this.type = type;
    }
    
    public static boolean addnew(SystemMonitor one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO system(NAME,IP,TIMECHECK,TYPE,STATUS,CONTENT) VALUES(?,?,NOW(),?,?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getName());
            pstm.setString(i++, one.getIp());
            pstm.setInt(i++, one.getType());
            pstm.setInt(i++, one.getStatus());
            pstm.setString(i++, one.getContent());
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }

        } catch (Exception e) {
            System.out.println(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    
}
