/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author azrulm
 */
@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String username;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private String id;
    private String presence;
    private String status;
    private String type;

    private String firstName;
    private String lastName;
    private String emailWork;
    private String cellPhoneWork;
    private String potsPhoneWork;
    private String organization;
    private String password;

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.azrul.epice.domain.Person[username=" + username + "]";
    }

        /**
         * @return the username
         */
        public String getUsername() {
            return username;
        }

        /**
         * @param username the username to set
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * @return the presence
         */
        public String getPresence() {
            return presence;
        }

        /**
         * @param presence the presence to set
         */
        public void setPresence(String presence) {
            this.presence = presence;
        }

        /**
         * @return the status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return the firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * @param firstName the firstName to set
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * @param lastName the lastName to set
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * @return the emailWork
         */
        public String getEmailWork() {
            return emailWork;
        }

        /**
         * @param emailWork the emailWork to set
         */
        public void setEmailWork(String emailWork) {
            this.emailWork = emailWork;
        }

        /**
         * @return the cellPhoneWork
         */
        public String getCellPhoneWork() {
            return cellPhoneWork;
        }

        /**
         * @param cellPhoneWork the cellPhoneWork to set
         */
        public void setCellPhoneWork(String cellPhoneWork) {
            this.cellPhoneWork = cellPhoneWork;
        }

        /**
         * @return the potsPhoneWork
         */
        public String getPotsPhoneWork() {
            return potsPhoneWork;
        }

        /**
         * @param potsPhoneWork the potsPhoneWork to set
         */
        public void setPotsPhoneWork(String potsPhoneWork) {
            this.potsPhoneWork = potsPhoneWork;
        }

        /**
         * @return the organization
         */
        public String getOrganization() {
            return organization;
        }

        /**
         * @param organization the organization to set
         */
        public void setOrganization(String organization) {
            this.organization = organization;
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
