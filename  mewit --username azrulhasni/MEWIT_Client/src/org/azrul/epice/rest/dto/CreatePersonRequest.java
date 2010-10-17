/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import org.azrul.epice.domain.Person;

/**
 *
 * @author azrulhasni
 */
public class CreatePersonRequest extends Request{
    private Person person;
    private String password;

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
