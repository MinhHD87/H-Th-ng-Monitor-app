
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Private
 */
public class TestClass {

    public static void main(String[] args) {
        TimeZone zoneGMT = TimeZone.getTimeZone("GMT");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        simpleDateFormat.setTimeZone(zoneGMT);
//
        System.out.println("Time zone: " + zoneGMT.getID());
        System.out.println("default time zone: " + TimeZone.getDefault().getID());
//        System.out.println();
//
        Calendar calendar = Calendar.getInstance();
        Calendar calendarGMT = new GregorianCalendar();
        calendarGMT.setTimeZone(zoneGMT);

        System.out.println("calendar.getTime(): " + calendarGMT.getTime());
        System.out.println("GMT:     " + simpleDateFormat.format(calendar.getTime()));
        System.out.println("Default: " + calendar.getTime());
//--
        getTimestampGMT();
    }

    public static Timestamp getTimestampGMT() {
        Timestamp ts = null;
        Calendar clGMT = new GregorianCalendar();
        try {
            TimeZone zoneGMT = TimeZone.getTimeZone("GMT");
            clGMT.setTimeZone(zoneGMT);
            System.out.println("clGMT.getTime(): " + clGMT.getTime());
            ts = new Timestamp((clGMT.getTime()).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hour: "+clGMT.get(Calendar.HOUR_OF_DAY));
        System.out.println("ts:"+ts);
        return ts;
    }

    public static String getDefaultTimeZone() {
        String timeZone = "";
        try {
            timeZone = TimeZone.getDefault().getID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeZone;
    }

}
