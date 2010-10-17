/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.jpa.daoimpl.queryimpl;

import org.azrul.epice.db4o.daoimpl.queryimpl.*;
import com.db4o.query.Predicate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author azrulhasni
 */
public class JPACriteria {
    private List<Predicate> predicates = new ArrayList<Predicate>();;
    
    public JPACriteria(){

    }

    public JPACriteria(List<Predicate> predicates){
        if (predicates==null){
            predicates = new ArrayList<Predicate>();
        }
        this.predicates.addAll(predicates);
    }

    /**
     * @return the predicates
     */
    public List<Predicate> getPredicates() {
        return predicates;
    }

    /**
     * @param predicates the predicates to set
     */
    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

   
}
