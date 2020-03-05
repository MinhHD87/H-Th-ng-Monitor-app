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
public class RequestNotifyMessage {

    static final Logger logger = Logger.getLogger(RequestNotifyMessage.class);
    static final ObjectMapper mapper = new ObjectMapper();
    
    String user;
    String pass;
    int type;
    String content;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
    
    public static void main(String[] args){
//        String testString = "{\"account\":\"congnx\",\"smsNumber\":\"3\"}";
//        RequestNotifyMessage objectMy = RequestNotifyMessage.toObject(testString);
//        System.out.println(objectMy.account);
//        System.out.println(objectMy.gpc_pe);
//        System.out.println(objectMy.vtePrice);
//        System.out.println(objectMy.smsNumber);
    }

    public RequestNotifyMessage() {
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
    
    public static RequestNotifyMessage toObject(String jsonStr) {
        RequestNotifyMessage result = null;
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            result = mapper.readValue(jsonStr, RequestNotifyMessage.class);
        } catch (IOException e) {
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    
    

}
