/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.rest.service;

import com.thoughtworks.xstream.XStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
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
import org.azrul.epice.rest.dto.AddAttachmentRequest;
import org.azrul.epice.rest.dto.AddAttachmentResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("addAttachment")
public class AddAttachmentResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of AddAttachmentResource */
    public AddAttachmentResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.AddAttachmentResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("AddAttachmentResponse", AddAttachmentResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("AddAttachmentRequest", AddAttachmentRequest.class);

        AddAttachmentResponse errorResponse = new AddAttachmentResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("ACCEPT ITEM ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            AddAttachmentRequest oRequest = (AddAttachmentRequest) reader.fromXML(request);
            AddAttachmentResponse oResponse = doService(oRequest);

            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    /**
     * PUT method for updating or creating an instance of AddAttachmentResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    public AddAttachmentResponse doService(AddAttachmentRequest oRequest) {
        AddAttachmentResponse oResponse = new AddAttachmentResponse();
        FileRepositoryDAO fileRepoDAO = FileRepositoryDAOFactory.getInstance();
        AttachmentDAO attachmentDAO = AttachmentDAOFactory.getInstance();

        oResponse.setSessionId(oRequest.getSessionId());
        if (oRequest.getItem() != null) {
            ItemDAO itemDAO = ItemDAOFactory.getInstance();
            Item item = itemDAO.findItemById(oRequest.getItem().getId());
            if (item != null) {

                Attachment attachment = attachmentDAO.createWithoutPersisting(oRequest.getFile(), oRequest.getSessionId());
                FileRepository fileRepo = fileRepoDAO.findFileRepositoryById(item.getFileRepository().getId());
                String user = oRequest.getSessionId();

                //save attached file
                fileRepo = fileRepoDAO.addNewAttachment(fileRepo, attachment);

                Item ritem = oRequest.getItem();
                ritem.setFileRepository(fileRepo);
                itemDAO.labelWithType(ritem, user);
                oResponse.setItem(ritem);
            } else {
                //item is still not persisted yet
                //do add attachment but in non-persistence mode

//                FileRepository fileRepo = fileRepoDAO.findFileRepositoryById( oRequest.getFileRepoId());
//                if (fileRepo==null){
//                    fileRepo = fileRepoDAO.create(oRequest.getFileRepoId(),oRequest.getSessionId(),"");
//                }
                FileRepository fileRepo= oRequest.getItem().getFileRepository();
                if (oRequest.getItem().getFileRepository()==null){
                    fileRepo = fileRepoDAO.createWithoutPersisting(oRequest.getSessionId(), "");
                }
                if (fileRepo.getOwner()==null){
                    fileRepo.setOwner(oRequest.getSessionId());
                }
                fileRepo.setId(oRequest.getItem().getFileRepository().getId());
                Attachment attachment = attachmentDAO.createWithoutPersisting(oRequest.getFile(), oRequest.getSessionId());
                fileRepo.getAttachments().add(attachment);

                Item ritem = oRequest.getItem();
                ritem.setFileRepository(fileRepo);
                oResponse.setItem(ritem);
            }
        }
        return oResponse;
    }
}
