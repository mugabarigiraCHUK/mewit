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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.rest.dto.CreatePersonRequest;
import org.azrul.epice.rest.dto.CreatePersonResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */

@Path("createPerson")
public class CreatePersonResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of CreatePersonResource */
    public CreatePersonResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.CreatePersonResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("CreatePersonResponse", CreatePersonResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("CreatePersonRequest", CreatePersonRequest.class);

        CreatePersonResponse errorResponse = new CreatePersonResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("FIND ITEMBY ID ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {
            CreatePersonResponse oResponse = doService(params, reader);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public CreatePersonResponse doService(MultivaluedMap<String, String> params, XStream reader) throws UnsupportedEncodingException {
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
        CreatePersonRequest oRequest = (CreatePersonRequest) reader.fromXML(request);
        personDAO.addNewUser(oRequest.getPerson());
        CreatePersonResponse oResponse = new CreatePersonResponse();
        oResponse.setPerson(oRequest.getPerson());
        oResponse.setSessionId(oRequest.getSessionId());
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of CreatePersonResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
