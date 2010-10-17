/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.db4o.daoimpl.queryimpl;

import com.db4o.query.Predicate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class DB4OAllItemsInMonthFilter implements DB4OItemsFilter {

    private String type = null;
    private int month;
    private int year;

    
    public String getType() {
        return type;
    }

    public DB4OAllItemsInMonthFilter() {
        type = "ALL_ITEMS_IN_MONTH";
        Calendar cal = GregorianCalendar.getInstance();
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
    }

    public DB4OAllItemsInMonthFilter(int month, int year) {
        type = "ALL_ITEMS_IN_MONTH_" + month + "_YEAR_" + year;
        this.month = month;
        this.year = year;
    }

    public DB4OItemPredicate filter(final Item item, final String user) {
        Predicate predicate = new Predicate() {

            
            public boolean match(Object et) {
                return booleanFilter(item, user);
            }
        };
        DB4OItemPredicate itemPredicate = new DB4OItemPredicate();
        itemPredicate.set(predicate);
        return itemPredicate;
    }

    
    public boolean booleanFilter(Item item, String user) {
        if (item.getRecipient().equals(user) || item.getSender().equals(user)) {
            if (item.getStartDate() != null) {
                Calendar startDate = GregorianCalendar.getInstance();
                startDate.setTime(item.getStartDate());
                if (startDate.get(Calendar.MONTH) == month && startDate.get(Calendar.YEAR) == year) {
                    return true;
                }
            }
            if (item.getDeadLine() != null) {
                Calendar deadline = GregorianCalendar.getInstance();
                deadline.setTime(item.getDeadLine());
                if (deadline.get(Calendar.MONTH) == month && deadline.get(Calendar.YEAR) == year) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }
}
