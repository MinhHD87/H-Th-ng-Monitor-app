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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import vn.htcjsc.brandname.voice.bean.MyItem;
import vn.htcjsc.brandname.voice.commons.Md5;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.db.DBPool;
import vn.htcjsc.brandname.voice.model.Monitor;

/**
 *
 * @author Private
 */
@Repository("chartDao")
public class ChartDaoImpl implements ChartDaoIF {

    static Logger logger = Logger.getLogger(ChartDaoImpl.class);

    public ArrayList<Monitor> getview(int page, int maxRow, String key, String ip, String pos, int status) {
        ArrayList<Monitor> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        String sql = "SELECT A.* FROM monitor A WHERE PARENT = 0 ";
        if (!Tool.checkNull(key)) {
            sql += " AND (NAME like ? OR USERNAME like ?)";
        }
        if (!Tool.checkNull(ip)) {
            sql += " AND IP = ? ";
        }
        if (!Tool.checkNull(pos)) {
            sql += " AND POS = ? ";
        }
        if (status != 127) {
            sql += " AND STATUS = ? ";
        }
        sql += " ORDER BY A.ID DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            if (!Tool.checkNull(ip)) {
                pstm.setString(i++, ip);
            }
            if (!Tool.checkNull(pos)) {
                pstm.setString(i++, pos);
            }
            if (status != 127) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, (page - 1) * maxRow);
            pstm.setInt(i++, maxRow);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Monitor mon = new Monitor();
                mon.setId(rs.getInt("ID"));
                mon.setName(rs.getString("NAME"));
                mon.setDescription(rs.getString("DESCRIPTION"));
                mon.setIp(rs.getString("IP"));
                mon.setPos(rs.getString("POS"));
                mon.setUsername(rs.getString("USERNAME"));
                mon.setPassword(rs.getString("PASSWORD"));
                mon.setStatus(rs.getInt("STATUS"));
                mon.setParent(rs.getInt("PARENT"));
                mon.setTimedown(rs.getTimestamp("TIMEDOWN"));
                mon.setTimeup(rs.getTimestamp("TIMEUP"));
                mon.setPhone(rs.getString("PHONE"));
                mon.setEmail(rs.getString("EMAIL"));
                mon.setTelegram(rs.getString("TELEGRAM"));
                mon.setVoice(rs.getString("VOICE"));
                
                result.add(mon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }
    
    @Override
    public ArrayList<Monitor> view(int page, int maxRow, String key, String ip, String pos, int status) {
        ArrayList<Monitor> result = this.getview(page, maxRow, key, ip, pos, status);
        result.forEach((Monitor res) -> {
            ArrayList<Monitor> arrayList = new ArrayList<>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            
            String sql = "SELECT * FROM monitor  WHERE PARENT = ? ";
            try {
                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, res.getId());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Monitor mon = new Monitor();
                    mon.setId(rs.getInt("ID"));
                    mon.setName(rs.getString("NAME"));
                    mon.setDescription(rs.getString("DESCRIPTION"));
                    mon.setIp(rs.getString("IP"));
                    mon.setPos(rs.getString("POS"));
                    mon.setUsername(rs.getString("USERNAME"));
                    mon.setPassword(rs.getString("PASSWORD"));
                    mon.setStatus(rs.getInt("STATUS"));
                    mon.setParent(rs.getInt("PARENT"));
                    mon.setTimedown(rs.getTimestamp("TIMEDOWN"));
                    mon.setTimeup(rs.getTimestamp("TIMEUP"));
                    mon.setPhone(rs.getString("PHONE"));
                    mon.setEmail(rs.getString("EMAIL"));
                    mon.setTelegram(rs.getString("TELEGRAM"));
                    mon.setVoice(rs.getString("VOICE"));
                    arrayList.add(mon);
                }
            } catch (SQLException ex) {
                logger.error(Tool.getLogMessage(ex));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
            res.setArr(arrayList);
            res.setStr(arrayList);
        });
        return result;
    }

    @Override
    public int count(String key, String ip, String pos, int status) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM monitor A WHERE PARENT = 0 ";
        if (!Tool.checkNull(key)) {
            sql += " AND (NAME like ?  OR USERNAME like ?)";
        }
        if (!Tool.checkNull(ip)) {
            sql += " AND IP = ? ";
        }
        if (!Tool.checkNull(pos)) {
            sql += " AND POS = ? ";
        }
        if (status != 127) {
            sql += " AND STATUS = ? ";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            if (!Tool.checkNull(ip)) {
                pstm.setString(i++, ip);
            }
            if (!Tool.checkNull(pos)) {
                pstm.setString(i++, pos);
            }
            if (status != 127) {
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
    public Monitor findIdAndTime(int id,Timestamp timesreach) {
        Monitor mon = this.getById(id);
        if(mon != null) {
            List<MyItem> listHDD = new ArrayList<>();
            List<MyItem> listRAM = new ArrayList<>();
            List<MyItem> listCPU = new ArrayList<>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM system WHERE NAME = ?";
            try {
                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, mon.getName());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date ngay = formatter.parse(timesreach+"");
                    Date ngay2 = formatter.parse(rs.getTimestamp("TIMECHECK")+""); ;  
                    DateFormat formatter3 = new SimpleDateFormat("yyyy");
                    Date nam = formatter3.parse(timesreach+"");
                    Date nam2 = formatter3.parse(rs.getTimestamp("TIMECHECK")+"");  
                    
                    if(nam.compareTo(nam2) == 0) {
                        MyItem item = new MyItem();
                        item.setTime(rs.getTimestamp("TIMECHECK").toString());
                        item.setValue(Tool.string2Integer(rs.getString("CONTENT")));
                        switch (rs.getInt("TYPE")) {
                            case 1:
                                if(ngay.compareTo(ngay2) == 0) {
                                    listRAM.add(item);
                                }
                                break;
                            case 2:
                                listHDD.add(item);
                                break;
                            case 3:
                                if(ngay.compareTo(ngay2) == 0) {
                                    listCPU.add(item);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (SQLException ex) {
                logger.error(Tool.getLogMessage(ex));
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(ChartDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
            mon.setListRam(listRAM);
            mon.setListHDD(listHDD);
            mon.setListCPU(listCPU);
        }
        return mon;
    }

    @Override
    public Monitor findById(int id) {
        Monitor mon = this.getById(id);
        if(mon != null) {
            List<MyItem> listHDD = new ArrayList<>();
            List<MyItem> listRAM = new ArrayList<>();
            List<MyItem> listCPU = new ArrayList<>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM system WHERE NAME = ?";
            try {
                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, mon.getName());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    MyItem item = new MyItem();
                    item.setTime(rs.getTimestamp("TIMECHECK").toString());
                    item.setValue(Tool.string2Integer(rs.getString("CONTENT")));
                    switch (rs.getInt("TYPE")) {
                        case 1:
                            listRAM.add(item);
                            break;
                        case 2:
                            listHDD.add(item);
                            break;
                        case 3:
                            listCPU.add(item);
                            break;
                        default:
                            break;
                    }
                }
            } catch (SQLException ex) {
                logger.error(Tool.getLogMessage(ex));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
            mon.setListRam(listRAM);
            mon.setListHDD(listHDD);
            mon.setListCPU(listCPU);
        }
        return mon;
    }
    
    public Monitor getById(int id) {
        Monitor mon = this.findWithId(id);
        if(mon != null) {
            ArrayList<Monitor> arrayList = new ArrayList<>();
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM monitor WHERE PARENT = ?";
            try {
                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, mon.getId());
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Monitor mo = new Monitor();
                    mo.setId(rs.getInt("ID"));
                    mo.setName(rs.getString("NAME"));
                    mo.setDescription(rs.getString("DESCRIPTION"));
                    mo.setIp(rs.getString("IP"));
                    mo.setPos(rs.getString("POS"));
                    mo.setUsername(rs.getString("USERNAME"));
                    mo.setPassword(rs.getString("PASSWORD"));
                    mo.setStatus(rs.getInt("STATUS"));
                    mo.setParent(rs.getInt("PARENT"));
                    mo.setTimedown(rs.getTimestamp("TIMEDOWN"));
                    mo.setTimeup(rs.getTimestamp("TIMEUP"));
                    mo.setPhone(rs.getString("PHONE"));
                    mo.setEmail(rs.getString("EMAIL"));
                    mo.setTelegram(rs.getString("TELEGRAM"));
                    mo.setVoice(rs.getString("VOICE"));
                    arrayList.add(mo);
                }
            } catch (SQLException ex) {
                logger.error(Tool.getLogMessage(ex));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
            mon.setArr(arrayList);
            mon.setStr(arrayList);
        }
        return mon;
    }
    
    public Monitor findWithId(int id) {
        Monitor mon = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM monitor WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                mon = new Monitor();
                mon.setId(rs.getInt("ID"));
                mon.setName(rs.getString("NAME"));
                mon.setDescription(rs.getString("DESCRIPTION"));
                mon.setIp(rs.getString("IP"));
                mon.setPos(rs.getString("POS"));
                mon.setUsername(rs.getString("USERNAME"));
                mon.setPassword(rs.getString("PASSWORD"));
                mon.setStatus(rs.getInt("STATUS"));
                mon.setParent(rs.getInt("PARENT"));
                mon.setTimedown(rs.getTimestamp("TIMEDOWN"));
                mon.setTimeup(rs.getTimestamp("TIMEUP"));
                mon.setPhone(rs.getString("PHONE"));
                mon.setEmail(rs.getString("EMAIL"));
                mon.setTelegram(rs.getString("TELEGRAM"));
                mon.setVoice(rs.getString("VOICE"));
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return mon;
    }

    @Override
    public Monitor checkLoginDB(String userName, String password) {
        Monitor mon = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM monitor WHERE upper(USERNAME) = upper(?) AND PASSWORD = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, Md5.encryptMD5(password));
            rs = pstm.executeQuery();
            if (rs.next()) {
                mon = new Monitor();
                mon.setId(rs.getInt("ID"));
                mon.setName(rs.getString("NAME"));
                mon.setDescription(rs.getString("DESCRIPTION"));
                mon.setIp(rs.getString("IP"));
                mon.setPos(rs.getString("POS"));
                mon.setUsername(rs.getString("USERNAME"));
                mon.setPassword(rs.getString("PASSWORD"));
                mon.setStatus(rs.getInt("STATUS"));
                mon.setParent(rs.getInt("PARENT"));
                mon.setTimedown(rs.getTimestamp("TIMEDOWN"));
                mon.setTimeup(rs.getTimestamp("TIMEUP"));
                mon.setPhone(rs.getString("PHONE"));
                mon.setEmail(rs.getString("EMAIL"));
                mon.setTelegram(rs.getString("TELEGRAM"));
                mon.setVoice(rs.getString("VOICE"));
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return mon;
    }

    @Override
    public int create(Monitor mon) {
        return 0;
    }

    @Override
    public Monitor update(Monitor mon) {
        return mon;
    }

    @Override
    public Monitor deleteForEver(int id) {
        return new Monitor();
    }

    @Override
    public Monitor delete(int id) {
        return new Monitor();
    }

    @Override
    public Monitor undoDelete(int id) {
        return new Monitor();
    }

}
