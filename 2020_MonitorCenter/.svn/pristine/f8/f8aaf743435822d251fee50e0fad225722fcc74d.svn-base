
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class TimeZoneDemo {

    public static void main(String args[]) {

        // create default time zone object
        TimeZone timezonedefault = TimeZone.getDefault();

        // checking default time zone value          
        System.out.println("Default time zone is :\n" + timezonedefault);
//        Calendar tzCal = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
//        Timestamp tsNew = new Timestamp((tzCal.getTime()).getTime());
//        System.out.println(tsNew);
        test1();
    }

    public static void test1() {
        Timestamp ts = null;
        //--
        TimeZone timeZone1 = TimeZone.getTimeZone("Asia/Bangkok");
        TimeZone timeZone2 = TimeZone.getTimeZone("Asia/Hong_Kong");
        System.out.println("==>" + timeZone1.getID());
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone1);
//--
        long timeCPH = calendar.getTimeInMillis();
        ts = new Timestamp((calendar.getTime()).getTime());
        System.out.println("time Asia/Bangkok  = " + timeCPH);
        System.out.println("hour Asia/Bangkok  = " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Timestamp Asia/Bangkok: " + ts);
//--
        calendar.setTimeZone(timeZone2);

        long timeLA = calendar.getTimeInMillis();
        ts = new Timestamp((calendar.getTime()).getTime());
        System.out.println("time Asia/Hong_Kong   = " + timeLA);
        System.out.println("hour Asia/Hong_Kong   = " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Timestamp Asia/Hong_Kong: " + ts);
//        String[] arr = TimeZone.getAvailableIDs();
//        for (String one : arr) {
//            System.out.println(one);
//        }
    }
}
