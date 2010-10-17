/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulhasni
 */
public class AddAttachmentRequest extends Request{
    private String sessionId;
    private Item item;
    //private String itemId;
    //private String fileRepoId;
    private String file;

    /**
     * @return the itemId
     */
//    public String getItemId() {
//        return itemId;
//    }
//
//    /**
//     * @param itemId the itemId to set
//     */
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

//    /**
//     * @return the fileRepoId
//     */
//    public String getFileRepoId() {
//        return fileRepoId;
//    }
//
//    /**
//     * @param fileRepoId the fileRepoId to set
//     */
//    public void setFileRepoId(String fileRepoId) {
//        this.fileRepoId = fileRepoId;
//    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
