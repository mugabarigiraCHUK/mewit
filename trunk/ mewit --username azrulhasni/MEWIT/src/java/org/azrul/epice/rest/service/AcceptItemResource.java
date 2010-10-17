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
import org.azrul.epice.rest.dto.AcceptItemRequest;
import org.azrul.epice.rest.dto.AcceptItemResponse;
import org.azrul.epice.rest.dto.FindItemByIdRequest;
import org.azrul.epice.rest.dto.FindItemByIdResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */

@Path("acceptItem")
public class AcceptItemResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of AcceptItemResource */
    public AcceptItemResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.AcceptItemResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
       XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("AcceptItemResponse", AcceptItemResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("AcceptItemRequest", AcceptItemRequest.class);

        AcceptItemResponse errorResponse = new AcceptItemResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("ACCEPT ITEM ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            AcceptItemRequest oRequest = (AcceptItemRequest) reader.fromXML(request);
            AcceptItemResponse oResponse = doService(oRequest);

            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public AcceptItemResponse doService(AcceptItemRequest oRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item item = itemDAO.findItemById(oRequest.getItemID());
        item.setStatus(Item.Status.ACCEPTED);
        item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
        AcceptItemResponse oResponse = new AcceptItemResponse();
        oResponse.setItem(item);
        oResponse.setSessionId(oRequest.getSessionId());
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of AcceptItemResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
