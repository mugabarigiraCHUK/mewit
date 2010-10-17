/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.dao.factory;

import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.jpa.daoimpl.JPAPersonDAO;
import org.azrul.epice.opends.daoimpl.OpenDSPersonDAO;

/**
 *
 * @author azrulhasni
 */
public class PersonDAOFactory {
    public static PersonDAO getInstance(){
        return new JPAPersonDAO();
    }
}
