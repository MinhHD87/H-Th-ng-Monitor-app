package vn.htcjsc.brandname.voice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.db.DBPool;
import vn.htcjsc.brandname.voice.model.Monitor;

@Repository("MonDao")
public class MonitorDaoImpl implements MonitorDaoIF {

    static Logger logger = Logger.getLogger(MonitorDaoImpl.class);

    @Override
    public Monitor findById(int id) {
        Monitor mon = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM MONITOR  WHERE ID = ?";
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
                mon.setParent(rs.getInt("PARENT"));
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
    public int create(Monitor one) {
        {
            int result = 0;
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "INSERT INTO MONITOR(NAME,DESCRIPTION,IP,POS,USERNAME,PASSWORD,PARENT,PHONE,EMAIL,TELEGRAM,VOICE)"
                    + "            VALUES( ?   ,    ?   , ?   ,  ?  ,?      ,  ?  ,   ? ,   ?    ,?    ,     ?    ,   ?  )";
            try {

                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                int i = 1;
                pstm.setString(i++, one.getName());
                pstm.setString(i++, one.getDescription());
                pstm.setString(i++, one.getIp());
                pstm.setString(i++, one.getPos());
                pstm.setString(i++, one.getUsername());
                pstm.setString(i++, one.getPassword());
                pstm.setInt(i++, one.getParent());
                pstm.setString(i++, one.getPhone());
                pstm.setString(i++, one.getEmail());
                pstm.setString(i++, one.getTelegram());
                pstm.setString(i++, one.getVoice());
                if (pstm.executeUpdate() == 1) {
                    try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            result = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Creating Monitor failed, no ID obtained.");
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.error(Tool.getLogMessage(ex));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
            return result;
        }
    }

    @Override
    public Monitor update(Monitor one) {
        {
            Monitor result = findById(one.getId());
            if (result != null) {
                Connection conn = null;
                PreparedStatement pstm = null;
                String sql = "UPDATE MONITOR SET NAME =? ,DESCRIPTION= ?,IP = ?,POS = ?,USERNAME =? ,PASSWORD= ?,PARENT= ?,PHONE= ? ,EMAIL= ?,TELEGRAM= ?,VOICE= ?  WHERE ID = ?";
                try {
                    conn = DBPool.getConnection();
                    conn.setAutoCommit(false);
                    pstm = conn.prepareStatement(sql);
                    int i = 1;
                    pstm.setString(i++, one.getName());
                    pstm.setString(i++, one.getDescription());
                    pstm.setString(i++, one.getIp());
                    pstm.setString(i++, one.getPos());
                    pstm.setString(i++, one.getUsername());
                    pstm.setString(i++, one.getPassword());
                    pstm.setInt(i++, one.getParent());
                    pstm.setString(i++, one.getPhone());
                    pstm.setString(i++, one.getEmail());
                    pstm.setString(i++, one.getTelegram());
                    pstm.setString(i++, one.getVoice());
                    pstm.setInt(i++, one.getId());
                    if (pstm.executeUpdate() == 1) {
                        conn.commit();
                        return result;
                    } else {
                        conn.rollback();
                        result = null;
                    }
                } catch (SQLException ex) {
                    DBPool.rollback(conn, result);
                    logger.error(Tool.getLogMessage(ex));
                } finally {
                    DBPool.freeConn(null, pstm, conn);
                }
            }
            return result;
        }
    }

    @Override
    public Monitor delete(int id) {
        Monitor result = findById(id);
        if (result != null) {
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "DELETE FROM MONITOR WHERE ID = ?";
            try {
                conn = DBPool.getConnection();
                conn.setAutoCommit(false);
                pstm = conn.prepareStatement(sql);
                int i = 1;
                pstm.setInt(i++, id);
                if (pstm.executeUpdate() != 1) {
                    DBPool.rollback(conn, result);
                } else {
                    conn.commit();
                }
            } catch (Exception e) {
                DBPool.rollback(conn, result);
                logger.error(Tool.getLogMessage(e));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Monitor> view(int page, int maxRow, String name, int parent, String ip, String pos) {
        ArrayList<Monitor> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT M.* FROM MONITOR M ";
        if (!Tool.checkNull(name)) {
            sql += " WHERE NAME like ?";
        }
//        if (!Tool.checkNull(parent)) {
//            sql += " AND PARENT = ? ";
//        }
        if (!Tool.checkNull(ip)) {
            sql += " AND IP like ? ";
        }
        if (!Tool.checkNull(pos)) {
            sql += " AND POS like ? ";
        }

        sql += " ORDER BY M.ID DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(name)) {
                pstm.setString(i++, "%" + name + "%");
            }
//            if (!Tool.checkNull(parent)) {
//                pstm.setInt(i++, parent);
//            }
            if (!Tool.checkNull(ip)) {
                pstm.setString(i++, "%" + ip + "%");
            }
            if (!Tool.checkNull(pos)) {
                pstm.setString(i++, "%" + pos + "%");
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
                mon.setParent(rs.getInt("PARENT"));
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
    public int count(String name, int parent, String ip, String pos) {
        {
            int result = 0;
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "SELECT count(*) FROM MONITOR M ";
            if (!Tool.checkNull(name)) {
                sql += " WHERE NAME like ?)";
            }
//            if (!Tool.checkNull(parent)) {
//                sql += " AND PARENT = ? ";
//            }
            if (!Tool.checkNull(ip)) {
                sql += " AND IP like ? ";
            }
            if (!Tool.checkNull(pos)) {
                sql += " AND POS like ? ";
            }
            try {
                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql);
                int i = 1;
                if (!Tool.checkNull(name)) {
                    pstm.setString(i++, "%" + name + "%");
                }
//                if (!Tool.checkNull(parent)) {
//                    pstm.setInt(i++, parent);
//                }
                if (!Tool.checkNull(ip)) {
                    pstm.setString(i++, "%" + ip + "%");
                }
                if (!Tool.checkNull(pos)) {
                    pstm.setString(i++, "%" + pos + "%");
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
    }
}
