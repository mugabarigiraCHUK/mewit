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
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.GiveCommentsOnFeedbackRequest;
import org.azrul.epice.rest.dto.GiveCommentsOnFeedbackResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("giveCommentsOnFeedback")
public class GiveCommentsOnFeedbackResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of GiveCommentsToFeedbackResource */
    public GiveCommentsOnFeedbackResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.GiveCommentsToFeedbackResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("GiveCommentsOnFeedbackResponse", GiveCommentsOnFeedbackResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("GiveCommentsOnFeedbackRequest", GiveCommentsOnFeedbackRequest.class);

        GiveCommentsOnFeedbackResponse errorResponse = new GiveCommentsOnFeedbackResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("GIVE COMMENT TO FEEDBACK ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");

            GiveCommentsOnFeedbackRequest oRequest = (GiveCommentsOnFeedbackRequest) reader.fromXML(request);
            GiveCommentsOnFeedbackResponse oResponse = doService(oRequest);

            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public GiveCommentsOnFeedbackResponse doService(GiveCommentsOnFeedbackRequest oRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item item = itemDAO.findItemById(oRequest.getItemID());
        if (oRequest.isApproved() == false) {
            item.setStatus(Item.Status.NEED_REDO);
            int redoCounter = item.getRedoCounter();
            redoCounter++;
            item.setRedoCounter(redoCounter);
        } else {
            item.setStatus(Item.Status.DONE_CONFIRMED);
        }
        item.setCommentsOnFeedback(oRequest.getComments());
        item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
        GiveCommentsOnFeedbackResponse oResponse = new GiveCommentsOnFeedbackResponse();
        oResponse.setItem(item);
        oResponse.setSessionId(oRequest.getSessionId());
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of GiveCommentsToFeedbackResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
