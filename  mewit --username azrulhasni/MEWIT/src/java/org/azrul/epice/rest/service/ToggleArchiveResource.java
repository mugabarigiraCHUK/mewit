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
import org.azrul.epice.exception.ItemNotReachableException;
import org.azrul.epice.rest.dto.ToggleArchiveRequest;
import org.azrul.epice.rest.dto.ToggleArchiveResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("toggleArchive")
public class ToggleArchiveResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of ToggleArchiveResource */
    public ToggleArchiveResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.ToggleArchiveResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("ToggleArchiveResponse", ToggleArchiveResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("ToggleArchiveRequest", ToggleArchiveRequest.class);

        ToggleArchiveResponse errorResponse = new ToggleArchiveResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("TOGGLE ARCHIVE ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");

            ToggleArchiveRequest oRequest = (ToggleArchiveRequest) reader.fromXML(request);
            ToggleArchiveResponse oResponse = doService(oRequest);

            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (ItemNotReachableException e) {
            //do nothing
        }
        return writer.toXML(errorResponse);
    }

    public ToggleArchiveResponse doService(ToggleArchiveRequest oRequest) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        ToggleArchiveResponse oResponse = new ToggleArchiveResponse();
        if ((Boolean.TRUE).equals(oRequest.getArchive())){
            List<Item> items = itemDAO.findItemsById(oRequest.getItemIds());
            oResponse.setItems(itemDAO.archiveItems(oRequest.getSessionId(), items));
        }else{
             List<Item> items = itemDAO.findItemsById(oRequest.getItemIds());
             oResponse.setItems(itemDAO.unarchiveItems(oRequest.getSessionId(), items));
        }
//        if (!(item.getStatus() == Item.Status.REJECTED || item.getStatus() == Item.Status.DONE_CONFIRMED)) {
//            List<String> errors = new ArrayList<String>();
//            errors.add("TOGGLE ARCHIVE ERROR");
//            errors.add("Cannot archive items");
//            oResponse.setErrors(errors);
//        } else {
//            List<Item> items = new ArrayList<Item>();
//            items.add(item);
//            if (item.getSender().equals(oRequest.getSessionId())) {
//                if (item.isArchivedForSender()) {
//                    itemDAO.unarchiveItems(oRequest.getSessionId(), items);
//                } else {
//                    itemDAO.archiveItems(oRequest.getSessionId(), items);
//                }
//            } else {
//                if (item.isArchivedForRecipient()) {
//                    itemDAO.unarchiveItems(oRequest.getSessionId(), items);
//                } else {
//                    itemDAO.archiveItems(oRequest.getSessionId(), items);
//                }
//            }
//            oResponse.setItem(item);
//            oResponse.setSessionId(oRequest.getSessionId());
//        }
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of ToggleArchiveResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
