/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.db4o.daoimpl.queryimpl;

import org.azrul.epice.domain.Item;

/**
 *
 * @author azrulhasni
 */
public interface DB4OItemsFilter  {
    boolean booleanFilter(Item item, String user);
    String getType();
    DB4OItemPredicate filter(Item item, String user);
}
