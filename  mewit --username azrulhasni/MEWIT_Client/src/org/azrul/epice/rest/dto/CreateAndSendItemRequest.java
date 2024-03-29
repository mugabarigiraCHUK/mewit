/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.rest.dto;

import java.util.Date;
import java.util.List;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.FileRepository;
import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulhasni
 */
public class CreateAndSendItemRequest extends Request{

    private List<String> recipients;
    private List<String> supervisors;
    private List<String> tags;
    private List<String> links;
    private String subject;
    private String task;
    private Date startDate;
    private Date deadline;
    private List<String> files;
    private Item.Priority priority;
    private SearchItemsQuery query;

    /**
     * @return the recipients
     */
    public List<String> getRecipients() {
        return recipients;
    }

    /**
     * @param recipients the recipients to set
     */
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }



    /**
     * @return the supervisors
     */
    public List<String> getSupervisors() {
        return supervisors;
    }

    /**
     * @param supervisors the supervisors to set
     */
    public void setSupervisors(List<String> supervisors) {
        this.supervisors = supervisors;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the links
     */
    public List<String> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<String> links) {
        this.links = links;
    }


   

  

    /**
     * @return the priority
     */
    public Item.Priority getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Item.Priority priority) {
        this.priority = priority;
    }

    /**
     * @return the query
     */
    public SearchItemsQuery getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(SearchItemsQuery query) {
        this.query = query;
    }

    /**
     * @return the files
     */
    public List<String> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<String> files) {
        this.files = files;
    }

   


  
}
