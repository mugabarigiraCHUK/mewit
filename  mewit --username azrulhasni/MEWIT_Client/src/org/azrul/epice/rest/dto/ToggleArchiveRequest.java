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
public class ToggleArchiveRequest extends Request{
  private List<String> itemIds;
  private Boolean archive;

    /**
     * @return the itemID
     */
    public List<String> getItemIds() {
        return itemIds;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }


    /**
     * @return the archive
     */
    public Boolean getArchive() {
        return archive;
    }

    /**
     * @param archive the archive to set
     */
    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
}
