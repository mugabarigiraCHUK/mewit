/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.n
 */
package org.azrul.epice.rest.service;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.CreateAndSendItemRequest;
import org.azrul.epice.rest.dto.CreateAndSendItemResponse;
import org.azrul.epice.rest.dto.LoginResponse;
import org.azrul.epice.util.AlfrescoWSUtils;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("createAndSendItem")
public class CreateAndSendItemResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of LoginResource */
    public CreateAndSendItemResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.LoginResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getJson() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("CreateAndSendItemResponse", CreateAndSendItemResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("CreateAndSendItemRequest", CreateAndSendItemRequest.class);

        LoginResponse errorResponse = new LoginResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("PROBLEM_CREATING_OR_SENDING_ITEM");

        errorResponse.setErrors(errors);
        try {

            MultivaluedMap<String, String> params = context.getQueryParameters();
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            if (request == null) {
                return writer.toXML(errorResponse);
            }
            CreateAndSendItemRequest oRequest = (CreateAndSendItemRequest) reader.fromXML(request);
            CreateAndSendItemResponse res = doService(oRequest);
            return URLEncoder.encode(writer.toXML(res), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateAndSendItemResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public CreateAndSendItemResponse doService(CreateAndSendItemRequest oRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item parent = null; //when creating, parent is always null, parent is not null only when delegating
        List<Item> items = new ArrayList<Item>(itemDAO.createItems(oRequest.getSessionId(), oRequest.getRecipients(), oRequest.getSupervisors(), oRequest.getTags(), oRequest.getLinks(), parent, oRequest.getTask(), oRequest.getSubject(), oRequest.getStartDate(), oRequest.getDeadline(), true, oRequest.getFiles(), oRequest.getPriority()));
        CreateAndSendItemResponse res = new CreateAndSendItemResponse();
        res.setItems(items);
        res.setCarriedQuery(oRequest.getCarriedQuery());
        res.setSessionId(oRequest.getSessionId());
        return res;
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putJson(String content) {
    }
}
