package vn.htcjsc.brandname.voice.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vn.htcjsc.brandname.voice.dao.SysAccountDaoIF;
import vn.htcjsc.brandname.voice.model.SysAccount;

public interface SysAccountService extends SysAccountDaoIF {

    public SysAccount getSysAccountLogin(HttpSession session);

    public String getUserName(HttpServletRequest request);

    public SysAccount getSysAccountLogin(HttpServletRequest request);

    public boolean updateIsdelete(int accID);
}
