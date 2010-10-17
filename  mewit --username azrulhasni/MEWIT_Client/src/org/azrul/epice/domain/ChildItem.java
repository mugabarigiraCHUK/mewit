/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.domain;

import java.io.Serializable;

public class ChildItem implements Serializable {
    private static final long serialVersionUID = -3721881600090407794L;
    private String id;
    private String recipient;
    private String subject;
    private Item.Status status;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the status
     */
    public Item.Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Item.Status status) {
        this.status = status;
    }
}
