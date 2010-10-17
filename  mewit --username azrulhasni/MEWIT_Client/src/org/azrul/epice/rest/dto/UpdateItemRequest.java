/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.Map;
import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulm
 */
public class UpdateItemRequest extends Request{
     private Item newItem;
     private Map<String, String> parameters;

    /**
     * @return the newItem
     */
    public Item getNewItem() {
        return newItem;
    }

    /**
     * @param newItem the newItem to set
     */
    public void setNewItem(Item newItem) {
        this.newItem = newItem;
    }

    /**
     * @return the parameters
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
