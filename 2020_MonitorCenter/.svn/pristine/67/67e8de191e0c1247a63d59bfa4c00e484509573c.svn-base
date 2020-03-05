/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.controllers.backend;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.htcjsc.brandname.voice.commons.Md5;
import vn.htcjsc.brandname.voice.commons.HttpUtil;
import vn.htcjsc.brandname.voice.commons.Tool;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.model.SysAccount;
import vn.htcjsc.brandname.voice.model.ext.AngularModel;
import vn.htcjsc.brandname.voice.model.ext.ResponResult;

/**
 *
 * @author Private
 */
@Controller
@RequestMapping("/admin/sys-account")
public class SysAccountController extends AbstractBackEnConst {

    private final String REDIRECT_VIEW = "redirect:/admin/sys-account/list";
    private final String TABLE = "ACCOUNTS";
    private final String MODEL_NAME = "sysAcc";

    @GetMapping("/list")
    @Override
    public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        model.put(TITLE, LANG.get("sysAccount.title.list"));
        return "sys-account";
    }

    @PostMapping("/rest/data")
    public ResponseEntity<AngularModel<SysAccount>> getData(HttpServletRequest request) {
        AngularModel<SysAccount> ngModel = new AngularModel<>();
        String key = HttpUtil.getString(request, "key");
        String phone = HttpUtil.getString(request, "phone");
        String email = HttpUtil.getString(request, "email");
        int maxRow = HttpUtil.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = HttpUtil.getInt(request, "crPage", 1);
        int status = HttpUtil.getInt(request, "status", MyConfig.STATUS.ALL.val);
        ArrayList<SysAccount> listData = accService.view(crPage, maxRow, key, phone, email, status);
        int totalRow = accService.count(key, phone, email, status);
        //--
        ngModel.setDatas(listData);
        ngModel.setTotalRow(totalRow);
        if (listData == null || listData.isEmpty()) {
            ngModel.setResult(new ResponResult(RESULT.FAIL, "Danh sách tài khoản trống"));
        } else {
            ngModel.setResult(new ResponResult(RESULT.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    // Add Accounts
    @GetMapping("/create")
    @Override
    public String createView(ModelMap modelMap, HttpServletRequest request, RedirectAttributes redrAtt) {
        SysAccount account = new SysAccount();
        modelMap.put(MODEL_NAME, account);
        modelMap.put(TITLE, LANG.get("sysAccount.title.add"));
        return "sys-account-add";
    }

    @PostMapping("/create")
    @Override
    public String createProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        String username = HttpUtil.getString(request, "username");
        String password = HttpUtil.getString(request, "password");
        String full_name = HttpUtil.getString(request, "full_name");
        String description = HttpUtil.getString(request, "description");
        String phone = HttpUtil.getString(request, "phone");
        String email = HttpUtil.getString(request, "email");
        String telegram = HttpUtil.getString(request, "telegram");
        String voice = HttpUtil.getString(request, "voice");
        String address = HttpUtil.getString(request, "address");
        int status = HttpUtil.getInt(request, "status", MyConfig.STATUS.LOCK.val);
        //--
        SysAccount account = new SysAccount();
        account.setUsername(username);
        account.setPassword(password);
        account.setFull_name(full_name);
        account.setDescription(description);
        account.setPhone(phone);
        account.setEmail(email);
        account.setTelegram(telegram);
        account.setVoice(voice);
        account.setAddress(address);
        account.setStatus(status);
        //---
        // TODO Chua check các lỗi tường mình như tài khoản tồn tại, Email tồn tại ....
        model.addAttribute("sysAcc", account);
        SysAccount userLogin = accService.getSysAccountLogin(request);
        account.setCreate_by(userLogin.getUsername());
        int id = accService.create(account);
        if (id > 0) {
            return REDIRECT_VIEW;
        } else {
            model.put(MODEL_NAME, account);
            return "sys-account-add";
        }

    }

    // -- Edit SysAccount
    @GetMapping("/edit")
    @Override
    public String editView(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int id = HttpUtil.getInt(request, "id");
        SysAccount acc = accService.findById(id);
        model.put(TITLE, LANG.get("sysAccount.title.edit"));
        if (acc != null) {
            model.addAttribute(MODEL_NAME, acc);
            return "sys-account-edit";
        } else {
            return REDIRECT_VIEW;
        }
    }

    @PostMapping("/edit")
    @Override
    public String editProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int id = HttpUtil.getInt(request, "id");
        SysAccount account = accService.findById(id);
        if (account != null) {
            String username = HttpUtil.getString(request, "username");
            String password = HttpUtil.getString(request, Md5.encryptMD5("password"));
            String fullName = HttpUtil.getString(request, "fullName");
            String phone = HttpUtil.getString(request, "phone");
            String email = HttpUtil.getString(request, "email");
            String address = HttpUtil.getString(request, "address");
            int status = HttpUtil.getInt(request, "status", MyConfig.STATUS.LOCK.val);
            //--
            account.setUsername(username);
            account.setPassword(Md5.encryptMD5(password));
            account.setFull_name(fullName);
            account.setPhone(phone);
            account.setEmail(email);
            account.setAddress(address);
            account.setStatus(status);
            model.addAttribute(MODEL_NAME, account);
            SysAccount oldVal = accService.update(account);
            if (oldVal != null) {
                redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Cập nhật tài khoản thành công"));
            } else {
                model.put(RESP_NAME, new ResponResult(RESULT.FAIL, "Cập nhật tài khoản thất bại"));
                model.put(MODEL_NAME, account);
                return "sys-account-edit";
            }
        } else {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.FAIL, "Không tìm thấy tài khoản cần sửa"));
        }
        return REDIRECT_VIEW;

    }

    @PostMapping("/updateInfo")
    public String updateInfoProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int accId = HttpUtil.getInt(request, "accId");
        SysAccount account = accService.findById(accId);
        if (account != null) {
            String password = HttpUtil.getString(request, "password");
            String newPassword = HttpUtil.getString(request, "newPassword");
            String reNewPassword = HttpUtil.getString(request, "reNewPassword");
            String fullName = HttpUtil.getString(request, "fullName");
            String phone = HttpUtil.getString(request, "phone");
            String email = HttpUtil.getString(request, "email");
            String address = HttpUtil.getString(request, "address");
            //--
            if (!account.getPassword().equals(Md5.encryptMD5(password))) {
                ResponResult result = new ResponResult(RESULT.FAIL, "Mật khẩu cũ không đúng");
                model.put(RESP_NAME, result);
                model.put(MODEL_NAME, account);
                return "sys-account-updateInfo";
            }
            if (!newPassword.equals(reNewPassword)) {
                ResponResult result = new ResponResult(RESULT.FAIL, "gõ lại mật khẩu mới không khớp");
                model.put(RESP_NAME, result);
                model.put(MODEL_NAME, account);
                return "sys-account-updateInfo";
            }
            //--
            account.setPassword(newPassword);
            account.setFull_name(fullName);
            account.setPhone(phone);
            account.setEmail(email);
            account.setAddress(address);
            model.put(MODEL_NAME, account);
            SysAccount oldVal = accService.update(account);
            if (oldVal != null) {
                if (!Tool.checkNull(newPassword)) {
                    return "redirect:/admin/logout";
                }
                redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Cập nhật tài khoản thành công"));
                return REDIRECT_VIEW;
            } else {
                model.put(RESP_NAME, new ResponResult(RESULT.FAIL, "Cập nhật tài khoản thất bại"));
                model.put(MODEL_NAME, account);
                return "sys-account-updateInfo";
            }
        } else {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.FAIL, "Không tìm thấy tài khoản cần sửa"));
        }
        return REDIRECT_VIEW;
    }

    @PostMapping("/rest/deleteForEver")
    public String deleteForEver(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int id = HttpUtil.getInt(request, "id");
        AngularModel<ResponResult> ngmodel = new AngularModel<>();
        SysAccount oldVal = accService.deleteForEver(id);
        if (oldVal != null) {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Xóa tài khoản thành công"));
        } else {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Xóa tài khoản thất bại"));
        }
        return REDIRECT_VIEW;
    }

    @PostMapping("/rest/delete")
    @Override
    public ResponseEntity<ResponResult> delete(HttpServletRequest request) {
        int id = HttpUtil.getInt(request, "id");
        return null;
    }
}
