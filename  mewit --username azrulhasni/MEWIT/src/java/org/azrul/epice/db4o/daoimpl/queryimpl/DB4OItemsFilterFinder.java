/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.db4o.daoimpl.queryimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azrulhasni
 */
public class DB4OItemsFilterFinder {
    public static DB4OItemsFilter get(String name, Map<String,Object> parameters){
        if ("ALL_ITEMS_IN_MONTH".equals(name)){
            return new DB4OAllItemsInMonthFilter((Integer)parameters.get("MONTH"),(Integer)parameters.get("YEAR"));
        }else if ("ALL_RECEIVED_ITEMS".equals(name)){
            return new DB4OAllReceivedItemsFilter();
        }else if ("ALL_SENT_ITEMS".equals(name)){
            return new DB4OAllSentItemsFilter();
        }else if ("ALL_SUPERVISED_ITEMS".equals(name)){
            return new DB4OAllSupervisedItemsFilter();
        }else if ("RECEIVED_ITEMS_DEADLINE_ON_DATE".equals(name)){
            return new DB4OReceivedItemsDeadlineOnDateFilter((Date)parameters.get("ON_DATE"));
        }else if ("RECEIVED_ITEMS_NEAR_DEADLINE".equals(name)){
            return new DB4OReceivedItemsNearDeadlineFilter();
        }else if ("RECEIVED_ITEMS_NEED_REDO".equals(name)){
            return new DB4OReceivedItemsNeedRedoFilter();
        }else if ("RECEIVED_ITEMS_NEEDED_ATTENTION".equals(name)){
            return new DB4OReceivedItemsNeededAttentionFilter();
        }else if ("RECEIVED_ITEMS_NOT_CONFIRMED".equals(name)){
            return new DB4OReceivedItemsNotConfirmedFilter();
        }else if ("RECEIVED_ITEMS_NOT_DONE".equals(name)){
            return new DB4OReceivedItemsNotDoneFilter();
        }else if ("RECEIVED_ITEMS_OVERDUE".equals(name)){
            return new DB4OReceivedItemsOverdueFilter();
        }else if ("SENT_ITEMS_DONE_UNCONFIRMED".equals(name)){
            return new DB4OSentItemsDoneUnconfirmedFilter();
        }else if ("SENT_ITEMS_NEAR_DEADLINE".equals(name)){
            return new DB4OSentItemsNearDeadlineFilter();
        }else if ("SENT_ITEMS_NEEDED_ATTENTION".equals(name)){
            return new DB4OSentItemsNeededAttentionFilter();
        }else if ("SENT_ITEMS_NEGOTIATED".equals(name)){
            return new DB4OSentItemsNegotiatedFilter();
        }else if ("SENT_ITEMS_NOT_DONE".equals(name)){
            return new DB4OSentItemsNotDoneFilter();
        }else if ("SENT_ITEMS_REJECTED".equals(name)){
            return new DB4OSentItemsRejectedFilter();
        }else if ("SUPERVISED_ITEMS_NEAR_DEADLINE".equals(name)){
            return new DB4OSupervisedItemsNearDeadlineFilter();
        }else{
            return null;
        }
    }

    public static List<DB4OItemsFilter> get(List<String> filterNames,Map<String,Object> parameters){
        List<DB4OItemsFilter> filters = new ArrayList<DB4OItemsFilter>();
        for (String name:filterNames){
            filters.add(get(name,parameters));
        }
        return filters;
    }
}
