/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.services;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.dao.SysAccountDaoIF;
import vn.htcjsc.brandname.voice.model.SysAccount;

/**
 *
 * @author Private
 */
@Service("accService")
@Transactional
public class SysAccountServiceImpl implements SysAccountService {

    static final Logger logger = Logger.getLogger(SysAccountServiceImpl.class);

    @Autowired
    SysAccountDaoIF accDao;
    @Autowired

    //--
    static String[] USER_BLACK_LIST = {"admin",
        "administrator",
        "moderator",
        "mode",
        "quantri",};

    public static boolean isBlackList(String user) {
        boolean result = false;
        for (String one : USER_BLACK_LIST) {
            if (user.startsWith(one)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void outerReload() {

    }

    @Override
    public SysAccount getSysAccountLogin(HttpSession session) {
        SysAccount acc = null;
        try {
            acc = (SysAccount) session.getAttribute(MyConfig.USER_SESSION_NAME);
        } catch (Exception e) {
            logger.error("SysAccount.getSysAccount:" + Tool.getLogMessage(e));
        }
        return acc;
    }

    @Override
    public String getUserName(HttpServletRequest request) {
        SysAccount acc = getSysAccountLogin(request);
        if (acc != null) {
            return acc.getUsername();
        } else {
            return "Unknow";
        }
    }

    @Override
    public SysAccount getSysAccountLogin(HttpServletRequest request) {
        SysAccount acc = null;
        try {
            HttpSession session = request.getSession(false);
            acc = (SysAccount) session.getAttribute(MyConfig.USER_SESSION_NAME);
        } catch (Exception e) {
            logger.error("SysAccount.getSysAccount:" + Tool.getLogMessage(e));
        }
        return acc;
    }

    @Override
    public SysAccount findById(int accID) {
        return accDao.findById(accID);
    }

    @Override
    public SysAccount checkLoginDB(String userName, String password) {
        SysAccount account = accDao.checkLoginDB(userName, password);
        if (account != null) {
        }
        return account;
    }

    @Override
    public int create(SysAccount oneAcc) {
        if (isBlackList(oneAcc.getUsername())) {
            return -1;
        }
        return accDao.create(oneAcc);
    }

    @Override
    public SysAccount update(SysAccount accUpdate) {
        return accDao.update(accUpdate);
    }

    @Override
    public SysAccount delete(int accID) {
        return accDao.delete(accID);
    }

    @Override
    public SysAccount deleteForEver(int id) {
        return accDao.deleteForEver(id);
    }

    @Override
    public SysAccount undoDelete(int accID) {
        return accDao.undoDelete(accID);
    }

    @Override
    public boolean updateIsdelete(int accID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated 
    }
//--

    // ADDITION Method
    /**
     *
     * @param rolePath
     * @param uri
     * @param contextPath
     * @return
     */
    private boolean checkURL(String rolePath, String uri, String contextPath) {
        boolean right = Boolean.FALSE;
        try {
            if (!Tool.checkNull(rolePath) && !Tool.checkNull(uri)) {
                rolePath = Tool.removeEndCharactor(rolePath, "/");
                uri = Tool.removeEndCharactor(uri, "/");
                String[] arrPath = rolePath.split("[;,]");
                if (arrPath != null) {
                    for (String onePath : arrPath) {
//                        System.out.println("==>onePath:"+onePath+" | => "+contextPath + onePath);
                        if (onePath.equals(uri) || uri.equals(contextPath + onePath)) {
                            right = true;
                            break;
                        }
                    }
                }
            } else {
                logger.error("==> !Tool.checkNull(rolePath):" + !Tool.checkNull(rolePath) + " | !Tool.checkNull(uri):" + !Tool.checkNull(uri));
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }

        return right;
    }

    @Override
    public ArrayList<SysAccount> view(int page, int maxRow, String key, String phone, String email, int status) {
        return accDao.view(page, maxRow, key, phone, email, status);
    }

    @Override
    public int count(String key, String phone, String email, int status) {
        return accDao.count(key, phone, email, status);
    }

}
