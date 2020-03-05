/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.htc.monitor.common.Tool;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 *
 * @author Company
 */
public class RequestMessage {

    static final Logger logger = Logger.getLogger(RequestMessage.class);
    static final ObjectMapper mapper = new ObjectMapper();
    String account;
    int smsNumber;
    String telcoCode;
    String action;
    int money;
    String security;
    int vtePrice;
    int gpcPrice;
    int vmsPrice;
    int vnmPrice;
    int blPrice;
    int ddgPrice;
    String gpc_pe;
    String vte_pe;
    String vms_pe;
    String vnm_pe;
    String bl_pe;
    String ddg_pe;
    int sms_quota;
    int monthly_price_active;
    int groupSMS;
    String messageID;
    String accAction;
    String notecomment;
    
    public static void main(String[] args){
        String testString = "{\"account\":\"congnx\",\"smsNumber\":\"3\"}";
        RequestMessage objectMy = RequestMessage.toObject(testString);
        System.out.println(objectMy.account);
        System.out.println(objectMy.gpc_pe);
        System.out.println(objectMy.vtePrice);
        System.out.println(objectMy.smsNumber);
    }

    public RequestMessage() {
        // Must have no-argument constructor
    }

    public String toJsonStr() {
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            String jsonInString = mapper.writeValueAsString(this);
            return jsonInString;
        } catch (JsonProcessingException e) {
            logger.error(Tool.getLogMessage(e));
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }
    
    public static RequestMessage toObject(String jsonStr) {
        RequestMessage result = null;
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            result = mapper.readValue(jsonStr, RequestMessage.class);
        } catch (IOException e) {
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(int smsNumber) {
        this.smsNumber = smsNumber;
    }

    public String getTelcoCode() {
        return telcoCode;
    }

    public void setTelcoCode(String telcoCode) {
        this.telcoCode = telcoCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String securty) {
        this.security = securty;
    }

    public int getVtePrice() {
        return vtePrice;
    }

    public void setVtePrice(int vtePrice) {
        this.vtePrice = vtePrice;
    }

    public int getGpcPrice() {
        return gpcPrice;
    }

    public void setGpcPrice(int gpcPrice) {
        this.gpcPrice = gpcPrice;
    }

    public int getVmsPrice() {
        return vmsPrice;
    }

    public void setVmsPrice(int vmsPrice) {
        this.vmsPrice = vmsPrice;
    }

    public int getVnmPrice() {
        return vnmPrice;
    }

    public void setVnmPrice(int vnmPrice) {
        this.vnmPrice = vnmPrice;
    }

    public int getBlPrice() {
        return blPrice;
    }

    public void setBlPrice(int blPrice) {
        this.blPrice = blPrice;
    }

    public String getGpc_pe() {
        return gpc_pe;
    }

    public void setGpc_pe(String gpc_pe) {
        this.gpc_pe = gpc_pe;
    }

    public String getVte_pe() {
        return vte_pe;
    }

    public void setVte_pe(String vte_pe) {
        this.vte_pe = vte_pe;
    }

    public String getVms_pe() {
        return vms_pe;
    }

    public void setVms_pe(String vms_pe) {
        this.vms_pe = vms_pe;
    }

    public String getVnm_pe() {
        return vnm_pe;
    }

    public void setVnm_pe(String vnm_pe) {
        this.vnm_pe = vnm_pe;
    }

    public String getBl_pe() {
        return bl_pe;
    }

    public void setBl_pe(String bl_pe) {
        this.bl_pe = bl_pe;
    }

    public int getSms_quota() {
        return sms_quota;
    }

    public void setSms_quota(int sms_quota) {
        this.sms_quota = sms_quota;
    }

    public int getMonthly_price_active() {
        return monthly_price_active;
    }

    public void setMonthly_price_active(int monthly_price_active) {
        this.monthly_price_active = monthly_price_active;
    }

    public int getGroupSMS() {
        return groupSMS;
    }

    public void setGroupSMS(int groupSMS) {
        this.groupSMS = groupSMS;
    }

    public int getDdgPrice() {
        return ddgPrice;
    }

    public void setDdgPrice(int ddgPrice) {
        this.ddgPrice = ddgPrice;
    }

    public String getDdg_pe() {
        return ddg_pe;
    }

    public void setDdg_pe(String ddg_pe) {
        this.ddg_pe = ddg_pe;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getAccAction() {
        return accAction;
    }

    public void setAccAction(String accAction) {
        this.accAction = accAction;
    }

    public String getNotecomment() {
        return notecomment;
    }

    public void setNotecomment(String notecomment) {
        this.notecomment = notecomment;
    }

    

}
