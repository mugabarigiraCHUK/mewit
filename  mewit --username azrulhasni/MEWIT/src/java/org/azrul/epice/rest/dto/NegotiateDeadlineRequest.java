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
public class NegotiateDeadlineRequest extends Request {
    private String itemID;
    private Date newProposedDeadline;
    private String reasonForNegotiationOfDeadline;

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
     * @return the newProposedDeadline
     */
    public Date getNewProposedDeadline() {
        return newProposedDeadline;
    }

    /**
     * @param newProposedDeadline the newProposedDeadline to set
     */
    public void setNewProposedDeadline(Date newProposedDeadline) {
        this.newProposedDeadline = newProposedDeadline;
    }

    /**
     * @return the reasonForNegotiationOfDeadline
     */
    public String getReasonForNegotiationOfDeadline() {
        return reasonForNegotiationOfDeadline;
    }

    /**
     * @param reasonForNegotiationOfDeadline the reasonForNegotiationOfDeadline to set
     */
    public void setReasonForNegotiationOfDeadline(String reasonForNegotiationOfDeadline) {
        this.reasonForNegotiationOfDeadline = reasonForNegotiationOfDeadline;
    }

  
}
