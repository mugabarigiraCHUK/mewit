/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.domain.Person;
import org.azrul.epice.exception.UserNotExistException;
import org.azrul.epice.exception.WrongPasswordException;
import org.azrul.epice.util.LogUtil;
import org.azrul.epice.util.SendMailUtil;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author azrulm
 */
public class JPAPersonDAO implements PersonDAO {

    private static String PUNAME = "MEWITPU";
    private static Executor executor = Executors.newFixedThreadPool(10);

    public JPAPersonDAO() {
    }

    public Person addNewUser(Person person) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        String cachedPassword = new String(person.getPassword());
        person.setPassword(passwordEncryptor.encryptPassword(person.getPassword()));
        person.setType("NEW");
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            em.persist(person);
            em.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            em.close();
        }
        person.setPassword(cachedPassword);
        return person;
    }

    public void updateUser(String username, Person person) throws UserNotExistException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            Person dbPerson = em.find(Person.class, username);
            if (dbPerson == null) {
                throw new UserNotExistException();
            }
            dbPerson.setCellPhoneWork(person.getCellPhoneWork()); 
            dbPerson.setFirstName(person.getFirstName());
            dbPerson.setLastName(person.getLastName());
            dbPerson.setOrganization(person.getOrganization());
            dbPerson.setPotsPhoneWork(person.getPotsPhoneWork());
            dbPerson.setPresence(person.getPresence());
            dbPerson.setStatus(person.getStatus());
            dbPerson.setType(person.getType());
            if (("NEW").equals(dbPerson.getType())){
                BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
                dbPerson.setPassword(passwordEncryptor.encryptPassword(person.getPassword()));
                dbPerson.setType("OLD");
            }
            em.merge(dbPerson);
            em.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
        } finally {
            em.close();
        }
    }

     public void changeUserPasword(String username, String newPassword) throws UserNotExistException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            Person dbPerson = em.find(Person.class, username);
            if (dbPerson == null) {
                throw new UserNotExistException();
            }

            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            dbPerson.setPassword(passwordEncryptor.encryptPassword(newPassword));
            dbPerson.setType("NEW");
            em.persist(dbPerson);
            em.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(JPAPersonDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            em.close();
        }
    }

    public void changeUserPasword(String username, String currentPassword, String newPassword) throws UserNotExistException, WrongPasswordException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // Begin a new local transaction so that we can persist a new entity
            tx.begin();
            Person dbPerson = em.find(Person.class, username);
            if (dbPerson == null) {
                throw new UserNotExistException();
            }
            
            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            if (!passwordEncryptor.checkPassword(currentPassword,dbPerson.getPassword())) {
                throw new WrongPasswordException();
            }
            dbPerson.setPassword(passwordEncryptor.encryptPassword(newPassword));
            em.persist(dbPerson);
            em.flush();
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(JPAPersonDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            em.close();
        }
    }

    public Person getUserInfo(String username) throws UserNotExistException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        Person dbPerson = em.find(Person.class, username);
        dbPerson.setPassword(null);
        if (dbPerson == null) {
            throw new UserNotExistException();
        }
        return dbPerson;
    }

    public boolean isUserExist(String username) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        return em.find(Person.class, username) != null;
    }

    public boolean isValid(String username, String password) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        Person person = em.find(Person.class, username);

        em.close();
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        return passwordEncryptor.checkPassword(password, person.getPassword());
    }

    public boolean verifyUserExistAndInviteIfNot(final String username,final String usermail, final String sendername) {
        if (isUserExist(username) == true) {
            return true;
        } else {
            final Person newperson = new Person();
            newperson.setEmailWork(usermail);
            newperson.setUsername(username);
            newperson.setPassword(UUID.randomUUID().toString().replace("-",""));
            addNewUser(newperson);
            final ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
            executor.execute(new Runnable(){
                public void run() {
//                    try {
                        String inviteText = props.getString("EMAIL_INVITE_TEXT").
                                replace("%%Link%%", props.getString("EPICE_URL")).
                                replace("%%Email%%", newperson.getUsername()).
                                replace("%%Key%%", newperson.getPassword()).
                                replace("%%Sender%%", sendername);
                        //System.out.println("inviteText=" + inviteText);
                        SendMailUtil.sendMail(usermail, inviteText, props.getString("EMAIL_INVITE_TITLE"));
//                    } catch (UnsupportedEncodingException ex) {
//                        Logger.getLogger(JPAPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            });
            return false;
        }

    }

    public static void main(String[] args) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        System.out.println(passwordEncryptor.encryptPassword("abc123"));
    }
}
