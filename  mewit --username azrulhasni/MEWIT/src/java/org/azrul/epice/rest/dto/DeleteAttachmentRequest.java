/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.List;

/**
 *
 * @author azrulhasni
 */
public class DeleteAttachmentRequest extends Request {
    private String itemId;
    private List<String> attachmentIds;

  
    /**
     * @return the itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the attachmentIds
     */
    public List<String> getAttachmentIds() {
        return attachmentIds;
    }

    /**
     * @param attachmentIds the attachmentIds to set
     */
    public void setAttachmentIds(List<String> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

   
}
