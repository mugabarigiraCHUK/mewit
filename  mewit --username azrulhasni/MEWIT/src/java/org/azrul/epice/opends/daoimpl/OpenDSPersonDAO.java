/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.opends.daoimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.domain.Person;
import org.azrul.epice.exception.UserNotExistException;
import org.azrul.epice.exception.WrongPasswordException;
import org.azrul.epice.util.OpenDSUtil;
import org.azrul.epice.util.SendMailUtil;
import org.azrul.epice.util.StringUtil;
import org.opends.server.admin.client.ldap.JNDIDirContextAdaptor;

/**
 *
 * @author azrulhasni
 */
public class OpenDSPersonDAO implements PersonDAO {

    private static String ldapManagerCN;
    private static String ldapManagerPassword;

    static {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        ldapManagerCN = props.getString("LDAP_DIR_MANAGER_CN");
        ldapManagerPassword = props.getString("LDAP_DIR_MANAGER_PASSWORD");
    }

    private JNDIDirContextAdaptor createAdaptor(String username, String password) {
        JNDIDirContextAdaptor adaptor = OpenDSUtil.getAdaptor(username, password);
        return adaptor;
    }

    private JNDIDirContextAdaptor createAdaptorAsAdmin() {
        JNDIDirContextAdaptor adaptor = OpenDSUtil.getAdaptorAsAdmin(ldapManagerCN, ldapManagerPassword);
        return adaptor;
    }

   

    public Person addNewUser(Person person) {
        OpenDSUtil.createEntry(createAdaptorAsAdmin(), OpenDSUtil.personToAttributes(person),person.getPassword());
        return person;
    }

    public void updateUser(String username, Person person)  throws UserNotExistException{
        JNDIDirContextAdaptor adaptor = createAdaptorAsAdmin();
        try {
            LdapName ldapName = OpenDSUtil.find(adaptor, username);
            if (ldapName==null){
                throw  new UserNotExistException(username);
            }
            List<String> all = new ArrayList<String>();
            Attributes atts = adaptor.readEntry(ldapName, all);
            OpenDSUtil.personToExistingAttributes(atts, person);
            adaptor.modifyEntry(ldapName, atts);
        } catch (NamingException ex) {
            Logger.getLogger(OpenDSPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeUserPasword(String username, String currentPassword, String newPassword)  throws UserNotExistException, WrongPasswordException{
        JNDIDirContextAdaptor localAdaptor = null;
        try {
            localAdaptor = (JNDIDirContextAdaptor) createAdaptor(username, currentPassword);
            if (localAdaptor ==null){
                throw new UserNotExistException(username);
            }
            LdapName ldapName = OpenDSUtil.find(localAdaptor, username);
            List<String> all = new ArrayList<String>();
            Attributes atts = createAdaptorAsAdmin().readEntry(ldapName, all);
            atts.put("userPassword", newPassword);
            localAdaptor.modifyEntry(ldapName, atts);

        } catch (NamingException ex) {
            throw new WrongPasswordException();
        } finally{
            if (localAdaptor!=null){
                localAdaptor.unbind();
            }
        }
    }

    public void changeUserPasword(String username,  String newPassword)  throws UserNotExistException{
        JNDIDirContextAdaptor localAdaptor = null;
        try {
            localAdaptor = (JNDIDirContextAdaptor) createAdaptorAsAdmin();
            if (localAdaptor ==null){
                throw new UserNotExistException(username);
            }
            LdapName ldapName = OpenDSUtil.find(localAdaptor, username);
            List<String> all = new ArrayList<String>();
            Attributes atts = createAdaptorAsAdmin().readEntry(ldapName, all);
            atts.put("userPassword", newPassword);
            localAdaptor.modifyEntry(ldapName, atts);
           
        } catch (NamingException ex) {
            throw new UserNotExistException();
        } finally{
            if (localAdaptor!=null){
                localAdaptor.unbind();
            }
        }
    }

    public Person getUserInfo(String username)  throws UserNotExistException {
         JNDIDirContextAdaptor adaptor = createAdaptorAsAdmin();
        try {
            LdapName ldapName = OpenDSUtil.find(adaptor, username);
            if (ldapName==null){
                throw new UserNotExistException(username);
            }
            Attributes atts = adaptor.readEntry(ldapName,Arrays.asList("*"));
            return OpenDSUtil.attributesToPerson(atts);
        } catch (NamingException ex) {
            Logger.getLogger(OpenDSPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isUserExist(String username) {
        JNDIDirContextAdaptor adaptor = createAdaptorAsAdmin();
        if (OpenDSUtil.find(adaptor, username)==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean isValid(String username, String password) {
        if (createAdaptor(username, password)==null){
            return false;
        }else{
            return true;
        }
    }

    public boolean verifyUserExistAndInviteIfNot(String username, String useremail, String sendername) {
        if (isUserExist(username)) {
            return true;
        } else {
            //create user first
            Person person = new Person();
            person.setUsername(username);
            person.setFirstName("_");
            person.setLastName("_");
            person.setCellPhoneWork("0000000");
            person.setEmailWork(useremail);
            person.setOrganization("_");
            person.setPotsPhoneWork("0000000");

            String password = StringUtil.randomString(6);
            addNewUser(person);

            String text = "Hi, \n A task was sent to you by "+sendername+". Please go to this address [http://www.mewit.net] to download mewit and see your task. \nYour login information:\nusername: "+useremail+"\npassword: "+password+"\nPlease change your passowrd once you have loged in the first time";
            SendMailUtil.sendMail(useremail, text, "A mewittask was sent to you");
            return false;
        }
    }
}
