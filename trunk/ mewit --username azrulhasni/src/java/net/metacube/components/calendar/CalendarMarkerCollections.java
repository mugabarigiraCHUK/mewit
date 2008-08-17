/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.metacube.components.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 *
 * @author Azrul
 */
public class CalendarMarkerCollections {
    private Map<String, Set<CalendarMarker>> markers = new HashMap<String, Set<CalendarMarker>>();
    private SimpleDateFormat sdf = new SimpleDateFormat(ResourceBundle.getBundle("epice").getString("SHORT_DATE_FORMAT"));
    
    public void addMarker(CalendarMarker marker){
        if (!markers.containsKey(marker.getMarkerDate())){
            markers.put(sdf.format(marker.getMarkerDate()),new HashSet<CalendarMarker>());
        }
        markers.get(sdf.format(marker.getMarkerDate())).add(marker); 
       
    }
    
    public  Set<CalendarMarker> getMarkersOnDate(Date date){
        return markers.get(sdf.format(date))!=null?markers.get(sdf.format(date)):new HashSet<CalendarMarker>();
    }
    
    public void reset(){
        markers.clear();
    }
}
