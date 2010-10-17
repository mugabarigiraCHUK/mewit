/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.rest.service;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.azrul.epice.dao.AttachmentDAO;
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.AttachmentDAOFactory;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.UploadFileRequest;
import org.azrul.epice.rest.dto.UploadFileResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("uploadFile")
public class UploadFileResource {

    @Context
    private UriInfo context;

    @Context
    javax.servlet.http.HttpServletRequest servletRequest;

    /** Creates a new instance of UploadFileResource */
    public UploadFileResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.UploadFileResource
     * @return an instance of java.lang.String
     */
    @POST
    
    @Produces("application/xml")
    public String getXml() throws FileNotFoundException, IOException {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("UploadFileResponse", UploadFileResponse.class);

        System.out.println("type="+servletRequest.getContentType());
        System.out.println("length="+servletRequest.getContentLength());

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("UploadFileRequest", UploadFileRequest.class);

        UploadFileResponse errorUploadFileResponse = new UploadFileResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("UPLOAD FILE ERROR");
        errorUploadFileResponse.setErrors(errors);
        MultivaluedMap<String, String> params = context.getQueryParameters();
        try {
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            UploadFileRequest oRequest = (UploadFileRequest) reader.fromXML(request);
            UploadFileResponse oResponse = doService( oRequest);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UploadFileResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorUploadFileResponse);
    }

    public UploadFileResponse doService(UploadFileRequest oRequest) throws FileNotFoundException, IOException {
        ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
        File dir = new File(props.getString("FILE_REPOSITORY") + "/" + oRequest.getDirectory());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(props.getString("FILE_REPOSITORY") + "/" + oRequest.getDirectory() + "/" + oRequest.getFileName());
        file.createNewFile();
        OutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int len;
        while ((len = servletRequest.getInputStream().read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        servletRequest.getInputStream().close();
        UploadFileResponse oResponse = new UploadFileResponse();
        oResponse.setFile(oRequest.getDirectory() + "/" + oRequest.getFileName());
        oResponse.setSessionId(oRequest.getSessionId());
        if (oRequest.getItemId()!=null){
            ItemDAO itemDAO = ItemDAOFactory.getInstance();
            Item item = itemDAO.findItemById(oRequest.getItemId());
            FileRepositoryDAO fileRepoDAO = FileRepositoryDAOFactory.getInstance();
            AttachmentDAO attachmentDAO = AttachmentDAOFactory.getInstance();
            Attachment attachment = attachmentDAO.createWithoutPersisting(oRequest.getDirectory() + "/" + oRequest.getFileName(),oRequest.getSessionId());
            FileRepository fileRepo = fileRepoDAO.findFileRepositoryById(item.getFileRepository().getId());
            fileRepoDAO.addNewAttachment(fileRepo, attachment);
            //refresh
            String user = oRequest.getSessionId();
            Item itemOut = itemDAO.refresh(item);
            itemDAO.labelWithType(itemOut, user);
            oResponse.setItem(itemOut);
        }
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of UploadFileResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
