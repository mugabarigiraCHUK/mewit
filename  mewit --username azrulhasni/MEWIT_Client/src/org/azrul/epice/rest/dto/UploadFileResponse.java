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
public class UploadFileResponse extends Response {

    private String file;
    private Item item;

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
