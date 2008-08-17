package net.metacube.components.calendar;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentTag;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CalendarTag is the tag handler class for <code>UICalendar</code>.
 *
 * @author <a href="mailto:marcel dot may at consol de">Marcel May</a>
 */
public class CalendarTag extends UIComponentTag {
    /** Logger. */
    private static Log log = LogFactory.getLog(CalendarTag.class);
    /** The action listener to invoke on date selection. */
    private String actionListener;

    /**
     *  Method reference to handle an action event generated as a result of
     *  clicking on a link that selects a new date.
     *
     * @param theActionListener the action listener reference.
     */
    public void setActionListener(final String theActionListener) {
        actionListener = theActionListener;
    }

    /**
     * Gets the component type.
     *
     * @see javax.faces.webapp.UIComponentTag#getComponentType()
     * @return the component type.
     */
    public String getComponentType() {
        return "Calendar";
    }

    /**
     * Gets the renderer type.
     *
     * @see javax.faces.webapp.UIComponentTag#getRendererType()
     * @return the renderer type.
     */
    public String getRendererType() {
        return "CalendarRenderer";
    }

    /**
     * Resets the tag.
     *
     * @see javax.servlet.jsp.tagext.Tag#release()
     */
    public void release() {
        super.release();
        actionListener = null;
    }

    /**
     * Configures the properties.
     *
     * @see javax.faces.webapp.UIComponentTag#setProperties(javax.faces.component.UIComponent)
     * @param component the component.
     */
    protected void setProperties(final UIComponent component) {
        super.setProperties(component);
        FacesContext context = FacesContext.getCurrentInstance();

        // Action Listener
        if (actionListener != null) {
            if (isValueReference(actionListener)) {
                Class[] args = {ActionEvent.class};
                MethodBinding mb =
                    context.getApplication().createMethodBinding(
                        actionListener, args);
                ((UICalendar) component).setActionListener(mb);
            }
            else {
              log.error("Invalid action listener " + actionListener);
              throw new javax.faces.FacesException(
                      "Invalid action listener " + actionListener);
            }
        }
    }
}
