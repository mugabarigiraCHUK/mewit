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
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.RejectItemRequest;
import org.azrul.epice.rest.dto.RejectItemResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */

@Path("rejectItem")
public class RejectItemResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of RejectItemResource */
    public RejectItemResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.RejectItemResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("RejectItemResponse", RejectItemResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("RejectItemRequest", RejectItemRequest.class);

        RejectItemResponse errorResponse = new RejectItemResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("REJECT ITEM ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            RejectItemRequest oRequest = (RejectItemRequest) reader.fromXML(request);
            RejectItemResponse oResponse = doService(oRequest);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RejectItemResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public RejectItemResponse doService(RejectItemRequest oRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item item = itemDAO.findItemById(oRequest.getItemID());
        item.setStatus(Item.Status.REJECTED);
        item.setReasonForRejectionOfTask(oRequest.getReason());
        item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
        RejectItemResponse oResponse = new RejectItemResponse();
        oResponse.setItem(item);
        oResponse.setSessionId(oRequest.getSessionId());
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of RejectItemResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
