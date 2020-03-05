/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity;

//import gk.myname.vn.entity.OptionTelco;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.htc.monitor.common.Tool;
import vn.htc.monitor.entity.rest.RequestMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author @author LienHoa(CongNX)
 */
public class PriceTelco {
    static final Logger logger = Logger.getLogger(RequestMessage.class);
    static final ObjectMapper mapper = new ObjectMapper();
    private int group0Price;
    private int group1Price;
    private int group2Price;
    private int group3Price;
    private int group4Price;
    private int group5Price;
    private int group6Price;
    private int group7Price;
    private int group8Price;
    private int group9Price;
    public PriceTelco(){
        group0Price = 0;
        group1Price = 0;
        group2Price = 0;
        group3Price = 0;
        group4Price = 0;
        group5Price = 0;
        group6Price = 0;
        group7Price = 0;
        group8Price = 0;
        group9Price = 0;
    }
    
    public static PriceTelco json2Objec(String str) {
        PriceTelco result = null;
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            result = mapper.readValue(str, PriceTelco.class);
            if (result == null) {
                result = new PriceTelco();
            }
        } catch (Exception e) {
            result = new PriceTelco();
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }
    
    public String toJson() {
        try {
            mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            String jsonInString = mapper.writeValueAsString(this);
            return jsonInString;
        } catch (JsonProcessingException e) {
            logger.error(Tool.getLogMessage(e));
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }

    public int getGroup0Price() {
        return group0Price;
    }

    public void setGroup0Price(int group0Price) {
        this.group0Price = group0Price;
    }

    public int getGroup1Price() {
        return group1Price;
    }

    public void setGroup1Price(int group1Price) {
        this.group1Price = group1Price;
    }

    public int getGroup2Price() {
        return group2Price;
    }

    public void setGroup2Price(int group2Price) {
        this.group2Price = group2Price;
    }

    public int getGroup3Price() {
        return group3Price;
    }

    public void setGroup3Price(int group3Price) {
        this.group3Price = group3Price;
    }

    public int getGroup4Price() {
        return group4Price;
    }

    public void setGroup4Price(int group4Price) {
        this.group4Price = group4Price;
    }

    public int getGroup5Price() {
        return group5Price;
    }

    public void setGroup5Price(int group5Price) {
        this.group5Price = group5Price;
    }

    public int getGroup6Price() {
        return group6Price;
    }

    public void setGroup6Price(int group6Price) {
        this.group6Price = group6Price;
    }

    public int getGroup7Price() {
        return group7Price;
    }

    public void setGroup7Price(int group7Price) {
        this.group7Price = group7Price;
    }

    public int getGroup8Price() {
        return group8Price;
    }

    public void setGroup8Price(int group8Price) {
        this.group8Price = group8Price;
    }

    public int getGroup9Price() {
        return group9Price;
    }

    public void setGroup9Price(int group9Price) {
        this.group9Price = group9Price;
    }
    
    
}
