/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.dao;

import org.azrul.epice.domain.Person;
import org.azrul.epice.exception.UserNotExistException;

/**
 *
 * @author Azrul
 */
public interface PersonDAO {
   public Person addNewUser(Person person);
    public void updateUser(String username, Person person)  throws UserNotExistException;
    public void changeUserPasword(String username, String currentPassword, String newPassword) throws UserNotExistException;
    public void changeUserPasword(String username, String newPassword) throws UserNotExistException;
    public Person getUserInfo(String username)  throws UserNotExistException;
    public boolean isUserExist(String username);
    public boolean isValid(String username, String password);
    public boolean verifyUserExistAndInviteIfNot(String username,String usermail, String sendername);
}
