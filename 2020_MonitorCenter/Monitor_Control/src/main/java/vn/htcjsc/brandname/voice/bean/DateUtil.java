/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htcjsc.brandname.voice.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HTC-PC
 */
public class DateUtil {
    public static final String DATE_FORMAT_NOW = "dd/MM/yyyy HH:mm:ss";
 
    public static final String DATE_HH_MM = "HH:mm dd/MM/yyyy";
 
    public static final String DATE_HH_MM_PM = "HH:mm aa dd/MM/yyyy";
 
    public static final String DATE_HH_MM_SC = "dd/MM/yyyy (HH:mm a)";
 
    public static final String DATE_12H_PM = "dd/MM/yyyy (hh:mm a)";
 
    public static final String DATE_FORMAT_SIMPLE = "dd/MM/yyyy";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
 
    public static final String DATE_HOUR = "HH:mm";
 
    public static final String DATE_FORMAT_HH = "dd/MM/yyyy HH:mm";
 
    /** The Constant SECOND. */
    public static final long SECOND = 1000;
    /** The Constant MINUTE. */
    public static final long MINUTE = SECOND * 60;
    /** The Constant HOUR. */
    public static final long HOUR = MINUTE * 60;
    /** The Constant DAY. */
    public static final long DAY = HOUR * 24;
    
    public static String format(Date date, String format) {
        if (date == null) {
            return " ";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
