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
import org.azrul.epice.dao.query.QueryResult;
import org.azrul.epice.dao.factory.ItemDAOFactory;

import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.SearchRequest;
import org.azrul.epice.rest.dto.SearchResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("search")
public class SearchResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of SearchResource */
    public SearchResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.SearchResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("SearchResponse", SearchResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("SearchRequest", SearchRequest.class);

        SearchResponse errorSearchResponse = new SearchResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("SEARCH ERROR");
        errorSearchResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {
            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            SearchRequest searchRequest = (SearchRequest) reader.fromXML(request);
            SearchResponse searchResponse = doLogic(searchRequest);
            String itemsString = writer.toXML(searchResponse);
            return URLEncoder.encode(itemsString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorSearchResponse);
    }

    public SearchResponse doLogic(SearchRequest searchRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        SearchResponse searchResponse = new SearchResponse();
        QueryResult result = itemDAO.runItemsQuery(searchRequest.getSessionId(), searchRequest.getQuery(), searchRequest.getStartRow(), searchRequest.getResultCount());
        if (result != null) {

            searchResponse.setLastRequest(searchRequest);
            searchResponse.setStartRow(result.getStartRow());
            if (result.getItems() != null) {
                searchResponse.setResultCount(result.getItems().size());
                searchResponse.setItems(result.getItems());
            } else {
                searchResponse.setResultCount(0);
                searchResponse.setItems(new ArrayList<Item>());
            }
            searchResponse.setTotalResultCount(result.getTotal().intValue());


            searchResponse.setSessionId(searchRequest.getSessionId());
            searchResponse.setCarriedQuery(searchRequest.getQuery());
        }
        return searchResponse;
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
