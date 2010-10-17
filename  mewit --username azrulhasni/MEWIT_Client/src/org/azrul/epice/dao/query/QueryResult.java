/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.dao.query;

import java.util.List;
import org.azrul.epice.domain.Item;
import java.util.ArrayList;

/**
 *
 * @author azrulm
 */
public class QueryResult {

    private List<Item> items=new ArrayList<Item>();
    private int total;
    private int startRow;

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

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


	public List<SearchPage> getPages(){
		List<SearchPage> pages = new ArrayList<SearchPage>();
		if (items==null || items.isEmpty())
		{
			//do nothing
		}else{
			int maxPage = (int)Math.ceil(total/items.size());
			for (int i=1;i<=maxPage ;i++ )
			{
				SearchPage p = new SearchPage();
				p.setDisplay(i);
				p.setData(i);
				pages.add(p);
			}
		}
		return pages;
	}

	public void setPages(List<SearchPage> pages){

	}
}
