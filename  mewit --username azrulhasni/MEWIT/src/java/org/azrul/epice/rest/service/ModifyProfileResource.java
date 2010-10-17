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
import org.azrul.epice.rest.dto.ModifyProfileRequest;
import org.azrul.epice.rest.dto.ModifyProfileResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */

@Path("modifyProfile")
public class ModifyProfileResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of ModifyProfileResource */
    public ModifyProfileResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.ModifyProfileResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("ModifyProfileResponse", ModifyProfileResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("ModifyProfileRequest", ModifyProfileRequest.class);

        ModifyProfileResponse errorResponse = new ModifyProfileResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("MODIFY PROFILE ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            ModifyProfileRequest oRequest = (ModifyProfileRequest) reader.fromXML(request);
            ModifyProfileResponse oResponse = doService(oRequest);
             return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch ( UserNotExistException ex){
            errors.add("User "+ex.getNonExistingUserEmail()+" does not exist");
        }
        return writer.toXML(errorResponse);
    }

    public ModifyProfileResponse doService(ModifyProfileRequest oRequest) throws UserNotExistException {
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        Person person = personDAO.getUserInfo(oRequest.getSessionId());

        person.setCellPhoneWork(oRequest.getPerson().getCellPhoneWork());
        person.setEmailWork(oRequest.getPerson().getEmailWork());
        person.setFirstName(oRequest.getPerson().getFirstName());
        person.setLastName(oRequest.getPerson().getLastName());
        person.setOrganization(oRequest.getPerson().getOrganization());
        person.setPotsPhoneWork(oRequest.getPerson().getPotsPhoneWork());
        person.setPassword(oRequest.getPerson().getPassword());
        personDAO.updateUser(oRequest.getSessionId(), person);
        Person person2 = personDAO.getUserInfo(oRequest.getSessionId());
        ModifyProfileResponse oResponse = new ModifyProfileResponse();
        oResponse.setSessionId(oRequest.getSessionId());
        oResponse.setPerson(person2);
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of ModifyProfileResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
