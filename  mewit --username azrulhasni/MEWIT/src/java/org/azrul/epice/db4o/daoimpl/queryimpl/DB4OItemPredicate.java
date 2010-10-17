/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.db4o.daoimpl.queryimpl;

import com.db4o.query.Predicate;

/**
 *
 * @author azrulhasni
 */
public class DB4OItemPredicate {
    private Predicate predicate;
    
    public DB4OItemPredicate(){

    }

    public DB4OItemPredicate(Predicate predicate){
        this.predicate = predicate;
    }

    public Predicate get(){
        return predicate;
    }

    public void set(Predicate predicate){
        this.predicate = predicate;
    }
}
