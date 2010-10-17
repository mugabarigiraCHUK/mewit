/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class ItemClassifierByDateUtil {

    private static SimpleDateFormat sdf;


    static {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        sdf = new SimpleDateFormat(props.getString("SHORT_DATE_FORMAT"));
    }

    public static Map<String, List<Item>> classify(List<Item> items) {
        HashMap<String, List<Item>> itemMap = new HashMap<String, List<Item>>();

        for (Item item : items) {
            if (item.getStartDate() != null && item.getDeadLine() != null) {
                long days = DateUtil.daysBetween(item.getStartDate(), item.getDeadLine())+1;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(item.getStartDate());
                for (int i = 0; i < days; i++) {
                    String date = sdf.format(cal.getTime());
                    if (itemMap.containsKey(date)) {
                        itemMap.get(date).add(item);
                    } else {
                        List<Item> itemSet = new ArrayList<Item>();
                        itemSet.add(item);
                        itemMap.put(date, itemSet);
                    }
                    cal.add(Calendar.DAY_OF_MONTH,1);
                }
            }
//            if (item.getDeadLine() != null) {
//                String deadline = sdf.format(item.getDeadLine());
//                if (itemMap.containsKey(deadline)) {
//                    if (!itemMap.get(deadline).contains(item)){
//                        itemMap.get(deadline).add(item);
//                    }
//                } else {
//                    List<Item> itemSet = new ArrayList<Item>();
//                    itemSet.add(item);
//                    itemMap.put(deadline, itemSet);
//                }
//            }
        }
        return itemMap;
    }
}
