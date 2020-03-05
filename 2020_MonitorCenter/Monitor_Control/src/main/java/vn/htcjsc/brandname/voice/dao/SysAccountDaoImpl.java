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
import vn.htcjsc.brandname.voice.commons.Md5;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.db.DBPool;
import vn.htcjsc.brandname.voice.model.SysAccount;

/**
 *
 * @author Private
 */
@Repository("accDao")
public class SysAccountDaoImpl implements SysAccountDaoIF {

    static Logger logger = Logger.getLogger(SysAccountDaoImpl.class);

    @Override
    public ArrayList<SysAccount> view(int page, int maxRow, String key, String phone, String email, int status) {
        ArrayList<SysAccount> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.* FROM ACCOUNT A ";
        if (!Tool.checkNull(key)) {
            sql += " WHERE (USERNAME like ?  OR FULL_NAME like ?)";
        }
        if (!Tool.checkNull(phone)) {
            sql += " AND PHONE like ? ";
        }
        if (!Tool.checkNull(email)) {
            sql += " AND EMAIL like ? ";
        }
        if (status != MyConfig.STATUS.ALL.val) {
            sql += " AND STATUS like ? ";
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
            if (!Tool.checkNull(phone)) {
                pstm.setString(i++, "%" + phone + "%");
            }
            if (!Tool.checkNull(email)) {
                pstm.setString(i++, "%" + email + "%");
            }
            if (status != MyConfig.STATUS.ALL.val) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, (page - 1) * maxRow);
            pstm.setInt(i++, maxRow);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SysAccount acc = new SysAccount();
                acc.setId(rs.getInt("ID"));
                acc.setUsername(rs.getString("USERNAME"));
                acc.setPassword(rs.getString("PASSWORD"));
                acc.setFull_name(rs.getString("FULL_NAME"));
                acc.setDescription(rs.getString("DESCRIPTION"));
                acc.setAddress(rs.getString("ADDRESS"));
                acc.setPhone(rs.getString("PHONE"));
                acc.setEmail(rs.getString("EMAIL"));
                acc.setTelegram(rs.getString("TELEGRAM"));
                acc.setVoice(rs.getString("VOICE"));
                acc.setStatus(rs.getInt("STATUS"));
                acc.setCreate_date(rs.getTimestamp("CREATE_DATE"));
                acc.setCreate_by(rs.getString("CREATE_BY"));
                acc.setUpdate_date(rs.getTimestamp("UPDATE_DATE"));
                acc.setUpdate_by(rs.getString("UPDATE_BY"));
                acc.setStatus(rs.getInt("STATUS"));
                result.add(acc);
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
    public int count(String key, String phone, String email, int status) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM ACCOUNT A ";
        if (!Tool.checkNull(key)) {
            sql += " WHERE (USERNAME like ?  OR FULL_NAME like ?)";
        }
        if (!Tool.checkNull(phone)) {
            sql += " AND PHONE like ? ";
        }
        if (!Tool.checkNull(email)) {
            sql += " AND EMAIL like ? ";
        }
        if (status != MyConfig.STATUS.ALL.val) {
            sql += " AND STATUS like ? ";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            if (!Tool.checkNull(phone)) {
                pstm.setString(i++, "%" + phone + "%");
            }
            if (!Tool.checkNull(email)) {
                pstm.setString(i++, "%" + email + "%");
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
    public SysAccount findById(int id) {
        SysAccount acc = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ACCOUNT  WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                acc = new SysAccount();
                acc.setId(rs.getInt("ID"));
                acc.setUsername(rs.getString("USERNAME"));
                acc.setPassword(rs.getString("PASSWORD"));
                acc.setFull_name(rs.getString("FULL_NAME"));
                acc.setDescription(rs.getString("DESCRIPTION"));
                acc.setAddress(rs.getString("ADDRESS"));
                acc.setPhone(rs.getString("PHONE"));
                acc.setEmail(rs.getString("EMAIL"));
                acc.setTelegram(rs.getString("TELEGRAM"));
                acc.setVoice(rs.getString("VOICE"));
                acc.setStatus(rs.getInt("STATUS"));
                acc.setCreate_date(rs.getTimestamp("CREATE_DATE"));
                acc.setCreate_by(rs.getString("CREATE_BY"));
                acc.setUpdate_date(rs.getTimestamp("UPDATE_DATE"));
                acc.setUpdate_by(rs.getString("UPDATE_BY"));
                acc.setStatus(rs.getInt("STATUS"));
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return acc;
    }

    @Override
    public SysAccount checkLoginDB(String userName, String password) {
        SysAccount result = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ACCOUNT  WHERE upper(USERNAME) = upper(?) AND PASSWORD = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, Md5.encryptMD5(password));
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = new SysAccount();
                result.setId(rs.getInt("ID"));
                result.setUsername(rs.getString("USERNAME"));
                result.setPassword(rs.getString("PASSWORD"));
                result.setFull_name(rs.getString("FULL_NAME"));
                result.setDescription(rs.getString("DESCRIPTION"));
                result.setAddress(rs.getString("ADDRESS"));
                result.setPhone(rs.getString("PHONE"));
                result.setEmail(rs.getString("EMAIL"));
                result.setTelegram(rs.getString("TELEGRAM"));
                result.setVoice(rs.getString("VOICE"));
                result.setStatus(rs.getInt("STATUS"));
                result.setCreate_date(rs.getTimestamp("CREATE_DATE"));
                result.setCreate_by(rs.getString("CREATE_BY"));
                result.setUpdate_date(rs.getTimestamp("UPDATE_DATE"));
                result.setUpdate_by(rs.getString("UPDATE_BY"));
                result.setStatus(rs.getInt("STATUS"));
            }
        } catch (SQLException ex) {
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    @Override
    public int create(SysAccount oneAcc) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO ACCOUNT(USERNAME,PASSWORD,FULL_NAME,DESCRIPTION,ADDRESS,PHONE,EMAIL,TELEGRAM,VOICE,CREATE_DATE,CREATE_BY,STATUS) "
                + "                   VALUES( ?   ,    ?   ,     ?   ,        ?  ,?      ,  ?  ,   ? ,   ?    ,?    ,  NOW()   ,    ?    ,   ?  )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 1;
            pstm.setString(i++, oneAcc.getUsername());
            pstm.setString(i++, Md5.encryptMD5(oneAcc.getPassword()));
            pstm.setString(i++, oneAcc.getFull_name());
            pstm.setString(i++, oneAcc.getDescription());
            pstm.setString(i++, oneAcc.getAddress());
            pstm.setString(i++, oneAcc.getPhone());
            pstm.setString(i++, oneAcc.getEmail());
            pstm.setString(i++, oneAcc.getTelegram());
            pstm.setString(i++, oneAcc.getVoice());
            pstm.setString(i++, oneAcc.getCreate_by());
            pstm.setInt(i++, oneAcc.getStatus());
            if (pstm.executeUpdate() == 1) {
                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating SysAccount failed, no ID obtained.");
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

    @Override
    public SysAccount update(SysAccount edit) {
        SysAccount result = findById(edit.getId());
        if (result != null) {
            Connection conn = null;
            PreparedStatement pstm = null;
            String sql = "UPDATE ACCOUNT SET USERNAME = ?,";
            if (!Tool.checkNull(edit.getPassword())) {
                sql += "PASSWORD = ?,";
            }
            sql += "FULL_NAME = ?,ADDRESS = ?,PHONE = ?,EMAIL = ?,TELEGRAM= ?,VOICE=?"
                    + ",UPDATE_DATE = NOW(),UPDATE_BY = ?,STATUS=? "
                    + " where ID = ?";
            try {
                conn = DBPool.getConnection();
                conn.setAutoCommit(false);
                pstm = conn.prepareStatement(sql);
                int i = 1;

                pstm.setString(i++, edit.getUsername());
                if (!Tool.checkNull(edit.getPassword())) {
                    pstm.setString(i++, Md5.encryptMD5(edit.getPassword()));
                }
                pstm.setString(i++, edit.getFull_name());
                pstm.setString(i++, edit.getDescription());
                pstm.setString(i++, edit.getAddress());
                pstm.setString(i++, edit.getPhone());
                pstm.setString(i++, edit.getEmail());
                pstm.setString(i++, edit.getTelegram());
                pstm.setString(i++, edit.getVoice());
                pstm.setString(i++, edit.getUpdate_by());
                pstm.setInt(i++, edit.getStatus());
                pstm.setInt(i++, edit.getId());
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

    @Override
    public SysAccount deleteForEver(int id) {
        SysAccount result = findById(id);
        if (result != null) {
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "DELETE FROM ACCOUNT WHERE ID = ?";
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
            } catch (SQLException e) {
                DBPool.rollback(conn, result);
                logger.error(Tool.getLogMessage(e));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
        }
        return result;
    }

    @Override
    public SysAccount delete(int accID) {
        SysAccount result = findById(accID);
        if (result != null) {

            Connection conn = null;
            PreparedStatement pstm = null;
            String sql = "UPDATE  SYS_ACCOUNT SET ISDEL = ? WHERE ACC_ID = ?";
            try {
                conn = DBPool.getConnection();
                conn.setAutoCommit(false);
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, Boolean.TRUE);
                pstm.setInt(2, accID);
                if (pstm.executeUpdate() == 1) {
                    conn.commit();
                } else {
                    DBPool.rollback(conn, result);
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

    @Override
    public SysAccount undoDelete(int accID) {
        SysAccount result = findById(accID);
        if (result != null) {
            Connection conn = null;
            PreparedStatement pstm = null;
            String sql = "UPDATE  SYS_ACCOUNT SET ISDEL = ? WHERE ACC_ID = ?";
            try {
                conn = DBPool.getConnection();
                conn.setAutoCommit(false);
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, Boolean.FALSE);
                pstm.setInt(2, accID);
                if (pstm.executeUpdate() == 1) {
                    conn.commit();
                } else {
                    DBPool.rollback(conn, result);
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
