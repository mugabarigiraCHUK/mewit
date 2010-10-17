/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.Date;

/**
 *
 * @author azrulhasni
 */
public class ModifyDeadlineRequest extends Request {
    private String itemID;
    private Date newDeadline;

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the newDeadline
     */
    public Date getNewDeadline() {
        return newDeadline;
    }

    /**
     * @param newDeadline the newDeadline to set
     */
    public void setNewDeadline(Date newDeadline) {
        this.newDeadline = newDeadline;
    }

  

}
