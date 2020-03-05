package vn.htcjsc.brandname.voice.controllers.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.htcjsc.brandname.voice.commons.AbstractConst;
import static vn.htcjsc.brandname.voice.commons.AbstractConst.LANG;
import vn.htcjsc.brandname.voice.commons.HttpUtil;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.config.MyContext;
import vn.htcjsc.brandname.voice.model.SysAccount;
import vn.htcjsc.brandname.voice.model.ext.AngularModel;
import vn.htcjsc.brandname.voice.model.ext.ResponResult;

/**
 *
 * @author Private
 */
@Controller
@RequestMapping("/admin")
public class SessionController extends AbstractBackEnConst {

    private final String TABLE = "ACCOUNTS";


    @InitBinder
    @Override
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringtrimmer);
    }

    @GetMapping({"/index", "/"})
    public String index(ModelMap model, HttpServletRequest request) {
        model.put("userlog", accService.getSysAccountLogin(request).getUsername());
        model.put(TITLE, LANG.get("generic.home"));
        return "index";
    }

    @GetMapping("/sessionExpire")
    public ResponseEntity<AngularModel<SysAccount>> sessionExpire(HttpServletRequest request) {
        AngularModel<SysAccount> ngModel = new AngularModel<>();
        ngModel.setResult(new ResponResult(AbstractConst.RESULT.NO_LOGIN, "Session Expire..."));
        System.out.println("-> /sessionExpire có sang đây");
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, ModelMap model) {
        SysAccount account = accService.getSysAccountLogin(request);
        model.put(TITLE, LANG.get("generic.login"));
        if (account != null) {
            return ADMIN_INDEX_PAGE;
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(ModelMap model, HttpServletRequest request) {
        String username = HttpUtil.getString(request, "username");
        String password = HttpUtil.getString(request, "password");
        HttpSession session = request.getSession(false);
        SysAccount account = accService.checkLoginDB(username, password);
        if (account != null) {
            if (account.getStatus() == MyConfig.STATUS.LOCK.val || account.getStatus() == MyConfig.STATUS.UNACTIVE.val) {
                model.addAttribute("error", "Tài khoản của bạn bị khóa hoặc chưa active, vui lòng liên hệ quản trị!");
                return "login";
            }
            session.setAttribute(MyConfig.USER_SESSION_NAME, account);
            session.setAttribute("accService", accService);
            MyContext.putSessionOnline(username, session);
            return ADMIN_INDEX_PAGE;
        } else {
            model.addAttribute("error", "Đăng nhập thất bại vui lòng kiểm tra lại! ");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SysAccount acc = accService.getSysAccountLogin(request);
        if (acc != null) {
            MyContext.logOutSession(acc.getUsername());
        }
        if (session != null) {
            session.removeAttribute(MyConfig.USER_SESSION_NAME);
            session.invalidate();
        }
        return "redirect:/admin/login";
    }

    @Override
    public String list(ModelMap modelMap, HttpServletRequest request, RedirectAttributes redrAtt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity getData(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String createView(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String createProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editView(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<AngularModel<ResponResult>> delete(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
