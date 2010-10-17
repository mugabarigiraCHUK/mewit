/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.List;
import java.util.Map;
import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulhasni
 */
public class DelegateItemResponse extends Response{
    private Item parentItem;
    private List<Item> childItems;

    /**
     * @return the parentItem
     */
    public Item getParentItem() {
        return parentItem;
    }

    /**
     * @param parentItem the parentItem to set
     */
    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }

   
    /**
     * @return the childItems
     */
    public List<Item> getChildItems() {
        return childItems;
    }

    /**
     * @param childItems the childItems to set
     */
    public void setChildItems(List<Item> childItems) {
        this.childItems = childItems;
    }

 
   
    

}
