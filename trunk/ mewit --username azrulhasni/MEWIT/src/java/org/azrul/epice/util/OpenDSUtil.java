/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

/**
 *
 * @author azrulhasni
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import org.opends.server.admin.client.AuthenticationException;
import org.opends.server.admin.client.AuthenticationNotSupportedException;
import org.opends.server.admin.client.CommunicationException;
import org.opends.server.admin.client.ldap.JNDIDirContextAdaptor;

import javax.naming.directory.DirContext;
import javax.naming.NamingException;
import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;


import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapName;
import org.azrul.epice.domain.Person;

public class OpenDSUtil {

    private static String LDAP_HOST;
    private static int LDAP_PORT;
    private static String LDAP_PEOPLE_CN;

    static {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        LDAP_HOST = props.getString("LDAP_HOST");
        LDAP_PORT = Integer.parseInt(props.getString("LDAP_PORT"));
        LDAP_PEOPLE_CN = props.getString("LDAP_PEOPLE_CN");
    }

     public static JNDIDirContextAdaptor getAdaptorAsAdmin(String username, String password) {
        try {
            return JNDIDirContextAdaptor.simpleBind(LDAP_HOST, LDAP_PORT,username, password);
        } catch (CommunicationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationNotSupportedException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JNDIDirContextAdaptor getAdaptor(String username, String password) {
        try {
            return JNDIDirContextAdaptor.simpleBind(LDAP_HOST, LDAP_PORT, "uid=" + username + "," + LDAP_PEOPLE_CN, password);
        } catch (CommunicationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationNotSupportedException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Attributes personToAttributes(Person person) {
        Attributes atts = new BasicAttributes();
        personToExistingAttributes(atts, person);
        return atts;
    }

    public static Person attributesToPerson(Attributes atts) {
        Person p = new Person();

        try {
            if (atts.get("mobile") != null) {
                p.setCellPhoneWork((String) atts.get("mobile").get());
            }
            if (atts.get("mail") != null) {
                p.setEmailWork((String) atts.get("mail").get());
            }
            if (atts.get("givenname") != null) {
                p.setCellPhoneWork((String) atts.get("givenname").get());
            }
            if (atts.get("sn") != null) {
                p.setLastName((String) atts.get("sn").get());
            }
            if (atts.get("o") != null) {
                p.setOrganization((String) atts.get("o").get());
            }
            if (atts.get("telephoneNumber") != null) {
                p.setPotsPhoneWork((String) atts.get("telephoneNumber").get());
            }
            if (atts.get("uid") != null) {
                p.setUsername((String) atts.get("uid").get());
            }

        } catch (NamingException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public static void createEntry(JNDIDirContextAdaptor adaptor, Attributes atts, String randomPassword) {
        if (atts.get("uid") == null) {
            return;
        }
        String uid = null;
        JNDIDirContextAdaptor adminAdaptor = null;
        try {
            if (adaptor != null) {
                uid = atts.get("uid").get().toString();
                ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
                adminAdaptor = JNDIDirContextAdaptor.simpleBind(LDAP_HOST, LDAP_PORT, props.getString("LDAP_DIR_MANAGER_CN"), props.getString("LDAP_DIR_MANAGER_PASSWORD"));
                atts.put("userPassword", randomPassword);
                atts.put("objectClass", "inetOrgPerson");
                atts.put("cn", uid);
                adminAdaptor.createEntry(new LdapName("uid=" + uid + "," + LDAP_PEOPLE_CN), atts);

            }
        } catch (CommunicationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationNotSupportedException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (adminAdaptor != null) {
                adminAdaptor.unbind();
            }
        }
    }

    public static LdapName find(JNDIDirContextAdaptor adaptor, String uid) {
        try {
            Collection<LdapName> user = adaptor.listEntries(new LdapName("ou=People,dc=mewit,dc=net"), "uid=" + uid);
            if (user.isEmpty()) {
                return null;
            }
            LdapName ldapName = user.iterator().next();
            return ldapName;
            
        } catch (NamingException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws NamingException {
        JNDIDirContextAdaptor adaptor = null;
        try {
            //DirContext dirContext = createLdapContext();
            //JNDIDirContextAdaptor adaptor =  JNDIDirContextAdaptor.adapt(dirContext);
            adaptor = JNDIDirContextAdaptor.simpleBind("localhost", 389, "uid=donald.duck@company.com,ou=People,dc=mewit,dc=net", "abc123");
            //Collection<LdapName> all = adaptor.listEntries(new LdapName("ou=People,dc=mewit,dc=net"), "uid=donald_dot_duck_at_company_dot_com");
            Collection<LdapName> all = adaptor.listEntries(new LdapName("ou=People,dc=mewit,dc=net"), "uid=donald.duck@company.com");
            List<String> attr = new ArrayList<String>();
            attr.add("*");
            for (LdapName n : all) {
                Attributes a = adaptor.readEntry(n, attr);
                System.out.println(a.get("givenname").get());
                System.out.println(a.get("sn").get());
                System.out.println(a.get("mail").get());
                System.out.println(a.get("cn").get());
                System.out.println(a.get("o").get());
                System.out.println(a.get("mobile").get());
                System.out.println(a.get("telephoneNumber").get());
                a.put("o", "Cybersecurity1");
                a.put("userPassword","abc1234");
                adaptor.modifyEntry(n, a);
                System.out.println(a.get("o").get());
            }
            adaptor.unbind();
//            Person p = new Person();
//            p.setCellPhoneWork("012-34567");
//            p.setEmailWork("person@test.com");
//            p.setFirstName("First");
//            p.setLastName("Last");
//            p.setOrganization("Company");
//            p.setPotsPhoneWork("01-2345678");
//            p.setUsername("testuser1");
//            Attributes atts = personToAttributes(p);
//
//            createEntry(adaptor, atts, "12345");
        } catch (CommunicationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationNotSupportedException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(OpenDSUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (adaptor != null) {
                adaptor.unbind();
            }
        }


        // do other stuff with the adaptor
    }

    private static DirContext createLdapContext() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        //env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
        //env.put(Context.SECURITY_CREDENTIALS, "qwerty");
        env.put(Context.SECURITY_PRINCIPAL, "uid=donald_dot_duck_at_company_dot_com,ou=People,dc=mewit,dc=net");
        env.put(Context.SECURITY_CREDENTIALS, "abc123");
        return new InitialLdapContext(env, null);
    }

    public static void personToExistingAttributes(Attributes atts, Person person) {
        atts.put("mail", person.getEmailWork());
        atts.put("givenname", person.getFirstName());
        atts.put("sn", person.getLastName());
        atts.put("o", person.getOrganization());
        atts.put("mobile", person.getCellPhoneWork());
        atts.put("telephoneNumber", person.getPotsPhoneWork());
        atts.put("uid", person.getUsername());
    }
}
