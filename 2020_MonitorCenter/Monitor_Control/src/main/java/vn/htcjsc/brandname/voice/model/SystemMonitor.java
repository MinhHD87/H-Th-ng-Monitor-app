/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.log4j.Logger;

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
    private int type;
    private int status;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public SystemMonitor(){};
    public SystemMonitor(int id, String name, String ip, String content, Timestamp timecheck, int type, int status) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.content = content;
        this.timecheck = timecheck;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SystemMonitor{" + "logger=" + logger + ", id=" + id + ", name=" + name + ", ip=" + ip + ", content=" + content + ", timecheck=" + timecheck + ", type=" + type + ", status=" + status + '}';
    }

    


   

}
