/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl.queryimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulhasni
 */
public class JPAFullTextSearchFilter /*implements JPAItemsFilter*/ {

    private String keywords;

    public JPAFullTextSearchFilter(String keywords) {
        this.keywords = keywords;
    }

     public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
       
            //return cb.like(ritem.get("subject").as(String.class), "%" + keywords + "%");
            StringTokenizer st = new StringTokenizer(keywords, " ");
            List<Predicate> preds = new ArrayList<Predicate>();
            while (st.hasMoreTokens()) {
                String keyword = st.nextToken();
                preds.add(cb.like(ritem.get("subject").as(String.class), "%" + keyword + "%"));
                preds.add(cb.like(ritem.get("task").as(String.class), "%" + keyword + "%"));
                preds.add(cb.like(ritem.get("recipient").as(String.class), "%" + keyword + "%"));
                preds.add(cb.like(ritem.get("sender").as(String.class), "%" + keyword + "%"));
            }
            return cb.or(preds.toArray(new Predicate[1]));
       
    }

    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
