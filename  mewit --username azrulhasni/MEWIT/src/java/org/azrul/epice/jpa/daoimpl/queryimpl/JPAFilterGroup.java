/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl.queryimpl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class JPAFilterGroup implements JPAItemsFilter {

    private List<JPAItemsFilter> filters = new ArrayList<JPAItemsFilter>();
    private boolean useOR = true;
    private String keywords;


     public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (JPAItemsFilter filter : filters) {
            predicates.add(filter.filter(user, cb, ritem));
        }

//        int state=0;
//
//        if (useOR==true&& keywords==null){
//            state=1;
//        }else if (useOR==true && keywords!=null){
//            state = 2;
//        }else if (useOR==false && keywords==null){
//            state = 3;
//        }else{
//            state = 4;
//        }
//        Predicate predicate = null;
//        if (state == 1){
//             predicate = cb.or(predicates.toArray(new Predicate[0]));
//        }else if (state == 2){
//             JPAFullTextSearchFilter fullTextSearchFilter = new JPAFullTextSearchFilter(keywords);
//             predicate = cb.and(cb.or(predicates.toArray(new Predicate[0])),fullTextSearchFilter.filter(user, cb, ritem));
//        }else if (state == 3){
//             predicate = cb.and(predicates.toArray(new Predicate[0]));
//        }else if (state == 4){
//            JPAFullTextSearchFilter fullTextSearchFilter = new JPAFullTextSearchFilter(keywords);
//            predicate = cb.and(cb.and(predicates.toArray(new Predicate[0])),fullTextSearchFilter.filter(user, cb, ritem));
//        }
//        return predicate;
        return   cb.or(predicates.toArray(new Predicate[0]));
    }

    public boolean filter(Item item, String currentUser) {
        boolean res = true;
        boolean firstFlag = true;
        for (JPAItemsFilter filter : filters) {
            if (firstFlag == true) {
                res = filter.filter(item, currentUser);
                firstFlag = false;
            } else {
                if (useOR) {
                    res = res || filter.filter(item, currentUser);
                } else {
                    res = res && filter.filter(item, currentUser);
                }
            }
        }
        return res;
    }

    public String getType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the filters
     */
    public List<JPAItemsFilter> getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(List<JPAItemsFilter> filters) {
        this.filters = filters;
    }

    /**
     * @return the useOR
     */
    public boolean getUseOR() {
        return useOR;
    }

    /**
     * @param useOR the useOR to set
     */
    public void setUseOR(boolean useOR) {
        this.useOR = useOR;
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
