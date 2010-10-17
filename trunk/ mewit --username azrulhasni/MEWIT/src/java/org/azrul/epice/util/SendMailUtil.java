/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Azrul
 */
public class SendMailUtil {

    public static void sendMail(String toEmail, String message, String subject) {
        try {
//        Authenticator auth = new Authenticator() {
//
//            @Override
//            public PasswordAuthentication getPasswordAuthentication() {
//                ResourceBundle rb = ResourceBundle.getBundle("org.azrul.epice.config.epice");
//                return new PasswordAuthentication(rb.getString("SMTP_USER"), rb.getString("SMTP_PASSWORD"));
//            }
//        };
            ResourceBundle rb = ResourceBundle.getBundle("org.azrul.epice.config.epice");
            //get system propeties
            Properties props = new Properties();
            //setup mail server example using google
            props.put("mail.smtp.user", rb.getString("SMTP_USER"));
            props.put("mail.smtp.host", rb.getString("SMTP_HOST"));
            props.put("mail.smtp.port", rb.getString("SMTP_PORT"));
            props.put("mail.smtp.starttls.enable", rb.getString("SMTP_STARTTLS_ENABLE"));
            props.put("mail.smtp.auth", rb.getString("SMTP_AUTH"));
            props.put("mail.smtp.socketFactory.port", rb.getString("SMTP_SOCKETFACTORY_PORT"));
            props.put("mail.smtp.socketFactory.class", rb.getString("SMTP_SOCKETFACTORY_CLASS"));
            props.put("mail.smtp.socketFactory.fallback", rb.getString("SMTP_SOCKETFACTORY_FALLBACK"));
            //SecurityManager security = System.getSecurityManager();
            Authenticator auth = new SMTPAuthenticator();
            //get session;
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            //define message
            MimeMessage msg = new MimeMessage(session);
            //only one recipient a time
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            InternetAddress[] iaFroms = {new InternetAddress(rb.getString("SMTP_USER"))};
            msg.addFrom(iaFroms);
            msg.setSubject(subject);
            //get attachment
            //---------------
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent(message, "text/html");
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//            msg.setContent(multipart);
            msg.setContent(message,"text/plain");
            //send message
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

       


    }

     private static class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            ResourceBundle appProps = ResourceBundle.getBundle("org.azrul.epice.config.epice");
            return new PasswordAuthentication((String)appProps.getObject("SMTP_USER"), (String)appProps.getObject("SMTP_PASSWORD"));
        }
    }


//    private class SMTPAuthenticator extends javax.mail.Authenticator {
//        @Override
//        public PasswordAuthentication getPasswordAuthentication() {
//            ResourceBundle rb = ResourceBundle.getBundle("org.azrul.epice.config.epice");
//            return new PasswordAuthentication(rb.getString("SMTP_USER"), rb.getString("SMTP_PASSWORD"));
//        }
//    }
    public static void main(String args[]) {

        String text = "Hi, \n A task was sent to you by Azrul. Please go to this address to download mewit and see your task ";
        SendMailUtil.sendMail("azrulhasni@gmail.com", text, "A mewittask was sent to you");
    }
}
