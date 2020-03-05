/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.controllers.backend;

import java.util.ArrayList;
import java.util.HashMap;
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
import vn.htcjsc.brandname.voice.services.MessageMonitorServiceIF;
import vn.htcjsc.brandname.voice.model.MessageMonitor;
import vn.htcjsc.brandname.voice.model.ext.AngularModel;
import vn.htcjsc.brandname.voice.model.ext.ResponResult;

/**
 *
 * @author hoan
 */
@Controller
@RequestMapping("/admin/message-monitor")
public class MessageMonitorController extends AbstractBackEnConst {

    private final String REDIRECT_VIEW = "redirect:/admin/message-monitor/list";
    private final String TABLE = "message";
    private final String MODEL_NAME = "messageMonitor";
    @Autowired
    MessageMonitorServiceIF MessageMonitorSV;

    @GetMapping("/list")
    @Override
    public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
//        if (accService.checkAccess(request)) {
//            modelMap.put(MODEL_NAME, cateSV.listCategories());
//            modelMap.put(TITLE, "Quản lý Chuyên mục");
//            return "list-categories";
//        } else {
//            ResponResult result = new UserActionLog(TABLE, 0, ACTION.VIEW, RESULT.NO_RIGHT, "Bạn không có quyền truy cập Module: Quản lý chuyên mục").logAction(request);
//            redrAtt.addFlashAttribute(RESP_NAME, result);
//            return ADMIN_INDEX_PAGE;
//        }
        model.put(TITLE, LANG.get("messageMonitor.title.list"));
        return "message-monitor";
    }

    @PostMapping("/rest/data")
    @Override
    public ResponseEntity<AngularModel<MessageMonitor>> getData(HttpServletRequest request) {
        AngularModel<MessageMonitor> ngModel = new AngularModel<>();

        int status = HttpUtil.getInt(request, "status", MyConfig.STATUS.ALL.val);
        String key = HttpUtil.getString(request, "key");
        String ip = HttpUtil.getString(request, "ip");
        String sendto = HttpUtil.getString(request, "sendto");
        int maxRow = HttpUtil.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = HttpUtil.getInt(request, "crPage", 1);
        ArrayList<MessageMonitor> listData = MessageMonitorSV.view(crPage, maxRow, key, ip, status);
        int totalRow = MessageMonitorSV.count(key, ip,sendto, status);
        ngModel.setDatas(listData);
        ngModel.setTotalRow(totalRow);
//        HashMap<String, Boolean> cate = accService.checkRight(request);
//        ngModel.setRoles(cate);
        if (listData == null || listData.isEmpty()) {
            ngModel.setResult(new ResponResult(RESULT.FAIL, "Danh sách chuyên mục trống"));
        } else {
            ngModel.setResult(new ResponResult(RESULT.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);

    }

//    @GetMapping("/create")
//    @Override
//    public String createView(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
//        if (accService.checkAccess(request)) {
//            model.addAttribute(MODEL_NAME, new Categories());
//            model.put("listParent", cateSV.listCateParent("string"));
//            model.put("listImage", folderSV.listImage());
//            model.put(TITLE, "Tạo mới Chuyên mục");
//            return "add-categories";
//        } else {
//            ResponResult result = new UserActionLog(TABLE, 0, ACTION.CREATE, RESULT.NO_RIGHT, "Bạn không có quyền thêm mới chuyên mục").logAction(request);
//            redrAtt.addFlashAttribute(RESP_NAME, result);
//            return ADMIN_INDEX_PAGE;
//        }
//    }
//
//    @PostMapping("/create")
//    @Override
//    public String createProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
//        if (accService.checkAccess(request)) {
//            model.put("listParent", cateSV.listCateParent());
//            model.put("listImage", folderSV.listImage());
//            String name = HttpUtil.getString(request, "name");
//            String description = HttpUtil.getString(request, "description");
//            int num = HttpUtil.getInt(request, "num");
//            String icon = HttpUtil.getString(request, "icon");
//            String parentName[] = request.getParameterValues("parentName");
//            System.out.println("danh sach" + parentName);
//            int status = HttpUtil.getInt(request, "status");
//            String create_by = accService.getUserName(request);
//            Categories cate = new Categories();
//            cate.setName(name);
//            cate.setDescription(description);
//            cate.setNum(num);
//            cate.setIcon(icon);
//            cate.setParentName(parentName[0]);
//            cate.setStatus(status);
//            cate.setCreatedBy(create_by);
//            int id = cateSV.create(cate);
////            ArrayList<Agency> allAg = agencyService.getAll();
//            if (id > 0) {
//                ResponResult result = new UserActionLog(TABLE, id, ACTION.CREATE, RESULT.SUCCESS, "Thêm mới chuyên mục thành công").logAction(request);
//                redrAtt.addFlashAttribute(RESP_NAME, result);
//                return REDIRECT_VIEW;
//            } else {
//                model.addAttribute(RESP_NAME, new ResponResult(RESULT.FAIL, "Thêm mới chuyên mục thất bại"));
//                model.addAttribute(MODEL_NAME, cate);
//                return "add-categories";
//            }
//
//        } else {
//            ResponResult result = new UserActionLog(TABLE, 0, ACTION.CREATE, RESULT.NO_RIGHT, "Bạn không có quyền truy cập Module: thêm mới chuyên mục").logAction(request);
//            redrAtt.addFlashAttribute(RESP_NAME, result);
//            return ADMIN_INDEX_PAGE;
//        }
//    }
//
//    // UPDATE
//    @GetMapping("/edit")
//    @Override
//    public String editView(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
//        int id = HttpUtil.getInt(request, "id");
//        if (accService.checkAccess(request)) {
//            Categories cate = cateSV.findById(id);
//            model.put(TITLE, "Cập nhật chuyên mục");
//            model.put("listParent", cateSV.listCateParent("string"));
//            if (cate != null) {
//                model.addAttribute(MODEL_NAME, cate);
//                model.put("imge", cate.getIcon());
//                return "edit-categories";
//            } else {
//                ResponResult result = new UserActionLog(TABLE, id, ACTION.UPDATE, RESULT.FAIL, "Không tìm thấy chuyên mục cần sửa").logAction(request);
//                redrAtt.addFlashAttribute(RESP_NAME, result);
//                return REDIRECT_VIEW;
//            }
//        } else {
//            ResponResult result = new UserActionLog(TABLE, id, ACTION.UPDATE, RESULT.FAIL, "Bạn không có quyền truy cập Module: sửa chuyên mục").logAction(request);
//            redrAtt.addFlashAttribute(RESP_NAME, result);
//            return ADMIN_INDEX_PAGE;
//        }
//    }
//
//    @PostMapping("/edit")
//    @Override
//    public String editProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
//        int id = HttpUtil.getInt(request, "id");
//        model.put("listParent", cateSV.listCateParent());
//        model.put("listImage", folderSV.listImage());
//        if (accService.checkAccess(request)) {
//            Categories cate = cateSV.findById(id);
//            if (cate != null) {
//                String name = HttpUtil.getString(request, "name");
//                String description = HttpUtil.getString(request, "description");
//                int num = HttpUtil.getInt(request, "num");
//                String icon = HttpUtil.getString(request, "icon");
//                String parentName = HttpUtil.getString(request, "parentName");
//                int status = HttpUtil.getInt(request, "status");
//                String updated_by = accService.getUserName(request);
//                cate.setName(name);
//                cate.setDescription(description);
//                cate.setNum(num);
//                cate.setIcon(icon);
//                cate.setParentName(parentName);
//                cate.setStatus(status);
//                cate.setUpdatedBy(updated_by);
//                Categories oldVal = cateSV.update(cate);
//                if (oldVal != null) {
//                    ResponResult result = new UserActionLog(TABLE, cate.getId(), ACTION.UPDATE, RESULT.SUCCESS, "Cập nhật Chuyên mục thành công", oldVal).logAction(request);
//                    redrAtt.addFlashAttribute(RESP_NAME, result);
//                } else {
//                    ResponResult result = new UserActionLog(TABLE, cate.getId(), ACTION.UPDATE, RESULT.FAIL, "Cập nhật chuyên mục thất bại").logAction(request);
//                    model.addAttribute(RESP_NAME, result);
//                    model.addAttribute(MODEL_NAME, cate);
//                    return "edit-categories";
//                }
//            } else {
//                ResponResult result = new UserActionLog(TABLE, id, ACTION.UPDATE, RESULT.FAIL, "Không tìm thấy chuyen mục cần sửa").logAction(request);
//                redrAtt.addFlashAttribute(RESP_NAME, result);
//            }
//            return REDIRECT_VIEW;
//        } else {
//            ResponResult result = new UserActionLog(TABLE, id, ACTION.UPDATE, RESULT.NO_RIGHT, "Bạn không có quyền cập nhật chuyên mục").logAction(request);
//            redrAtt.addFlashAttribute(RESP_NAME, result);
//            return ADMIN_INDEX_PAGE;
//        }
//    }
//
//    @PostMapping("/rest/delete")
//    @Override
//    public ResponseEntity delete(HttpServletRequest request) {
//        int id = HttpUtil.getInt(request, "id");
//        String deleted_by = accService.getUserName(request);
//        AngularModel<ResponResult> ngmodel = new AngularModel<>();
//        if (accService.checkAccess(request)) {
//            Categories oldVal = cateSV.delete(id, deleted_by);
//            if (oldVal != null) {
//                ResponResult result = new UserActionLog(TABLE, id, ACTION.DEL, RESULT.SUCCESS, "Xóa Chuyên mục thành công", oldVal).logAction(request);
//                ngmodel.setResult(result);
//                return new ResponseEntity<>(ngmodel, HttpStatus.OK);
//            } else {
//                ResponResult result = new UserActionLog(TABLE, id, ACTION.DEL, RESULT.NO_RIGHT, "Xóa Chuyên mục thất bại").logAction(request);
//                ngmodel.setResult(result);
//                return new ResponseEntity<>(ngmodel, HttpStatus.OK);
//            }
//        } else {
//            ResponResult result = new UserActionLog(TABLE, id, ACTION.DEL, RESULT.NO_RIGHT, " không có quyền xóa Chuyên mục").logAction(request);
//            ngmodel.setResult(result);
//            return new ResponseEntity<>(ngmodel, HttpStatus.OK);
//        }
//    }
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
    public ResponseEntity delete(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
