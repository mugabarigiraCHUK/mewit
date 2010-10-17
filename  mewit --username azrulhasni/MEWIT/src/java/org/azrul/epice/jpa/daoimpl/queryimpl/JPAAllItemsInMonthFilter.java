/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl.queryimpl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class JPAAllItemsInMonthFilter implements JPAItemsFilter {

    private String type = null;
    private int month;
    private int year;

    public String getType() {
        return type;
    }

    public JPAAllItemsInMonthFilter() {
        type = "ALL_ITEMS_IN_MONTH";
        Calendar cal = GregorianCalendar.getInstance();
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
    }

    public JPAAllItemsInMonthFilter(int month, int year) {
        type = "ALL_ITEMS_IN_MONTH_" + month + "_YEAR_" + year;
        this.month = month;
        this.year = year;
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        Calendar firstDateOfMonth = Calendar.getInstance();
        firstDateOfMonth.set(Calendar.DAY_OF_MONTH, firstDateOfMonth.getActualMinimum(Calendar.DAY_OF_MONTH));

        Calendar lastDateOfMonth = Calendar.getInstance();
        lastDateOfMonth.set(Calendar.DAY_OF_MONTH, lastDateOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cb.and(
                cb.or(
                cb.equal(ritem.get("sender").as(String.class), user),
                cb.equal(ritem.get("recipient").as(String.class), user)),
                cb.or(
                cb.between(ritem.get("startDate").as(Date.class), firstDateOfMonth.getTime(), lastDateOfMonth.getTime()),
                cb.between(ritem.get("deadline").as(Date.class), firstDateOfMonth.getTime(), lastDateOfMonth.getTime())));
        
        

    }

     public boolean filter(Item item, String user) {
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
