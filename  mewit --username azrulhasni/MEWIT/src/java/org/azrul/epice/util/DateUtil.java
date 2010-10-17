/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Azrul
 */
public class DateUtil {

    static final long ONE_HOUR = 60 * 60 * 1000L;

    public static long daysBetween(Date d1, Date d2) {
        return ((Math.abs(d2.getTime() - d1.getTime()) + ONE_HOUR) /
                (ONE_HOUR * 24));
    }

    public static void main(String[] args){
        try {
            SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            List<Date> days = getDatesBetweenTwoDates(sdfd.parse("2008-02-25 00:00:00 AM"), sdfd.parse("2008-03-10 00:00:00 AM"));
            for (Date day:days){
                System.out.println(sdfd.format(day));
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            boolean b = checkDateBetweenTwoDatesInclusive(sdf.parse("2008-03-01"),sdf.parse("2008-02-25"), sdf.parse("2008-03-10"));
            System.out.println("2008-03-01 is between 2008-02-25 and 2008-03-10:"+Boolean.toString(b));

            boolean b1 = checkDateBetweenTwoDatesInclusive(sdf.parse("2008-03-11"),sdf.parse("2008-02-25"), sdf.parse("2008-03-10"));
            System.out.println("2008-03-11 is between 2008-02-25 and 2008-03-10:"+Boolean.toString(b1));

            boolean b2 = checkDateBetweenTwoDatesInclusive(sdf.parse("2008-02-25"),sdf.parse("2008-02-25"), sdf.parse("2008-03-10"));
            System.out.println("2008-03-25 is between 2008-02-25 and 2008-03-10:"+Boolean.toString(b2));

            boolean b3 = checkDateBetweenTwoDatesInclusive(sdf.parse("2008-03-10"),sdf.parse("2008-02-25"), sdf.parse("2008-03-10"));
            System.out.println("2008-03-10 is between 2008-02-25 and 2008-03-10:"+Boolean.toString(b3));

        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Date> getDatesBetweenTwoDates(Date startDate, Date endDate) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        List<Date> days = new ArrayList<Date>();
        
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        while (cal.getTimeInMillis()<=endDate.getTime()) {
            days.add(cal.getTime());
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

     public static boolean checkDateBetweenTwoDatesInclusive( Date date2BChecked, Date startDate, Date endDate) {
        

       
        GregorianCalendar startCal = new GregorianCalendar();
        GregorianCalendar endCal = new GregorianCalendar();
        GregorianCalendar date2BCheckedCal = new GregorianCalendar();

        
        startCal.setTime(startDate);
        endCal.setTime(endDate);
        date2BCheckedCal.setTime(date2BChecked);



        startCal.set(Calendar.HOUR_OF_DAY, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);

        endCal.set(Calendar.HOUR_OF_DAY, 0);
        endCal.set(Calendar.MINUTE, 0);
        endCal.set(Calendar.SECOND, 0);
        endCal.set(Calendar.MILLISECOND, 0);

        date2BCheckedCal.set(Calendar.HOUR_OF_DAY, 0);
        date2BCheckedCal.set(Calendar.MINUTE, 0);
        date2BCheckedCal.set(Calendar.SECOND, 0);
        date2BCheckedCal.set(Calendar.MILLISECOND, 0);

        if (date2BCheckedCal.equals(endCal)){
            return true;
        }
        else if (date2BCheckedCal.equals(startCal)){
            return true;
        }
        else if (date2BCheckedCal.before(endCal) && date2BCheckedCal.after(startCal)){
            return true;
        } else {
            return false;
        }
        
    }

     public static Calendar getToday(){
            GregorianCalendar todayCal = new GregorianCalendar();

            todayCal.set(Calendar.HOUR_OF_DAY, 0);
            todayCal.set(Calendar.MINUTE, 0);
            todayCal.set(Calendar.SECOND, 0);
            todayCal.set(Calendar.MILLISECOND, 0);
            return todayCal;
     }
}
