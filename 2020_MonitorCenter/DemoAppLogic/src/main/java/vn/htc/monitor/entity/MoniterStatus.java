/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import vn.htc.monitor.util.RouteMonitor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.db.DBPool;

/**
 *
 * @author HTC-PC
 */
public class MoniterStatus {
    final Logger logger = Logger.getLogger(MoniterStatus.class);
    public static final HashMap<String, MoniterStatus> CACHE = new HashMap<>();
    
    static {
        MoniterStatus dao = new MoniterStatus();
        ArrayList<MoniterStatus> cache = dao.findAll();
        cache.forEach((one) -> {
            CACHE.put(one.getName(), one);
        });
    }
    
    public static void reload() {
        MoniterStatus dao = new MoniterStatus();
        ArrayList<MoniterStatus> cache = dao.findAll();
        CACHE.clear();
        if (cache != null && !cache.isEmpty()) {
            cache.forEach((one) -> {
                CACHE.put(one.getName(), one);
            });
        }
    }
    
    public static MoniterStatus getMonitorStatus4Cache(String user) {
        synchronized (CACHE) {
            return CACHE.get(user);
        }
    }
    
    public ArrayList<MoniterStatus> findAll() {
        ArrayList<MoniterStatus> all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM monitor WHERE 1 = 1 AND 2 = 2";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                MoniterStatus mnt = new MoniterStatus();
                
                mnt.setId(rs.getInt("ID"));
                mnt.setName(rs.getString("NAME"));
                mnt.setIp(rs.getString("IP"));
                mnt.setPos(rs.getString("POS"));
                int stt = rs.getInt("STATUS");
                if (stt == 0) {
                    mnt.setStatus("DOWN");
                } else {
                    mnt.setStatus("UP");
                }
                mnt.setTimedown(rs.getTimestamp("TIMEDOWN"));
                mnt.setTimeup(rs.getTimestamp("TIMEUP"));
                mnt.setCheckstatus(rs.getString("CHECK_STATUS"));
                mnt.setPhone(rs.getString("PHONE"));
                mnt.setEmail(rs.getString("EMAIL"));
                mnt.setTelegram(rs.getString("TELEGRAM"));
                mnt.setVoice(rs.getString("VOICE"));
                mnt.setType(rs.getInt("TYPE"));
                all.add(mnt);
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        System.out.println("3333333333333333333333");
        return all;
    }
    
    public MoniterStatus findByName(String name) {
        MoniterStatus mnt = new MoniterStatus();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM monitor WHERE NAME = ? AND 2 = 2";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, name);
            rs = pstm.executeQuery();
            if (rs.next()) {
                mnt = new MoniterStatus();
                mnt.setId(rs.getInt("ID"));
                mnt.setName(rs.getString("NAME"));
                mnt.setIp(rs.getString("IP"));
                mnt.setPos(rs.getString("POS"));
                int stt = rs.getInt("STATUS");
                if (stt == 0) {
                    mnt.setStatus("DOWN");
                } else {
                    mnt.setStatus("UP");
                }
                java.util.Date date = new java.util.Date(); 
                mnt.setTimedown(rs.getTimestamp("TIMEDOWN"));
                if (mnt.getTimedown() == null) {
                    mnt.setTimedown(new Timestamp(date.getTime() - 10000));
                }
                mnt.setTimeup(rs.getTimestamp("TIMEUP"));
                if (mnt.getTimeup() == null) {
                    mnt.setTimeup(new Timestamp(date.getTime() + 10000));
                }
                String checkstt = rs.getString("CHECK_STATUS");
                if (checkstt == null) {
                    mnt.setCheckstatus(new RouteMonitor()); 
                } else {
                    mnt.setCheckstatus(checkstt);
                }
                mnt.setPhone(rs.getString("PHONE"));
                mnt.setEmail(rs.getString("EMAIL"));
                mnt.setTelegram(rs.getString("TELEGRAM"));
                mnt.setVoice(rs.getString("VOICE"));
            }
        } catch (Exception e) {
            Tool.debug(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return mnt;
    }
    
    public MoniterStatus checkMonitorStatus(String name, String stt) {
        MoniterStatus mnt = this.findByName(name);
        
        if (
            mnt.getCheckstatus().getMstt1().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt2().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt3().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt4().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt5().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt6().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt7().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt8().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt9().getStatus().equals("0") &&
            mnt.getCheckstatus().getMstt10().getStatus().equals("0")
           ) {
            mnt.setStatus("DOWN");
            mnt.setSend(true);
        } else {
            mnt.setStatus("UP");
            mnt.setSend(false);
        }
        
        String num = mnt.getCheckstatus().getNum().getStatus();
        
        switch (num) {
            case "1":
                mnt.getCheckstatus().getMstt1().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("2");
                break;
            case "2":
                mnt.getCheckstatus().getMstt2().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("3");
                break;
            case "3":
                mnt.getCheckstatus().getMstt3().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("4");
                break;
            case "4":
                mnt.getCheckstatus().getMstt4().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("5");
                break;
            case "5":
                mnt.getCheckstatus().getMstt5().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("6");
                break;
            case "6":
                mnt.getCheckstatus().getMstt6().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("7");
                break;
            case "7":
                mnt.getCheckstatus().getMstt7().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("8");       
                break;
            case "8":
                mnt.getCheckstatus().getMstt8().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("9");
                break;
            case "9":
                mnt.getCheckstatus().getMstt9().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("10");
                break;
            case "10":
                mnt.getCheckstatus().getMstt10().setStatus(stt);
                mnt.getCheckstatus().getNum().setStatus("1");
                break;
            default:
                break;
        }
                
        return mnt;
    }
    
    public boolean setTimeUpOrDown(String name, int updown) {
        MoniterStatus mnt = this.findByName(name);
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE monitor SET ";
        if (updown == 1) {
            sql += " TIMEUP ";
        } else {
            sql += " TIMEDOWN ";
        }
        
        sql += " = CURRENT_TIMESTAMP WHERE NAME = ? AND 3 = 3";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, mnt.getName());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload();
            }
        } catch (Exception e) {
            Tool.debug(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    
    public boolean setTimeUpOrDown2(String name, int updown) {
        MoniterStatus mnt = this.findByName(name);
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE monitor SET ";
        if (updown == 1) {
            sql += " TIMEUP ";
        } else {
            sql += " TIMEDOWN ";
        }
        
        sql += " = CURRENT_TIMESTAMP WHERE PARENT = ? AND 3 = 3";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, mnt.getName());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload();
            }
        } catch (Exception e) {
            Tool.debug(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    
    public boolean setMonitorStatus(String name, String sttm) {
        MoniterStatus mnt = this.checkMonitorStatus(name, sttm);
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE monitor SET STATUS = ?, CHECK_STATUS = ? WHERE NAME = ? AND 3 = 3";
        String route = RouteMonitor.toStringJson(mnt.getCheckstatus());
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            String stt = mnt.getStatus();
            if (stt.equals("UP")) {
                pstm.setInt(i++, 1);                
            } else {
                pstm.setInt(i++, 0);
            }
            pstm.setString(i++, route);
            pstm.setString(i++, mnt.getName());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload();
            }
        } catch (Exception e) {
            Tool.debug(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    @Override
    public String toString() {
        return "MonitorStatus{" + "id=" + id + ", name=" + name + ", ip=" + ip + ", pos=" + pos + ", status=" + status + ", timedown=" + timedown + ", timeup=" + timeup + "}";
    }
        
    private int id;
    private String name;
    private String ip;
    private String pos;
    private String status;
    private Timestamp timedown;
    private Timestamp timeup;
    private RouteMonitor checkstatus;
    private String phone;
    private String email;
    private String telegram;
    private String voice;
    private boolean send;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MoniterStatus() {
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
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

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public RouteMonitor getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(RouteMonitor checkstatus) {
        this.checkstatus = checkstatus;
    }
    
    public void setCheckstatus(String strJson) {
        this.checkstatus = RouteMonitor.json2Object(strJson);
    }
    
    public static enum TYPE {

        UP(0, "UP"), 
        DOWN(1, "DOWN");
        
        public int val;
        public String name;

        private TYPE(int val, String name) {
            this.val = val;
            this.name = name;
        }
    }
    
}
