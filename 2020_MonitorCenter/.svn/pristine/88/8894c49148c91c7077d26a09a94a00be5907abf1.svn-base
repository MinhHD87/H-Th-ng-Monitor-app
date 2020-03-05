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

/**
 *
 * @author Admin
 */
public class Monitor {

    final Logger logger = Logger.getLogger(Monitor.class);
    public static final HashMap<String, Monitor> CACHE = new HashMap<>();

    private int id;
    private String name;
    private String description;
    private String ip;
    private String pos;
    private String username;
    private String password;
    private int status;
    private int type;
    private int parent;
    private Timestamp timedown;
    private Timestamp timeup;
    private String check_status;
    private String phone;
    private String email;
    private String telegram;
    private String voice;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public Timestamp getTimedown() {
        return timedown;
    }

    public void setTimedown(Timestamp timedown) {
        this.timedown = timedown;
    }

    public Timestamp getTimeup() {
        return timeup;
    }

    public void setTimeup(Timestamp timeup) {
        this.timeup = timeup;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
    
}
