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
import java.util.TreeSet;
import java.util.List;
import java.util.Set;
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
import org.azrul.epice.dao.AttachmentDAO;
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.AttachmentDAOFactory;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.Attachment;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.rest.dto.AcceptItemRequest;
import org.azrul.epice.rest.dto.AcceptItemResponse;
import org.azrul.epice.rest.dto.DelegateItemRequest;
import org.azrul.epice.rest.dto.DelegateItemResponse;

/**
 * REST Web Service
 *
 * @author azrulhasni
 */
@Path("delegateItem")
public class DelegateItemResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of DelegateItemResource */
    public DelegateItemResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.DelegateItemResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("DelegateItemResponse", DelegateItemResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("DelegateItemRequest", DelegateItemRequest.class);

        DelegateItemResponse errorResponse = new DelegateItemResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("DELEGATE ITEM ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");
            if (request == null) {
                return writer.toXML(errorResponse);
            }
            DelegateItemRequest req = (DelegateItemRequest) reader.fromXML(request);
            DelegateItemResponse res = doService(req);
            return URLEncoder.encode(writer.toXML(res), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

    public DelegateItemResponse doService(DelegateItemRequest req) {
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        Item parent = itemDAO.findItemById(req.getParentId());
        DelegateItemResponse res = new DelegateItemResponse();
        if (req.getSessionId().equals(parent.getRecipient()) && (Item.Status.ACCEPTED).equals(parent.getStatus())) {
            Set<Item> children = itemDAO.createItems(req.getSessionId(), req.getRecipients(), req.getSupervisors(), req.getTags(), req.getLinks(), parent, req.getTask(), req.getSubject(), req.getStartDate(), req.getDeadline(), true, req.getFileRepository(), req.getPriority());
            parent = itemDAO.refresh(parent);
            SearchItemsQuery itemsQuery = req.getCarriedQuery();

            res.setParentItem(parent);
            res.setCarriedQuery(itemsQuery);
            res.setSessionId(req.getSessionId());
            res.setChildItems(new ArrayList<Item>(children));
        }
        return res;
    }

    /**
     * PUT method for updating or creating an instance of DelegateItemResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
