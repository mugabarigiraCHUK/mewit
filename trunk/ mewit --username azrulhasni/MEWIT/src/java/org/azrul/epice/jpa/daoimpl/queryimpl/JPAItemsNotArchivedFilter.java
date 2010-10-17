/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.jpa.daoimpl.queryimpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulm
 */
public class JPAItemsNotArchivedFilter implements JPAItemsFilter{
    private String type = "ITEMS_NOT_ARCHIVED";
    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        return cb.or(
                    cb.and(
                        cb.equal(
                            ritem.get("archivedForSender").as(Boolean.class),false
                        ),
                        cb.equal(
                            ritem.get("sender").as(String.class),user
                        )
                    ),
                    cb.and(
                        cb.equal(
                            ritem.get("archivedForRecipient").as(Boolean.class),false
                        ),
                        cb.equal(
                            ritem.get("recipient").as(String.class),user
                        )
                    )

               );
    }

    public boolean filter(Item item, String user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getType() {
       return type;
    }

}
