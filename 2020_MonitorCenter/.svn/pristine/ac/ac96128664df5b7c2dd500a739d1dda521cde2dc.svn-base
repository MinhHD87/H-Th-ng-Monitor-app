package vn.htc.monitor.entity.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.HashMap;

public class SMS {

    public static final HashMap<String, SMS> CACHE = new HashMap<>();

    static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String toString() {
        return "SMS{" + "phone=" + phone + ", user=" + user + ", pass=" + pass + ", tranId=" + tranId + ", brandName=" + brandName + ", dataEncode=" + dataEncode + ", sendTime=" + sendTime + ", telcoCode=" + telcoCode + '}';
    }

    String phone;
    String user;
    String pass;
    String tranId;
    String brandName;
    String dataEncode;
    String sendTime;
    String telcoCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDataEncode() {
        return dataEncode;
    }

    public void setDataEncode(String dataEncode) {
        this.dataEncode = dataEncode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getTelcoCode() {
        return telcoCode;
    }

    public void setTelcoCode(String telcoCode) {
        this.telcoCode = telcoCode;
    }

    public SMS() {
    }

    public SMS(String phone, String user, String pass, String tranId, String brandName, String dataEncode, String sendTime, String telcoCode) {
        this.phone = phone;
        this.user = user;
        this.pass = pass;
        this.tranId = tranId;
        this.brandName = brandName;
        this.dataEncode = dataEncode;
        this.sendTime = sendTime;
        this.telcoCode = telcoCode;
    }
    public static SMS json2Objec(String str) {
        SMS result = null;
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            result = mapper.readValue(str, SMS.class);
            if (result == null) {
                result = new SMS();
            }
        } catch (Exception e) {
            result = new SMS();
//            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public String toJson() {
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            String jsonInString = mapper.writeValueAsString(this);
            return jsonInString;
        } catch (JsonProcessingException e) {
//            logger.error(Tool.getLogMessage(e));
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }
}
