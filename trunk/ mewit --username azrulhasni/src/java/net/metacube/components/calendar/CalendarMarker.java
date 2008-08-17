/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.metacube.components.calendar;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author Azrul
 */
public class CalendarMarker {
    private String id;
    private String markerText = null;
    private Date markerDate = null;

   

    public String getMarkerText() {
        return markerText;
    }

    public void setMarkerText(String markerText) {
        this.markerText = markerText;
    }

    public Date getMarkerDate() {
        return markerDate;
    }

    public void setMarkerDate(Date markerDate) {
        this.markerDate = markerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        CalendarMarker m = (CalendarMarker)o;
        return this.id.equals(m.getId());
        
    }
    
}
