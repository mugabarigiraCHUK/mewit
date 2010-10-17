/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl.queryimpl;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class JPASentItemsNeededAttentionFilter implements JPAItemsFilter {

    public boolean filter(Item item, String user) {
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

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
       
        return   cb.and(
                    cb.equal(ritem.get("sender").as(String.class),user),
                    cb.and(
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.UNCONFIRMED),
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.ACCEPTED),
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.DELEGATED),
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.REJECTED),
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.NEED_REDO),
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.DONE_CONFIRMED),
                        cb.notEqual(ritem.get("status").as(Integer.class),Item.Status.NEED_REDO_DELEGATED)
                    )
                );
    }
}
