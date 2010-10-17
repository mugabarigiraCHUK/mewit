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
public class SearchRequest extends Request{
    private SearchItemsQuery query;
    private int startRow;
    private int resultCount;

    

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
}
