/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.List;
import java.util.Map;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;

/**
 *
 * @author azrulhasni
 */
public class ModifyProfileResponse extends Response{
    private List<Item> items;
    private Person person;

    /**
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @param person the person to set
     */
    public void setPerson(Person person) {
        this.person = person;
    }
   

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
