/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import vn.htc.monitor.common.Tool;

/**
 *
 * @author HTC-PC
 */
public class SystemForm {
    
    public static void main(String[] args) {
        SystemForm systemForm = new SystemForm();
        systemForm.setId(1);
        systemForm.setName("1");
        systemForm.setIp("1");
        systemForm.setType(1);
        systemForm.setStatus(1);
        java.util.Date date = new java.util.Date(); 
        systemForm.setTimecheck(new Timestamp(date.getTime()));
        systemForm.setContent("111111111");
        
        String aaa = SystemForm.toStringJson(systemForm);
        System.out.println(aaa);
        SystemForm bbb = SystemForm.json2Object(aaa);
        System.out.println(bbb.toString());
        
    }
    
    final Logger logger = Logger.getLogger(SystemForm.class);
    public static final HashMap<String, SystemForm> CACHE = new HashMap<>();
    
    public static SystemForm json2Object(String str) {
        SystemForm result = new SystemForm();
        if (!Tool.checkNull(str)) {
            try {
                JSONObject obj = (JSONObject) JSONSerializer.toJSON(str);
                
                try {
                    int id = (int) obj.get("id");
                    result.setId(id);
                } catch (Exception e) {
                    Tool.debug("Route khong co id:");
                }   
                try {
                    String name = (String) obj.get("name");
                    result.setName(name);
                } catch (Exception e) {
                    Tool.debug("Route khong co name:");
                }   
                try {
                    String ip = (String) obj.get("ip");
                    result.setIp(ip);
                } catch (Exception e) {
                    Tool.debug("Route khong co ip:");
                }   
                try {
                    Timestamp timecheck = (Timestamp) obj.get("timecheck");
                    result.setTimecheck(timecheck);
                } catch (Exception e) {
                    Tool.debug("Route khong co timecheck:");
                }   
                try {
                    int type = (int) obj.get("type");
                    result.setType(type);
                } catch (Exception e) {
                    Tool.debug("Route khong co type:");
                }   
                try {
                    int status = (int) obj.get("status");
                    result.setStatus(status);
                } catch (Exception e) {
                    Tool.debug("Route khong co status:");
                }   
                try {
                    String content = (String) obj.get("content");
                    result.setContent(content);
                } catch (Exception e) {
                    Tool.debug("Route khong co content:");
                }            
            } catch (Exception e) {
                Tool.debug("String json Route table not valid");
            }
        }

        return result;
    }
    
    public static String toStringJson(SystemForm route) {
        if (route != null) {
            JSONObject obj = JSONObject.fromObject(route);
            String str = obj.toString();
            return str;
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "SystemForm{" + "logger=" + logger + ", id=" + id + ", name=" + name + ", ip=" + ip + ", timecheck=" + timecheck + ", type=" + type + ", status=" + status + ", content=" + content + '}';
    }
    
    private int id;
    private String name;
    private String ip;
    private Timestamp timecheck;
    private int type;
    private int status;
    private String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getTimecheck() {
        return timecheck;
    }

    public void setTimecheck(Timestamp timecheck) {
        this.timecheck = timecheck;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
