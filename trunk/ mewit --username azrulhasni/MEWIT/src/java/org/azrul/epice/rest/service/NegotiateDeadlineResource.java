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
import org.azrul.epice.rest.dto.NegotiateDeadlineRequest;
import org.azrul.epice.rest.dto.NegotiateDeadlineResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */

@Path("negotiateDeadline")
public class NegotiateDeadlineResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of NegotiateDeadlineResource */
    public NegotiateDeadlineResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.NegotiateDeadlineResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("NegotiateDeadlineResponse", NegotiateDeadlineResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("NegotiateDeadlineRequest", NegotiateDeadlineRequest.class);

        NegotiateDeadlineResponse errorResponse = new NegotiateDeadlineResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("NEGOTIOATE DEADLINE ERROR");
        errorResponse.setErrors(errors);
        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            NegotiateDeadlineRequest oRequest = (NegotiateDeadlineRequest) reader.fromXML(request);
            NegotiateDeadlineResponse oResponse = doService(oRequest);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public NegotiateDeadlineResponse doService(NegotiateDeadlineRequest oRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item item = itemDAO.findItemById(oRequest.getItemID());
        item.setStatus(Item.Status.NEGOTIATED);
        item.setReasonForNegotiatiationOfDeadLine(oRequest.getReasonForNegotiationOfDeadline());
        item.setNegotiatedDeadLine(oRequest.getNewProposedDeadline());
        item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
        NegotiateDeadlineResponse oResponse = new NegotiateDeadlineResponse();
        oResponse.setItem(item);
        oResponse.setSessionId(oRequest.getSessionId());
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of NegotiateDeadlineResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
