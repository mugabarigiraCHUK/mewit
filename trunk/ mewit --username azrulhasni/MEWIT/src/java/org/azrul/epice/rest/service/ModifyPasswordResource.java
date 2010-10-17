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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.exception.UserNotExistException;
import org.azrul.epice.exception.WrongPasswordException;
import org.azrul.epice.rest.dto.ModifyPasswordRequest;
import org.azrul.epice.rest.dto.ModifyPasswordResponse;
import org.azrul.epice.util.OpenDSUtil;
import org.opends.server.admin.client.ldap.JNDIDirContextAdaptor;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("modifyPassword")
public class ModifyPasswordResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of ModifyPasswordResource */
    public ModifyPasswordResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.ModifyPasswordResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("ModifyPasswordResponse", ModifyPasswordResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("ModifyPasswordRequest", ModifyPasswordRequest.class);

        ModifyPasswordResponse errorResponse = new ModifyPasswordResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("MODIFY PASSWORD ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            ModifyPasswordRequest oRequest = (ModifyPasswordRequest) reader.fromXML(request);
            ModifyPasswordResponse oResponse = doService(oRequest);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return writer.toXML(errorResponse);
        }
    }

    public ModifyPasswordResponse doService(ModifyPasswordRequest oRequest) throws UserNotExistException {
        ModifyPasswordResponse oResponse = new ModifyPasswordResponse();
        oResponse.setSessionId(oRequest.getSessionId());
        try {
            PersonDAO personDAO = PersonDAOFactory.getInstance();
            personDAO.changeUserPasword(oRequest.getSessionId(), oRequest.getOldPassword(), oRequest.getNewPassword());
        } catch (WrongPasswordException e) {
            List<String> wrongPassword = new ArrayList<String>();
            wrongPassword.add("WRONG_PASSWORD");
            oResponse.setErrors(wrongPassword);
        }
        return oResponse;

    }

    /**
     * PUT method for updating or creating an instance of ModifyPasswordResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
