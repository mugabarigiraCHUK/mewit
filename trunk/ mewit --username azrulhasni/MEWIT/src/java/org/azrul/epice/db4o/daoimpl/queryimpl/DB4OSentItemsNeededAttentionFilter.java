/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.db4o.daoimpl.queryimpl;

import com.db4o.query.Predicate;

import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class DB4OSentItemsNeededAttentionFilter implements DB4OItemsFilter {

      public DB4OItemPredicate filter(final Item item, final String user){
        Predicate predicate = new Predicate() {

            
            public boolean match(Object et) {
                return booleanFilter(item,user);
            }
        };
        DB4OItemPredicate itemPredicate = new DB4OItemPredicate();
        itemPredicate.set(predicate);
        return itemPredicate;
    }

     public boolean booleanFilter(Item item, String user) {
        if (item.getSender().equals(user)) {
            if ((Item.Status.UNCONFIRMED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.NEGOTIATED).equals(item.getStatus())) {
                return true;
            } else if ((Item.Status.ACCEPTED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.DELEGATED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.REJECTED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.DONE_UNCONFIRMED).equals(item.getStatus())) {
                return true;
            } else if ((Item.Status.DONE_CONFIRMED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.NEED_REDO).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.NEED_REDO_DELEGATED).equals(item.getStatus())) {
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String getType() {
        return "SENT_ITEMS_NEEDED_ATTENTION";
    }
}
