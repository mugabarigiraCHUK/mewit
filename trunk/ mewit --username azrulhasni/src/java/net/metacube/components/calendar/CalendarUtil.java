package net.metacube.components.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Some helper methods for the calendar renderer.
 * 
 * @author mm
 */
final class CalendarUtil {
    /** Logging. */
    private static Log log = LogFactory.getLog(CalendarUtil.class);
    
    /**
     * Private constructor - this class can not get instantiated.
     */
    private CalendarUtil() {
        // Nothing.
    }

    public static String getMonthName(final Locale theLocale,
            Date theDate) {
        SimpleDateFormat monthYearFormat =
            new SimpleDateFormat("MMMMM yyyy", theLocale);
        return monthYearFormat.format( theDate );
    }
    
    /**
     * Computes an array of week days short names, beginning at monday.
     *  
     * This is an expensive operation.
     * 
     * @param theFacesContext
     * @return an string array of week day names in short format.
     */
    public static String[] initWeekDayNames(
            final Locale theLocale) { 
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE", theLocale);
        String[] weekNames = new String[7];
        Calendar calendar = Calendar.getInstance(theLocale);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        for(int i=0;i<weekNames.length;i++) {
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            weekNames[i] = dateFormat.format(calendar.getTime());
        }
        if (log.isDebugEnabled()) {
            log.debug("Initialized week day names for locale "+theLocale
                    + " to ["+weekNames[0] + " ... " + weekNames[6] + "]");
        }
        return weekNames;
    }

    /**
     * Gets the month year string.
     * 
     * @param theLocale the current locale.
     * @param theDate the date.
     * @return the month year string.
     */
    public static String getMonth(final Locale theLocale, final Date theDate) {
        SimpleDateFormat mMonthYearFormat =
            new SimpleDateFormat("MMMMM yyyy",theLocale);
        return mMonthYearFormat.format(theDate);
    }
    
    /**
     * @param theLocale
     * @return
     */
    public static DateFormat getDayDateFormat(final Locale theLocale) {
        return new SimpleDateFormat("EEE",theLocale);
    }

    /**
     * Subtracts one month from the date.
     *
     * @param theDate the date.
     */
    public static Date nextMonth(final Date theDate) {
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime( theDate );
        currentCal.add(Calendar.MONTH,1);
        return currentCal.getTime();
    }

    /**
     * Adds one month to the date.
     *
     * @param theDate the date.
     */
    public static Date prevMonth(final Date theDate) {
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime( theDate );
        currentCal.add(Calendar.MONTH,-1);
        return currentCal.getTime();
    }
}
