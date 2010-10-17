/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.dao.query;

/**
 *
 * @author azrulhasni
 */
public class ItemPredicate<T> {
    private T predicate;
    
    public ItemPredicate(){

    }

    public ItemPredicate(T predicate){
        this.predicate = predicate;
    }

    public T get(){
        return predicate;
    }

    public void set(T predicate){
        this.predicate = predicate;
    }
}
