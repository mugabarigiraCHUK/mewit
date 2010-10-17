/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.List;
import java.util.Map;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;

/**
 *
 * @author azrulhasni
 */
public class LoginResponse extends Response{
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

}
