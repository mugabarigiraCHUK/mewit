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
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.exception.UserNotExistException;
import org.azrul.epice.rest.dto.RetrievePasswordRequest;
import org.azrul.epice.rest.dto.RetrievePasswordResponse;
import org.azrul.epice.util.SendMailUtil;

/**
 * REST Web Service
 *
 * @author azrulm
 */
@Path("retrivePassword")
public class RetrivePasswordResource {

    private static Executor executor = Executors.newFixedThreadPool(10);
    @Context
    private UriInfo context;

    /** Creates a new instance of RetrivePasswordResource */
    public RetrivePasswordResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.RetrivePasswordResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("RetrievePasswordResponse", RetrievePasswordResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("RetrievePasswordRequest", RetrievePasswordRequest.class);

        RetrievePasswordResponse errorResponse = new RetrievePasswordResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("MODIFY PASSWORD ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            RetrievePasswordRequest oRequest = (RetrievePasswordRequest) reader.fromXML(request);
            RetrievePasswordResponse oResponse = doService(oRequest);
            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return writer.toXML(errorResponse);
        }
    }

    /**
     * PUT method for updating or creating an instance of RetrivePasswordResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    private RetrievePasswordResponse doService(final RetrievePasswordRequest oRequest) {
        RetrievePasswordResponse oResponse = new RetrievePasswordResponse();
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        final String password = UUID.randomUUID().toString().replace("-", "");
        try {
            personDAO.changeUserPasword(oRequest.getEmail(), password);
            final ResourceBundle props = ResourceBundle.getBundle("org.azrul.epice.config.epice");
            executor.execute(new Runnable() {

                public void run() {
                    String inviteText = props.getString("EMAIL_REACTIVATE_TEXT").
                            replace("%%Link%%", props.getString("EPICE_URL")).
                            replace("%%Email%%", oRequest.getEmail()).
                            replace("%%Key%%", password);
                    SendMailUtil.sendMail(oRequest.getEmail(), inviteText, props.getString("EMAIL_REACTIVATE_TITLE"));
                }
            });
        } catch (UserNotExistException ex) {
             Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, ex);
        }
        return oResponse;
    }
}
