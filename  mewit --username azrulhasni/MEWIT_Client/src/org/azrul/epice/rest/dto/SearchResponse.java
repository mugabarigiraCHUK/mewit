/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.rest.dto;

import java.util.List;
import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulhasni
 */
public class SearchResponse extends Response {

    private List<Item> items;
    private SearchRequest lastRequest;
    private int startRow;
    private int resultCount;
    private int totalResultCount;

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @return the lastRequest
     */
    public SearchRequest getLastRequest() {
        return lastRequest;
    }

    /**
     * @param lastRequest the lastRequest to set
     */
    public void setLastRequest(SearchRequest lastRequest) {
        this.lastRequest = lastRequest;
    }

    /**
     * @return the startRow
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * @param startRow the startRow to set
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * @return the resultCount
     */
    public int getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount the resultCount to set
     */
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * @return the totalResultCount
     */
    public int getTotalResultCount() {
        return totalResultCount;
    }

    /**
     * @param totalResultCount the totalResultCount to set
     */
    public void setTotalResultCount(int totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

 
}
