package net.metacube.components.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ResourceBundle;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Example model which can be registered for calendar action events.
 *
 * @author <a href="mailto:marcel dot may at consol de">Marcel May</a>
 * @todo think about a new calendar event?
 */
public class CalendarModel {
    /** Logging. */
    private static Log log = LogFactory.getLog(CalendarModel.class);
    /** The example date attribute. */
    private Date mDate;

    /**
     * Gets the date attribte.
     *
     * @return the date.
     */
    public Date getDate() {
        if (log.isDebugEnabled()) {
            log.debug("Getting date " + mDate);
        }
        if (mDate == null){
            mDate = new Date();
        }
        return mDate;
    }

    /**
     * Sets the date attribute.
     *
     * @param theDate the date attribute.
     */
    public void setDate(final Date theDate) {
        if (log.isDebugEnabled()) {
            log.debug("Setting date to " + theDate);
        }
        mDate = theDate;
    }

    /**
     * Action listener method.
     *
     * @param event the action event from an action source.
     */
    public void processCalendarEvent(final ActionEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("Processing action event " + event);
        }
        UICalendar uiCal = (UICalendar) event.getComponent();
        setDate(uiCal.getCurrentDate());
        if (log.isDebugEnabled()) {
            log.debug("New model date is " + getDate());
        }
        
    }
    
    public String getDateAsText(){
        ResourceBundle props = ResourceBundle.getBundle("epice");
        SimpleDateFormat sdf = new SimpleDateFormat(props.getString("SHORT_DATE_FORMAT"));
        return sdf.format(mDate);
    }
    
    public void setDateAsText(String dateTxt){
        //do nothing
    }
}
