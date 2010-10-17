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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
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
import org.azrul.epice.dao.FileRepositoryDAO;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.factory.FileRepositoryDAOFactory;
import org.azrul.epice.dao.factory.ItemDAOFactory;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;
import org.azrul.epice.domain.Item.Priority;
import org.azrul.epice.rest.dto.UpdateItemRequest;
import org.azrul.epice.rest.dto.UpdateItemResponse;

/**
 * REST Web Service
 *
 * @author azrulm
 */
@Path("updateItem")
public class UpdateItemResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of UpdateItemResource */
    public UpdateItemResource() {
    }

    /**
     * Retrieves representation of an instance of org.azrul.epice.rest.service.UpdateItemResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/xml")
    public String getXml() {
        XStream writer = new XStream();
        writer.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        writer.alias("UpdateItemResponse", UpdateItemResponse.class);

        XStream reader = new XStream();
        reader.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);
        reader.alias("UpdateItemRequest", UpdateItemRequest.class);

        UpdateItemResponse errorResponse = new UpdateItemResponse();
        List<String> errors = new ArrayList<String>();
        errors.add("GIVE FEEDBACK ERROR");
        errorResponse.setErrors(errors);

        MultivaluedMap<String, String> params = context.getQueryParameters();

        try {

            String request = URLDecoder.decode(params.getFirst("REQUEST"), "UTF-8");

            UpdateItemRequest oRequest = (UpdateItemRequest) reader.fromXML(request);


            UpdateItemResponse oResponse = doService(oRequest);

            return URLEncoder.encode(writer.toXML(oResponse), "UTF-8");

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return writer.toXML(errorResponse);
    }

//    public Item doModify(String itemId, String status, String type, String subject, String sender, String recipient, Date startDate, Date deadline, String task, Date negotiatedDeadline, String reasonForNegotiation, String reasonForRejection, String feedback, String commentsOnFeedback, String priority, boolean archived, boolean reference, String params) {
//        Item item = new Item();
//        item.setArchived(archived);
//        item.setCommentsOnFeedback(commentsOnFeedback);
//        item.setDeadLine(deadline);
//        item.setFeedback(feedback);
//        item.setId(itemId);
//        item.setNegotiatedDeadLine(negotiatedDeadline);
//        item.setReasonForNegotiatiationOfDeadLine(reasonForNegotiation);
//        item.setReasonForRejectionOfTask(reasonForRejection);
//        item.setStartDate(startDate);
//        item.setTask(task);
//        item.setPriority(Priority.valueOf(priority));
//        item.setSubject(subject);
//        item.setType(type);
//        item.setSender(sender);
//        item.setRecipient(recipient);
//        item.setStatus(Item.Status.valueOf(status));
//        StringTokenizer tokenizer = new StringTokenizer(params, ";");
//        Map<String, String> parameters = new HashMap<String, String>();
//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken();
//            String[] p = token.split(":");
//            parameters.put(p[0], p[1]);
//        }
//
//
//        return new Item();
//    }
    public UpdateItemResponse doService(UpdateItemRequest oRequest) {
        Item newitem = oRequest.getNewItem();
        String session = oRequest.getSessionId();
        ItemDAO itemDAO = ItemDAOFactory.getInstance();
        FileRepositoryDAO fileRepoDAO = FileRepositoryDAOFactory.getInstance();
        if (newitem.getSender() == null) {
            newitem.setSender(session);
        }
        itemDAO.labelWithType(newitem, session);
        UpdateItemResponse oResponse = new UpdateItemResponse();
        Map<String, String> params = oRequest.getParameters();
        if (newitem.getType().contains("SENT") == true || newitem.getType().contains("SUPERVISED") == true) {
            if (newitem.getStatus() == null) {//this must be an uncomplete new item
                Item item = itemDAO.findItemById(newitem.getId());
                if (item != null) {
                    item.setStatus(Item.Status.UNCONFIRMED);
                    item.setPriority(newitem.getPriority());
                    item.setRecipient(newitem.getRecipient());
                    item.setRecipients(newitem.getRecipients());
                    item.setSender(newitem.getSender());
                    item.setStartDate(newitem.getStartDate());
                    item.setDeadLine(newitem.getDeadLine());
                    item.setTask(newitem.getTask());
                    item.setSubject(newitem.getSubject());
                    item.setLinks(newitem.getLinks());
                    item.setTags(newitem.getTags());
                    //item.setParentId(newitem.getParentId());
                    item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                    if (newitem.getRecipients().size() > 1) {
                        itemDAO.createItems(newitem.getSender(), newitem.getRecipientsList(), newitem.getSupervisorsList(), newitem.getTagsList(), newitem.getLinksList(), null, newitem.getTask(), newitem.getSubject(), newitem.getStartDate(), newitem.getDeadLine(), false, item.getFileRepository(), Priority.NOT_SET);
                    }
                } else {
                    Item parent = null;
                    if (newitem.getParentId() != null) {
                        parent = itemDAO.findItemById(newitem.getParentId());
                    }
                    Set<Item> items = itemDAO.createItems(newitem.getSender(), newitem.getRecipientsList(), newitem.getSupervisorsList(), newitem.getTagsList(), newitem.getLinksList(), parent, newitem.getTask(), newitem.getSubject(), newitem.getStartDate(), newitem.getDeadLine(), false, newitem.getFileRepository(), Priority.NOT_SET);
                    if (items != null) {
                        for (Item it : items) {
                            if (it.getRecipient().equals(newitem.getRecipient())) {
                                item = it;
                                break;
                            }
                        }
                    }else{
                        items = new HashSet<Item>();
                    }
                }
                oResponse.setUpdatedItem(item);


            } else if (newitem.getStatus().equals(Item.Status.UNCONFIRMED)) {
            } else if (newitem.getStatus().equals(Item.Status.NEGOTIATED)) {

                Item item = itemDAO.findItemById(newitem.getId());
                item.setStatus(Item.Status.ACCEPTED);
                item.setDeadLine(newitem.getNegotiatedDeadLine());
                item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                oResponse.setUpdatedItem(item);
            } else if (newitem.getStatus().equals(Item.Status.ACCEPTED)) {
            } else if (newitem.getStatus().equals(Item.Status.DELEGATED)) {
            } else if (newitem.getStatus().equals(Item.Status.REJECTED)) {
            } else if (newitem.getStatus().equals(Item.Status.DONE_UNCONFIRMED)) {
                if (!("SUPERVISED").equals(newitem.getType())) {
                    if (("CONFIRMED").equals(params.get("CONFIRM_STATE"))) {

                        Item item = itemDAO.findItemById(newitem.getId());
                        item.setStatus(Item.Status.DONE_CONFIRMED);
                        item.setCommentsOnFeedback(newitem.getCommentsOnFeedback());
                        item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                        oResponse.setUpdatedItem(item);
                    } else if (("NOT_CONFIRMED").equals(params.get("CONFIRM_STATE"))) {

                        Item item = itemDAO.findItemById(newitem.getId());
                        item.setStatus(Item.Status.NEED_REDO);
                        int redoCounter = item.getRedoCounter();
                        redoCounter++;
                        item.setRedoCounter(redoCounter);
                        item.setCommentsOnFeedback(newitem.getCommentsOnFeedback());
                        item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                        oResponse.setUpdatedItem(item);
                    }
                }
            } else if (newitem.getStatus().equals(Item.Status.DONE_CONFIRMED)) {
            } else if (newitem.getStatus().equals(Item.Status.NEED_REDO)) {
            } else if (newitem.getStatus().equals(Item.Status.NEED_REDO_DELEGATED)) {
            }
        } else if (newitem.getType().contains("RECEIVED") == true || newitem.getType().contains("YOURSELF") == true) {
            if (newitem.getStatus().equals(Item.Status.UNCONFIRMED)) {
                if (("ACCEPT").equals(params.get("ACCEPT_STATE"))) {

                    Item item = itemDAO.findItemById(newitem.getId());
                    item.setStatus(Item.Status.ACCEPTED);
                    item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                    oResponse.setUpdatedItem(item);
                } else if (("REJECT").equals(params.get("ACCEPT_STATE"))) {

                    Item item = itemDAO.findItemById(newitem.getId());
                    item.setStatus(Item.Status.REJECTED);
                    item.setReasonForRejectionOfTask(newitem.getReasonForRejectionOfTask());
                    item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                    oResponse.setUpdatedItem(item);
                } else if (("NEGOTIATE").equals(params.get("ACCEPT_STATE"))) {

                    Item item = itemDAO.findItemById(newitem.getId());
                    item.setStatus(Item.Status.NEGOTIATED);
                    item.setReasonForNegotiatiationOfDeadLine(newitem.getReasonForNegotiatiationOfDeadLine());
                    item.setNegotiatedDeadLine(newitem.getNegotiatedDeadLine());
                    item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                    oResponse.setUpdatedItem(item);
                }
            } else if (newitem.getStatus().equals(Item.Status.NEGOTIATED)) {
            } else if (newitem.getStatus().equals(Item.Status.ACCEPTED)) {

                Item item = itemDAO.findItemById(newitem.getId());
                item.setStatus(Item.Status.DONE_UNCONFIRMED);
                item.setFeedback(newitem.getFeedback());
                item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                oResponse.setUpdatedItem(item);
            } else if (newitem.getStatus().equals(Item.Status.DELEGATED)) {

                Item item = itemDAO.findItemById(newitem.getId());
                item.setStatus(Item.Status.DONE_UNCONFIRMED);
                item.setFeedback(newitem.getFeedback());
                item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                oResponse.setUpdatedItem(item);
            } else if (newitem.getStatus().equals(Item.Status.REJECTED)) {
            } else if (newitem.getStatus().equals(Item.Status.DONE_UNCONFIRMED)) {
            } else if (newitem.getStatus().equals(Item.Status.DONE_CONFIRMED)) {
            } else if (newitem.getStatus().equals(Item.Status.NEED_REDO)) {

                Item item = itemDAO.findItemById(newitem.getId());
                item.setStatus(Item.Status.DONE_UNCONFIRMED);
                item.setFeedback(newitem.getFeedback());
                item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                oResponse.setUpdatedItem(item);
            } else if (newitem.getStatus().equals(Item.Status.NEED_REDO_DELEGATED)) {

                Item item = itemDAO.findItemById(newitem.getId());
                item.setStatus(Item.Status.DONE_UNCONFIRMED);
                item.setFeedback(newitem.getFeedback());
                item = itemDAO.updateFromFreshItem(item, oRequest.getSessionId());
                oResponse.setUpdatedItem(item);
            }
        }
        oResponse.setSessionId(oRequest.getSessionId());
        return oResponse;
    }

    /**
     * PUT method for updating or creating an instance of UpdateItemResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
