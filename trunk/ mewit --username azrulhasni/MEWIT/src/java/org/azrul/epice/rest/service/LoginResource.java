/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.rest.service;

import org.azrul.epice.jpa.daoimpl.JPAPersonDAO;
import com.thoughtworks.xstream.XStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.List;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.PersonDAO;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.factory.PersonDAOFactory;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Person;
import org.azrul.epice.rest.dto.LoginRequest;
import org.azrul.epice.rest.dto.LoginResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of LoginResource */
    public LoginResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.LoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getJson() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("LoginResponse", LoginResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("LoginRequest", LoginRequest.class);

        LoginResponse errorLoginResponse = new LoginResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("LOGIN_ERROR");
        errorLoginResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            if (request == null) {
                return writer.toXML(errorLoginResponse);
            }
            System.out.println(request);
            LoginRequest loginRequest = (LoginRequest) reader.fromXML(request);
            LoginResponse loginResponse = doService(loginRequest);
            return writer.toXML(loginResponse);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorLoginResponse);


    }

    public LoginResponse doService(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        PersonDAO personDAO = PersonDAOFactory.getInstance();
     
        if (personDAO.isValid(loginRequest.getLogin(), loginRequest.getPassword())) {
            Person person = personDAO.getUserInfo(loginRequest.getLogin());
            loginResponse.setSessionId(loginRequest.getLogin());
            loginResponse.setPerson(person);
        } else {
            List<String> wrongPassword = new ArrayList<String>();
            wrongPassword.add("WRONG_PASSWORD");
            loginResponse.setErrors(wrongPassword);
        }
        return loginResponse;
    }

    public LoginResponse doLDAPService(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        PersonDAO personDAO = PersonDAOFactory.getInstance();
        
        if (personDAO.isValid(loginRequest.getLogin(), loginRequest.getPassword())) {
//            SearchItemsQuery itemsQuery = new SearchItemsQuery();
//            List<String> filters = new ArrayList<String>();
//            filters.add("SENT_ITEMS_NEEDED_ATTENTION");
//            filters.add("RECEIVED_ITEMS_NEEDED_ATTENTION");
//            itemsQuery.setFilters(filters);
//            itemsQuery.setUseOR(true);
//
//            List<Item> items = itemDAO.runItemsQuery(loginRequest.getLogin(), itemsQuery);
//            loginResponse.setItems(items);
            Person person = personDAO.getUserInfo(loginRequest.getLogin());
            loginResponse.setSessionId(loginRequest.getLogin());
            loginResponse.setPerson(person);
//            loginResponse.setQuery(itemsQuery);
        } else {
            List<String> wrongPassword = new ArrayList<String>();
            wrongPassword.add("WRONG_PASSWORD");
            loginResponse.setErrors(wrongPassword);
        }
        return loginResponse;
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putJson(String content) {
    }
}
