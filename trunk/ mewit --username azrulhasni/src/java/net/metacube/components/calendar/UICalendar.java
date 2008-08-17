package net.metacube.components.calendar;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.component.UICommand;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Calendar component.
 *
 * @author <a href="mailto:marcel dot may at consol de">Marcel May</a>
 */
public class UICalendar extends UICommand {
    /** Logging. */
    private static Log log = LogFactory.getLog(UICalendar.class);
    /** Current date. */
    private Date mCurrentDate;

    /**
     * Default constructor.
     */
    public UICalendar() {
        super();
        // Initialize with current date.
        mCurrentDate = new Date();
    }

    /**
     * Sets the current date.
     *
     * @param theCurrentDate the current date.
     */
    public void setCurrentDate(final Date theCurrentDate) {
        mCurrentDate = theCurrentDate;
    }


    /**
     * Gets the current date.
     *
     * @return the current date.
     */
    public Date getCurrentDate() {
        return mCurrentDate;
    }

    /**
     * Stores the component state.
     *
     * @param theContext the faces context.
     * @return the object containing the serializable component state.
     */
    public Object saveState(final FacesContext theContext) {
        Object[] values = new Object[] {
            super.saveState(theContext),
            mCurrentDate
        };
        if (log.isDebugEnabled()) {
            log.debug("State saved.");
        }
        return values;
    }

    /**
     * Restores the component state.
     *
     * @param theContext the faces context.
     * @param theObject the object to restore from.
     */
    public void restoreState(final FacesContext theContext,
            final Object theObject) {
        Object[] values = (Object[]) theObject;
        super.restoreState(theContext, values[0]);
        mCurrentDate = (Date) values[1];
        if (log.isDebugEnabled()) {
            log.debug("State restored.");
        }
    }

    /**
     * Gets the component family type for choosing a renderer.
     *
     * @see javax.faces.component.UIComponent#getFamily()
     * @return the family type.
     */
    public String getFamily() {
        return "Calendar";
    }
}
