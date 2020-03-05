/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.controllers.backend;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.htcjsc.brandname.voice.bean.DateUtil;
import vn.htcjsc.brandname.voice.commons.HttpUtil;
import vn.htcjsc.brandname.voice.config.MyConfig;
import vn.htcjsc.brandname.voice.model.Monitor;
import vn.htcjsc.brandname.voice.model.ext.AngularModel;
import vn.htcjsc.brandname.voice.model.ext.ResponResult;

/**
 *
 * @author HTC-PC
 */
@Controller
@RequestMapping("/admin/chart")
public class ChartController extends AbstractBackEnConst{
    private final String REDIRECT_VIEW = "redirect:/admin/chart/list";
    private final String TABLE = "monitor";
    private final String MODEL_NAME = "sysMonitor";
    
    @GetMapping("/list")
    @Override
    public String list(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) {
        model.put(TITLE, "AppClient");
        return "chart";
    }
    
    @GetMapping("/view")
    public String view(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) throws ParseException {
        int id = HttpUtil.getInt(request, "id");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Monitor mon = chartService.findIdAndTime(id, timestamp);
        model.put(TITLE, "Biểu đồ");
        if (mon != null) {
            DateUtil dateutil = new DateUtil();
            String date2 = dateutil.format(date,dateutil.DATE_FORMAT);
            mon.setTimesreach(date2);
            model.addAttribute(MODEL_NAME, mon);
            return "view";
        } else {
            return REDIRECT_VIEW;
        }
    }
    
    @PostMapping("/view")
    public String viewProcess(ModelMap model, HttpServletRequest request, RedirectAttributes redrAtt) throws ParseException {
        int id = HttpUtil.getInt(request, "id");
        String datesreach = HttpUtil.getString(request, "datesreach");
        Monitor mon = null;
        DateFormat formatter = null;
        Date date = null;
        Timestamp timestamp = null;
        if (datesreach != null) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(datesreach);
        } else {
            date = new Date();
        }
            timestamp = new Timestamp(date.getTime());
            mon = chartService.findIdAndTime(id, timestamp);
        if (mon != null) {
            DateUtil dateutil = new DateUtil();
            String date2 = dateutil.format(date,dateutil.DATE_FORMAT);
            mon.setTimesreach(date2);
            model.addAttribute(MODEL_NAME, mon);
            return "view";
        } else {
            return REDIRECT_VIEW;
        }
    }

    @PostMapping("/rest/data")
    public ResponseEntity<AngularModel<Monitor>> getData(HttpServletRequest request) {
        AngularModel<Monitor> ngModel = new AngularModel<>();
        String key = HttpUtil.getString(request, "key");
        String ip = HttpUtil.getString(request, "ip");
        String pos = HttpUtil.getString(request, "pos");
        int maxRow = HttpUtil.getInt(request, "maxRow", MyConfig.ADMIN_MAX_ROW);
        int crPage = HttpUtil.getInt(request, "crPage", 1);
        int status = HttpUtil.getInt(request, "status", 127);
        ArrayList<Monitor> listData = chartService.view(crPage, maxRow, key, ip, pos, status);
        int totalRow = chartService.count(key, ip, pos, status);
        //--
        ngModel.setDatas(listData);
        ngModel.setTotalRow(totalRow);
        if (listData == null || listData.isEmpty()) {
            ngModel.setResult(new ResponResult(RESULT.FAIL, "Danh sách trống"));
        } else {
            ngModel.setResult(new ResponResult(RESULT.SUCCESS, ""));
        }
        return new ResponseEntity<>(ngModel, HttpStatus.OK);
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
    public ResponseEntity delete(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
