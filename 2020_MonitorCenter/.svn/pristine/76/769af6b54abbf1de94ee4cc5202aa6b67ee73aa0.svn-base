/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.json.simple.JSONAware;

/**
 *
 * @author MinhKudo
 */
@XmlRootElement(name = "ObjectJson")
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjectJson implements JSONAware {

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ObjectJson() {
    }

    public ObjectJson(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Override
    public String toJSONString() {
        StringBuffer sb = new StringBuffer();

        sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn

        sb.append("\"code\":\"" + getCode() + "\""); // dòng này có nghĩa là
        // "id":"Giá_Trị"
        sb.append(","); // sau mỗi cặp key/value là một dấu phẩy

        sb.append("\"message\":\"" + getMessage() + "\"");

        sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn

        return sb.toString();
    }

//    public static void main(String[] args) {
//        ObjectJson student = new ObjectJson(1, "Duy hung");
//
//        String JSONResult = student.toJSONString();
//
//        System.out.println(JSONResult);
//    }
}
