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
public class ModifyPasswordResponse extends Response {
    private List<Item> items;


    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }



    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

   
}
