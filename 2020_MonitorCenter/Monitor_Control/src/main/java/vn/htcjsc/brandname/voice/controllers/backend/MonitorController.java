package vn.htcjsc.brandname.voice.controllers.backend;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.htcjsc.brandname.voice.commons.HttpUtil;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.model.Monitor;
import vn.htcjsc.brandname.voice.model.ext.AngularModel;
import vn.htcjsc.brandname.voice.model.ext.ResponResult;
import vn.htcjsc.brandname.voice.services.MonitorService;

/**
 *
 * @author Phan Thanh Tùng
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorController extends AbstractBackEnConst {

    private final String REDIRECT_VIEW = "redirect:/admin/monitor/list";
    private final String TABLE = "MONITOR";
    private final String MODEL_NAME = "monDao";
    @Autowired
    MonitorService monService;

    @GetMapping("/list")
    @Override
    public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        model.put(TITLE, LANG.get("Monitor.title.list"));
        return "monitor";
    }

    @PostMapping("/rest/data")
    public ResponseEntity<AngularModel<Monitor>> getData(HttpServletRequest request) {
        AngularModel<Monitor> ngModel = new AngularModel<>();
        String name = HttpUtil.getString(request, "name");
        int parent = HttpUtil.getInt(request, "parent");
        String ip = HttpUtil.getString(request, "ip");
        String pos = HttpUtil.getString(request, "pos");
        int maxRow = HttpUtil.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = HttpUtil.getInt(request, "crPage", 1);
        ArrayList<Monitor> listData = monService.view(crPage, maxRow, name, parent, ip, pos);
        int totalRow = monService.count(name, parent, ip, pos);
        //--
        ngModel.setDatas(listData);
        ngModel.setTotalRow(totalRow);
        if (listData == null || listData.isEmpty()) {
            ngModel.setResult(new ResponResult(RESULT.FAIL, "Danh sách monitor trống"));
        } else {
            ngModel.setResult(new ResponResult(RESULT.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
    }

    @GetMapping("/create")
    @Override
    public String createView(ModelMap modelMap, HttpServletRequest request, RedirectAttributes redrAtt) {
        Monitor monitor = new Monitor();
        modelMap.put(MODEL_NAME, monitor);
        modelMap.put(TITLE, LANG.get("Tạo mới monitor"));
        return "monitor-add";
    }

    @PostMapping("/create")
    @Override
    public String createProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        String name = HttpUtil.getString(request, "name");
        String description = HttpUtil.getString(request, "description");
        String ip = HttpUtil.getString(request, "ip");
        String pos = HttpUtil.getString(request, "pos");
        String username = HttpUtil.getString(request, "username");
        String password = HttpUtil.getString(request, "password");
        int parent = HttpUtil.getInt(request, "parent");
        String phone = HttpUtil.getString(request, "phone");
        String email = HttpUtil.getString(request, "email");
        String telegram = HttpUtil.getString(request, "telegram");
        String voice = HttpUtil.getString(request, "voice");
        //--
        Monitor mon = new Monitor();
        mon.setName(name);
        mon.setDescription(description);
        mon.setIp(ip);
        mon.setPos(pos);
        mon.setUsername(username);
        mon.setPassword(password);
        mon.setParent(parent);
        mon.setPhone(phone);
        mon.setEmail(email);
        mon.setTelegram(telegram);
        mon.setVoice(voice);

        //---
        model.addAttribute("Monitor", mon);
        int id = monService.create(mon);
        if (id > 0) {
            return REDIRECT_VIEW;
        } else {
            model.put(MODEL_NAME, mon);
            return "monitor-add";
        }

    }

    @GetMapping("/edit")
    @Override
    public String editView(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int id = HttpUtil.getInt(request, "id");
        Monitor mon = monService.findById(id);
        model.put(TITLE, LANG.get("sysAccount.title.edit"));
        if (mon != null) {
            model.addAttribute(MODEL_NAME, mon);
            return "monitor-edit";
        } else {
            return REDIRECT_VIEW;
        }
    }

    @PostMapping("/edit")
    @Override
    public String editProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int id = HttpUtil.getInt(request, "id");
        Monitor mon = monService.findById(id);
        if (mon != null) {
            String name = HttpUtil.getString(request, "name");
            String description = HttpUtil.getString(request, "description");
            String ip = HttpUtil.getString(request, "ip");
            String pos = HttpUtil.getString(request, "pos");
            String username = HttpUtil.getString(request, "username");
            String password = HttpUtil.getString(request, "password");
            int parent = HttpUtil.getInt(request, "parent");
            String phone = HttpUtil.getString(request, "phone");
            String email = HttpUtil.getString(request, "email");
            String telegram = HttpUtil.getString(request, "telegram");
            String voice = HttpUtil.getString(request, "voice");
            //--
            mon.setName(name);
            mon.setDescription(description);
            mon.setIp(ip);
            mon.setPos(pos);
            mon.setUsername(username);
            mon.setPassword(password);
            mon.setParent(parent);
            mon.setPhone(phone);
            mon.setEmail(email);
            mon.setTelegram(telegram);
            mon.setVoice(voice);
            model.addAttribute(MODEL_NAME, mon);
            Monitor oldVal = monService.update(mon);
            if (oldVal != null) {
                redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Cập nhật monitor thành công"));
            } else {
                model.put(RESP_NAME, new ResponResult(RESULT.FAIL, "Cập nhật monitor thất bại"));
                model.put(MODEL_NAME, mon);
                return "monitor-edit";
            }
        } else {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.FAIL, "Không tìm thấy monitor cần sửa"));
        }
        return REDIRECT_VIEW;

    }

    @PostMapping("/rest/deleteForEver")
    public String deleteForEver(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        int id = HttpUtil.getInt(request, "id");
        AngularModel<ResponResult> ngmodel = new AngularModel<>();
        Monitor oldVal = monService.delete(id);
        if (oldVal != null) {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Xóa monitor thành công"));
        } else {
            redrAtt.addFlashAttribute(RESP_NAME, new ResponResult(RESULT.SUCCESS, "Xóa monitor thất bại"));
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
