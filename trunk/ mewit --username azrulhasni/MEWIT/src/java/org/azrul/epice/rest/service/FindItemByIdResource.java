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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;

import org.azrul.epice.db4o.daoimpl.DB4OItemDAO;
import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.FindItemByIdRequest;
import org.azrul.epice.rest.dto.FindItemByIdResponse;
import org.azrul.epice.rest.dto.SearchRequest;
import org.azrul.epice.rest.dto.SearchResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("findItemById")
public class FindItemByIdResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of FindItemByIdResource */
    public FindItemByIdResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.FindItemByIdResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("FindItemByIdResponse", FindItemByIdResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("FindItemByIdRequest", FindItemByIdRequest.class);

        FindItemByIdResponse errorResponse = new FindItemByIdResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("FIND ITEMBY ID ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");

            FindItemByIdRequest fibiRequest = (FindItemByIdRequest) reader.fromXML(request);
            FindItemByIdResponse fibiResponse = doService(fibiRequest);

            return URLEncoder.encode(writer.toXML(fibiResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public FindItemByIdResponse doService(FindItemByIdRequest fibiRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item item = itemDAO.findItemById(fibiRequest.getItemID());
        itemDAO.labelWithType(item, fibiRequest.getSessionId());
        FindItemByIdResponse fibiResponse = new FindItemByIdResponse();
        fibiResponse.setItem(item);
        fibiResponse.setSessionId(fibiRequest.getSessionId());
        return fibiResponse;
    }

    /**
     * PUT method for updating or creating an instance of FindItemByIdResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
