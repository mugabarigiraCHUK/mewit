package net.metacube.components.calendar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import java.util.Set;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.VariableResolver;
import javax.faces.event.ActionEvent;
import javax.faces.render.Renderer;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.azrul.epice.db4o.daoimpl.DB4OItemDAO;
import org.azrul.epice.dao.ItemDAO;

/**
 * Calendar renderer.
 *
 * @author <a href="mailto:marcel dot may at consol de">Marcel May</a>
 */
public class CalendarRenderer extends Renderer {

    /** Logging. */
    private static Log log = LogFactory.getLog(CalendarRenderer.class);
    /** The form number key. */
    public static final String FORM_NUMBER_ATTR = "com.sun.faces.FormNumber";
    /** The date format. */
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("ddMMyyyy");
    //private ItemDAO itemDAO = new DB4OItemDAO();

    /**
     * Default constructor.
     */
    public CalendarRenderer() {
        super();
    }

    /**
     * Decodes the request and configures the UICalendar.
     *
     * @param context the faces context.
     * @param theComponent the calendar component.
     */
    public void decode(final FacesContext context, final UIComponent theComponent) {
        UICalendar uiCal = (UICalendar) theComponent;
        String clientId = uiCal.getClientId(context);
        Map requestParameterMap = context.getExternalContext().getRequestParameterMap();

        // Set component date from request date.
        String curDate = (String) requestParameterMap.get(clientId + "_curDate");
        try {
            if (!curDate.equals("")) {
                uiCal.setCurrentDate(mDateFormat.parse(curDate));
            }
            if (log.isDebugEnabled()) {
                log.debug("New date is " + curDate);
            }
        } catch (Exception ex) {
            log.error("Can not parse " + curDate + ", using current date.", ex);
        // TODO: error handling
        }

        // Fire action for registered component
        uiCal.queueEvent(new ActionEvent(uiCal));
    }

    /**
     * Renders the UICalendar.
     *
     * @param context the faces context.
     * @param theComponent the calendar component.
     * @throws IOException on io error.
     */
    public void encodeEnd(final FacesContext context, final UIComponent theComponent) throws IOException {

        ResponseWriter writer = context.getResponseWriter();
        UICalendar uiCal = (UICalendar) theComponent;
        String clientId = uiCal.getClientId(context);
        Date curDate = uiCal.getCurrentDate();
        if (log.isDebugEnabled()) {
            log.debug("Current date is " + curDate);
        }
        Locale locale = context.getExternalContext().getRequestLocale();

        VariableResolver variableResolver = context.getApplication().getVariableResolver();
        CalendarMarkerCollections calMarkers = (CalendarMarkerCollections) variableResolver.resolveVariable(context, "calMarkers");


        // Header
        writer.write("<table border=\"1\" cellpadding=\"0\" align=\"center\">");
        writer.write(getHiddenFields(clientId));

        // Body
        int formNumber = getFormNumber(getForm(uiCal));
        writeMonthSelection(locale, writer,
                clientId, curDate, formNumber);
        writeDaySelection(locale, writer,
                clientId, curDate, formNumber, calMarkers);

        // Footer
        writer.write("</table>");
    }

    //
    // Helper methods
    //
    /**
     * Writes the day selection HTML code.
     *
     * @param theLocale the current locale.
     * @param writer the output writer.
     * @param clientId the client id.
     * @param curDate the current date.
     * @param theFormNumber the form number.
     * @throws IOException on write error.
     */
    private void writeDaySelection(final Locale theLocale,
            final ResponseWriter writer,
            final String clientId,
            final Date curDate,
            final int theFormNumber,
            final CalendarMarkerCollections calMarkers) throws IOException {
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(curDate);
        int currentMonth = currentCal.get(Calendar.MONTH);
        int currentDay = currentCal.get(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = Calendar.MONDAY;
        int lastDayOfWeek = Calendar.SUNDAY;


        // Render day names.
        writer.write("<tr>");
        String[] weekDayNames = CalendarUtil.initWeekDayNames(theLocale);
        for (int i = 0; i < 7; i++) {
            writer.write("<th bgcolor='#BEBEBE'>");
            writer.write(weekDayNames[i % 7]);
            writer.write("</th>");
        }
        writer.write("</tr>");


        Iterator iter = DateUtils.iterator(curDate, DateUtils.RANGE_MONTH_MONDAY);
        GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
        gcal.setTime(new Date());
        int today = gcal.get(GregorianCalendar.DAY_OF_MONTH);
        while (iter.hasNext()) {
            currentCal = (Calendar) iter.next();
            int dayOfWeek = currentCal.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = currentCal.get(Calendar.DAY_OF_MONTH);
            int month = currentCal.get(Calendar.MONTH);


            if (dayOfWeek == firstDayOfWeek) {
                writer.write("<tr>");
            }

            // Current day
            if (currentDay == dayOfMonth && currentMonth == month) {
                writer.write("<td bgcolor='#6699FF'>"); // blueish

            } // Weekend
            else if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                writer.write("<td bgcolor='#CECECE'>"); // greyish

            } // Default
            else {
                writer.write("<td>");
            }
            Set<CalendarMarker> markers = new HashSet<CalendarMarker>();
            if (calMarkers != null) {
                markers = calMarkers.getMarkersOnDate(currentCal.getTime());
            }
            if (currentMonth == month) {
                writer.write("<a ");
                writer.write(getAnchorAttrs(clientId, currentCal.getTime(), theFormNumber));
                if (dayOfMonth == today) {
                    writer.write("<font color='#660000'><b>");
                    writer.write("{" + Integer.toString(dayOfMonth) + "}");
                    writer.write("</b></font>");
                    for (CalendarMarker m : markers) {
                        writer.write("<font color='#DC143C'><b>");
                        writer.write(m.getMarkerText());
                        writer.write("</b></font>");
                    }
                    writer.write("</b></a>");

                } else if (currentDay == dayOfMonth) {
                    writer.write("><b>");
                    writer.write(Integer.toString(dayOfMonth));
                    for (CalendarMarker m : markers) {
                        writer.write("<font color='#DC143C'><b>");
                        writer.write(m.getMarkerText());
                        writer.write("</b></font>");
                    }
                    writer.write("</b></a>");
                } else {
                    writer.write(">");
                    writer.write(Integer.toString(dayOfMonth));
                    for (CalendarMarker m : markers) {
                        writer.write("<font color='#DC143C'><b>");
                        writer.write(m.getMarkerText());
                        writer.write("</b></font>");
                    }
                    writer.write("</a>");
                }
            } else {
                writer.write("<font color='#A0A0A0'>");
                writer.write(Integer.toString(dayOfMonth));
                for (CalendarMarker m : markers) {
                    writer.write("<font color='#DC143C'><b>");
                    writer.write(m.getMarkerText());
                    writer.write("</b></font>");
                }
                writer.write("</font>");
            }

            writer.write("</td>\n");
            if (dayOfWeek == lastDayOfWeek) {
                writer.write("</tr>\n");
            }
        }
    }

    /**
     * Writes the HTML current month selection.
     *
     * @param theLocale the locale.
     * @param writer the writer.
     * @param clientId the client id.
     * @param curDate the current date.
     * @param theFormNumber the form number.
     * @throws IOException if write fails.
     */
    private void writeMonthSelection(final Locale theLocale,
            final ResponseWriter writer,
            final String clientId, final Date curDate, final int theFormNumber)
            throws IOException {
        writer.write("<tr><th colspan='2'>");
        writer.write("<a " + getAnchorAttrs(clientId, CalendarUtil.prevMonth(curDate),
                theFormNumber) + ">");
        writer.write("&#x003C;</a></th><th colspan='3'>");
        writer.write(CalendarUtil.getMonth(theLocale, curDate));
        writer.write("</th><th colspan='2'>");
        writer.write("<a " + getAnchorAttrs(clientId, CalendarUtil.nextMonth(curDate),
                theFormNumber) + ">");
        writer.write("&#x003E;</a></th><tr>");

        writer.write("</tr>");
    }

    /**
     * Builds and returns the string consisting of the attibutes for a
     * result set navigation link anchor.
     *
     * @param clientId the clientId of the enclosing UIComponent
     * @param curDate the current date.
     * @param theFormNumber the form number.
     * @return a String suitable for setting as the value of a navigation
     * href.
     */
    private String getAnchorAttrs(
            final String clientId,
            final Date curDate,
            final int theFormNumber) {
        String currentDate = mDateFormat.format(curDate);
        String result = "href=\"#\" " + "onmousedown=\"" + "document.forms[" + theFormNumber + "]['" + clientId + "_curDate'].value='" + currentDate + "'; " + "document.forms[" + theFormNumber + "].submit()\"";

        return result;
    }

    /**
     * Gets the hidden field HTML form input element.
     *
     * @param clientId the client id value for the field.
     * @return the HTML hidden field entry.
     */
    private String getHiddenFields(final String clientId) {
        return "<input type=\"hidden\" name=\"" + clientId + "_curDate\"/>";
    }

    /**
     * Gets the enclosing parent form UI component.
     *
     * @param theUICalendar the calendar instance.
     * @return the enclosing form.
     */
    protected UIForm getForm(final UICalendar theUICalendar) {
        UIComponent parent = theUICalendar.getParent();
        while (parent != null && !(parent instanceof UIForm)) {
            parent = parent.getParent();
        }
        return (UIForm) parent;
    }

    /**
     * Gets t he form number.
     *
     * @param form the enclosing form component.
     * @return the form id.
     */
    protected int getFormNumber(final UIForm form) {
        // If we don't have a form, return 0
        if (null == form) {
            return 0;
        }
        Integer formsInt = (Integer) form.getAttributes().get(FORM_NUMBER_ATTR);
        if (null == formsInt) {
            return 0;
        }
        return formsInt.intValue();
    }
}
