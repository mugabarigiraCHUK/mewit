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
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.domain.Person;
import org.azrul.epice.exception.UserNotExistException;
import org.azrul.epice.rest.dto.FindPersonRequest;
import org.azrul.epice.rest.dto.FindPersonResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */

@Path("findPerson")
public class FindPersonResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of FindPersonResource */
    public FindPersonResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.FindPersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("FindPersonResponse", FindPersonResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("FindPersonRequest", FindPersonRequest.class);

        FindPersonResponse errorFindPersonResponse = new FindPersonResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("FindPerson ERROR");
        errorFindPersonResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            FindPersonRequest oRequest = (FindPersonRequest) reader.fromXML(request);
            FindPersonResponse findPersonResponse = doService(oRequest);
            return URLEncoder.encode(writer.toXML(findPersonResponse), "UTF-8");
        } catch (UserNotExistException ex) {
            try {
                return URLEncoder.encode(writer.toXML(errorFindPersonResponse), "UTF-8");
            } catch (UnsupportedEncodingException ex1) {
                Logger.getLogger(FindPersonResource.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorFindPersonResponse);
    }

    public FindPersonResponse doService(FindPersonRequest oRequest) throws UserNotExistException {
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        Person person = personDAO.getUserInfo(oRequest.getUserId());
        FindPersonResponse findPersonResponse = new FindPersonResponse();
        findPersonResponse.setPerson(person);
        return findPersonResponse;
    }

    /**
     * PUT method for updating or creating an instance of FindPersonResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
