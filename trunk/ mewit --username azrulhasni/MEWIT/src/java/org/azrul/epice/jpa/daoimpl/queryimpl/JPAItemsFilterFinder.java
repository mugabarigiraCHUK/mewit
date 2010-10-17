/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.jpa.daoimpl.queryimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author azrulhasni
 */
public class JPAItemsFilterFinder {
    public static JPAItemsFilter get(String name, Map<String,Object> parameters){
        if ("ALL_ITEMS_IN_MONTH".equals(name)){
            return new JPAAllItemsInMonthFilter((Integer)parameters.get("MONTH"),(Integer)parameters.get("YEAR"));
        }else if ("ALL_RECEIVED_ITEMS".equals(name)){
            return new JPAAllReceivedItemsFilter();
        }else if ("ALL_SENT_ITEMS".equals(name)){
            return new JPAAllSentItemsFilter();
        }else if ("ALL_SUPERVISED_ITEMS".equals(name)){
            return new JPAAllSupervisedItemsFilter();
        }else if ("RECEIVED_ITEMS_DEADLINE_ON_DATE".equals(name)){
            return new JPAReceivedItemsDeadlineOnDateFilter((Date)parameters.get("ON_DATE"));
        }else if ("RECEIVED_ITEMS_NEAR_DEADLINE".equals(name)){
            return new JPAReceivedItemsNearDeadlineFilter();
        }else if ("RECEIVED_ITEMS_NEED_REDO".equals(name)){
            return new JPAReceivedItemsNeedRedoFilter();
        }else if ("RECEIVED_ITEMS_NEEDED_ATTENTION".equals(name)){
            return new JPAReceivedItemsNeededAttentionFilter();
        }else if ("RECEIVED_ITEMS_NOT_CONFIRMED".equals(name)){
            return new JPAReceivedItemsNotConfirmedFilter();
        }else if ("RECEIVED_ITEMS_NOT_DONE".equals(name)){
            return new JPAReceivedItemsNotDoneFilter();
        }else if ("RECEIVED_ITEMS_OVERDUE".equals(name)){
            return new JPAReceivedItemsOverdueFilter();
        }else if ("SENT_ITEMS_DONE_UNCONFIRMED".equals(name)){
            return new JPASentItemsDoneUnconfirmedFilter();
        }else if ("SENT_ITEMS_NEAR_DEADLINE".equals(name)){
            return new JPASentItemsNearDeadlineFilter();
        }else if ("SENT_ITEMS_NEEDED_ATTENTION".equals(name)){
            return new JPASentItemsNeededAttentionFilter();
        }else if ("SENT_ITEMS_NEGOTIATED".equals(name)){
            return new JPASentItemsNegotiatedFilter();
        }else if ("SENT_ITEMS_NOT_DONE".equals(name)){
            return new JPASentItemsNotDoneFilter();
        }else if ("SENT_ITEMS_REJECTED".equals(name)){
            return new JPASentItemsRejectedFilter();
        }else if ("SUPERVISED_ITEMS_NEAR_DEADLINE".equals(name)){
            return new JPASupervisedItemsNearDeadlineFilter();
        }else if ("ITEMS_NOT_ARCHIVED".equals(name)){
            return new JPAItemsNotArchivedFilter();
        }  else{
            return null;
        }
    }

    public static List<JPAItemsFilter> get(List<String> filterNames,Map<String,Object> parameters){
        List<JPAItemsFilter> filters = new ArrayList<JPAItemsFilter>();
        for (String name:filterNames){
            filters.add(get(name,parameters));
        }
        return filters;
    }
}
