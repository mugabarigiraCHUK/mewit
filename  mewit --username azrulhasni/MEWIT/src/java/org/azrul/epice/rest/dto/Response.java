/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.rest.dto;

import java.util.List;
import java.util.Map;
import org.azrul.epice.dao.query.SearchItemsQuery;

/**
 *
 * @author azrulhasni
 */
public class Response {
    private String sessionId;
    private List<String> errors;
    private SearchItemsQuery carriedQuery;
    private Map<String,Map<String,String>> nextApis;
        /**
     * @return the nextApis
     */
    public Map<String, Map<String,String>> getNextApis() {
        return nextApis;
    }

    /**
     * @param nextApis the nextApis to set
     */
    public void setNextApis(Map<String, Map<String,String>> nextApis) {
        this.nextApis = nextApis;
    }

    /**
     * @return the errors
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

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
