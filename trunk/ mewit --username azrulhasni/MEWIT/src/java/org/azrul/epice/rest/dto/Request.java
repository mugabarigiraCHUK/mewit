/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import org.azrul.epice.dao.query.SearchItemsQuery;

/**
 *
 * @author azrulhasni
 */
public class Request {
    private String sessionId;
    private SearchItemsQuery carriedQuery;

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the carriedQuery
     */
    public SearchItemsQuery getCarriedQuery() {
        return carriedQuery;
    }

    /**
     * @param carriedQuery the carriedQuery to set
     */
    public void setCarriedQuery(SearchItemsQuery carriedQuery) {
        this.carriedQuery = carriedQuery;
    }
}
