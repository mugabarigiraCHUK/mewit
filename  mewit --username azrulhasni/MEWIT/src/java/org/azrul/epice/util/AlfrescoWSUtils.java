/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import org.alfresco.webservice.accesscontrol.ACE;
import org.alfresco.webservice.accesscontrol.ACL;
import org.alfresco.webservice.accesscontrol.AccessControlService;
import org.alfresco.webservice.accesscontrol.AccessControlServiceSoapBindingStub;
import org.alfresco.webservice.accesscontrol.AccessStatus;
import org.alfresco.webservice.accesscontrol.OwnerResult;
import org.alfresco.webservice.authentication.AuthenticationFault;
import org.alfresco.webservice.content.Content;
import org.alfresco.webservice.content.ContentFault;
import org.alfresco.webservice.content.ContentServiceSoapBindingStub;
import org.alfresco.webservice.repository.UpdateResult;
import org.alfresco.webservice.types.CML;
import org.alfresco.webservice.types.CMLAddAspect;
import org.alfresco.webservice.types.CMLCreate;
import org.alfresco.webservice.types.ContentFormat;
import org.alfresco.webservice.types.NamedValue;
import org.alfresco.webservice.types.ParentReference;
import org.alfresco.webservice.types.Predicate;
import org.alfresco.webservice.types.Reference;
import org.alfresco.webservice.types.Store;
import org.alfresco.webservice.util.AuthenticationUtils;
import org.alfresco.webservice.util.Constants;
import org.alfresco.webservice.util.Utils;
import org.alfresco.webservice.util.WebServiceFactory;
import org.alfresco.webservice.repository.RepositoryFault;
import org.alfresco.webservice.types.Query;
import org.alfresco.webservice.util.ContentUtils;

/**
 *
 * @author azrulhasni
 */
public class AlfrescoWSUtils {

    private static String ALFRESCO_ADMIN_USERNAME = null;
    private static String ALFRESCO_ADMIN_PASSWORD = null;

    static {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        ALFRESCO_ADMIN_USERNAME = props.getString("ALFRESCO_ADMIN_USERNAME");
        ALFRESCO_ADMIN_PASSWORD = props.getString("ALFRESCO_ADMIN_PASSWORD");
    }

    public static void main(String[] args) {
        File myfile = new File("C:/Users/azrulhasni/Documents/Training/Java for adults - Example.ppt");
        String res = upload(myfile, "UTF-8", "hello"+UUID.randomUUID().toString(), "goofy.goof_at_company.com", Arrays.asList("donald.duck_at_company.com"));
        System.out.println("Result=" + res);
    }

    @SuppressWarnings("empty-statement")
    public static void main2(String[] args) throws Exception {
        // Start the session
        AuthenticationUtils.startSession(ALFRESCO_ADMIN_USERNAME, ALFRESCO_ADMIN_PASSWORD);
        File myfile = new File("C:/Users/azrulhasni/Documents/Training/Java for adults - Example.ppt");
        String mimetype = getMimeType(myfile);
        try {
            // Create a reference to the parent where we want to create content
            Store storeRef = new Store(Constants.WORKSPACE_STORE, "SpacesStore");
            ParentReference userFolderRef = new ParentReference(storeRef, null, "/app:company_home/app:user_homes/cm:goofy.goof_at_company.com", Constants.ASSOC_CONTAINS, null);
            Reference folder = null;

            folder = createNode(userFolderRef, Constants.TYPE_FOLDER, "Momo");
            ParentReference folderRef = new ParentReference(storeRef, null, "/app:company_home/app:user_homes/cm:goofy.goof_at_company.com/cm_x003a_Momo", Constants.ASSOC_CONTAINS, null);
            Reference content = createNode(folderRef, Constants.TYPE_CONTENT, URLEncoder.encode("Web Services sample (" + System.currentTimeMillis() + ").ppt", "UTF-8"));

            AccessControlServiceSoapBindingStub acs = WebServiceFactory.getAccessControlService();
            Predicate predFolder = new Predicate(new Reference[]{folderRef}, storeRef, null);
            acs.addACEs(predFolder, new ACE[]{new ACE("donald.duck_at_company.com", "Contributor", AccessStatus.acepted)});

            Predicate predUserFolder = new Predicate(new Reference[]{userFolderRef}, storeRef, null);
            acs.addACEs(predUserFolder, new ACE[]{new ACE("donald.duck_at_company.com", "Consumer", AccessStatus.acepted)});

            ACL[] acls = acs.getACLs(predFolder, null);
            for (int i = 0; i < acls.length; i++) {
                ACL acl = acls[i];
                System.out.println(acl.getReference().getPath());
                ACE[] aces = acl.getAces();
                for (int j = 0; j < aces.length; j++) {
                    System.out.println("auth=" + aces[i].getAuthority());
                    System.out.println("perm=" + aces[i].getPermission());
                    System.out.println("acst=" + aces[i].getAccessStatus());
                }
            }
//            String[] auths = acs.getAuthorities();
//            for (int i=0;i<auths.length;i++){
//                System.out.println(auths[i]);
//
//            }

//            OwnerResult[] owners = acs.getOwners(pred);
//            for (int i=0;i<owners.length;i++){
//                System.out.println(owners[i].getOwner());
//            }
            //
            // Write some content
            //

            ContentServiceSoapBindingStub contentService = WebServiceFactory.getContentService();
            //String text = "3 - The quick brown fox jumps over the lazy dog";
            ContentFormat contentFormat = new ContentFormat(mimetype, "UTF-8");
            Content contentRef = contentService.write(content, Constants.PROP_CONTENT, ContentUtils.convertToByteArray(new FileInputStream(myfile)), contentFormat);
            System.out.println("Content Length: " + contentRef.getLength());
            System.out.println("Content Loc: " + contentRef.getNode().getPath());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            // End the session
            AuthenticationUtils.endSession();
            System.exit(0);
        }
    }

    private static Reference createNode(ParentReference companyHomeParent, String type, String nodename) {
        try {
            // Assign name
            String name = nodename;
            companyHomeParent.setChildName("cm:" + name);
            // Construct CML statement to create content node
            // Note: Assign "1" as a local id, so we can refer to it in subsequent
            //       CML statements within the same CML block
            NamedValue[] contentProps = new NamedValue[1];
            contentProps[0] = Utils.createNamedValue(Constants.PROP_NAME, name);
            CMLCreate create = new CMLCreate("1", companyHomeParent, null, null, null, type, contentProps);
            // Construct CML statement to add titled aspect
            NamedValue[] titledProps = new NamedValue[2];
            titledProps[0] = Utils.createNamedValue(Constants.PROP_TITLE, name);
            titledProps[1] = Utils.createNamedValue(Constants.PROP_DESCRIPTION, name);
            CMLAddAspect addAspect = new CMLAddAspect(Constants.ASPECT_TITLED, titledProps, null, "1");
            // Construct CML Block
            CML cml = new CML();
            cml.setCreate(new CMLCreate[]{create});
            cml.setAddAspect(new CMLAddAspect[]{addAspect});
            // Issue CML statement via Repository Web Service and retrieve result
            // Note: Batching of multiple statements into a single web call
            UpdateResult[] result = WebServiceFactory.getRepositoryService().update(cml);
            Reference reference = result[0].getDestination();
            //
            // Write some content
            //
            return reference;
        } catch (RepositoryFault ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String upload(File file, String encoding, String taskSubject, String sender, List<String> recipients) {
        try {
            String name = file.getName();
            String mimetype = getMimeType(file);
            byte[] bacontent = getBytesFromFile(file);

            AuthenticationUtils.startSession(ALFRESCO_ADMIN_USERNAME, ALFRESCO_ADMIN_PASSWORD);
            Store storeRef = new Store(Constants.WORKSPACE_STORE, "SpacesStore");
            ParentReference userFolderRef = new ParentReference(storeRef, null, "/app:company_home/app:user_homes/cm:" + sender, Constants.ASSOC_CONTAINS, null);
            ParentReference taskFolderRef = new ParentReference(storeRef, null, "/app:company_home/app:user_homes/cm:" + sender + "/cm_x003a_" + URLEncoder.encode(taskSubject, "UTF-8"), Constants.ASSOC_CONTAINS, null);
            Reference content = null;
            AccessControlServiceSoapBindingStub acs = WebServiceFactory.getAccessControlService();

            //user folder rights
            Predicate predUserFolder = new Predicate(new Reference[]{userFolderRef}, storeRef, null);
            for (String recipient : recipients) {
                acs.addACEs(predUserFolder, new ACE[]{new ACE(recipient, "Consumer", AccessStatus.acepted)});
            }
            acs.addACEs(predUserFolder, new ACE[]{new ACE(sender, "All", AccessStatus.acepted)});

            //create task folder
            createNode(userFolderRef, Constants.TYPE_FOLDER, URLEncoder.encode(taskSubject, "UTF-8"));




//            //task folder rights
//            Predicate predFolder = new Predicate(new Reference[]{taskFolderRef}, storeRef, null);
//            for (String recipient : recipients) {
//                acs.addACEs(predFolder, new ACE[]{new ACE(recipient, "Consumer", AccessStatus.acepted)});
//            }
//            acs.addACEs(predFolder, new ACE[]{new ACE(sender, "All", AccessStatus.acepted)});


            content = createNode(taskFolderRef, Constants.TYPE_CONTENT, URLEncoder.encode(name, "UTF-8"));


            if (content == null) {
                return null;
            }







            ContentServiceSoapBindingStub contentService = WebServiceFactory.getContentService();
            ContentFormat contentFormat = new ContentFormat(mimetype, encoding);
            Content result = contentService.write(content, Constants.PROP_CONTENT, bacontent, contentFormat);
            return result.getNode().getPath();
        } catch (AuthenticationFault ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.io.IOException ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static File download(String sender, String path) {
        try {
            AuthenticationUtils.startSession(ALFRESCO_ADMIN_USERNAME, ALFRESCO_ADMIN_PASSWORD);
            Store storeRef = new Store(Constants.WORKSPACE_STORE, "SpacesStore");
            Reference contentRef = new Reference(storeRef, null, path);

            ContentServiceSoapBindingStub contentService = WebServiceFactory.getContentService();
            Content[] readResult = contentService.read(new Predicate(new Reference[]{contentRef}, storeRef, null), Constants.PROP_CONTENT);
            Content content = readResult[0];
            File file = File.createTempFile("mewit_file_", null);
            ContentUtils.copyContentToFile(content, file);
            return file;
        } catch (ContentFault ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationFault ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AlfrescoWSUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getMimeType(File file) {
        return (new MimetypesFileTypeMap()).getContentType(file);
    }

    public static byte[] getBytesFromFile(File file) throws IOException, Exception {
        FileInputStream fis = new FileInputStream(file);
        return ContentUtils.convertToByteArray(fis);
//        InputStream is = new FileInputStream(file);
//
//        // Get the size of the file
//        long length = file.length();
//
//        if (length > Integer.MAX_VALUE) {
//            // File is too large
//        }
//
//        // Create the byte array to hold the data
//        byte[] bytes = new byte[(int) length];
//
//        // Read in the bytes
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
//            offset += numRead;
//        }
//
//        // Ensure all the bytes have been read in
//        if (offset < bytes.length) {
//            throw new IOException("Could not completely read file " + file.getName());
//        }
//
//        // Close the input stream and return bytes
//        is.close();
//        return bytes;
    }
}
