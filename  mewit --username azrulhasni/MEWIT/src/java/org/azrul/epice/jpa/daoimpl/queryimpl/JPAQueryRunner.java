/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl.queryimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.dao.ItemDAO;
import org.azrul.epice.dao.query.QueryResult;
import org.azrul.epice.dao.query.SearchItemsQuery;
import org.azrul.epice.domain.Item;
import org.azrul.epice.jpa.daoimpl.JPAItemDAO;
import org.azrul.epice.util.LogUtil;

/**
 *
 * @author azrulhasni
 */
public class JPAQueryRunner {

    public static String PUNAME = "MEWITPU";
    private ItemDAO itemDAO;

    public JPAQueryRunner() {
        itemDAO = new JPAItemDAO();
    }

    public QueryResult run(SearchItemsQuery query, String user, int startRow, int resultCount) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PUNAME);
        EntityManager em = factory.createEntityManager();
        QueryResult result = new QueryResult();
        try {

            List<String> itemIds = getItemIds(query, em);
            Long total = count(itemIds, query, user, em);
            List<Item> ritems = labelWithTypeAndFilterArchived(new ArrayList<Item>(run(itemIds, query, user, startRow, resultCount, em)), user);
            result.setItems(ritems);
            result.setTotal(total);
            result.setStartRow(startRow);
            return result;
        } catch (Exception ex) {
            LogUtil.getLogger().log(Level.SEVERE, Thread.currentThread().getStackTrace()[3].getMethodName(), ex);
            return result;
        } finally {
            em.close();
        }
    }

    private List<Item> labelWithTypeAndFilterArchived(List<Item> ritems, final String user) {
        //next, all refreshed items will be processed to determine its type.
        List<Item> items = new ArrayList<Item>();
        for (Item ritem : ritems) {
            if (ritem != null) {
//                if (getItemDAO().labelAndFilterForArchive(query.isArchiveIncluded(), query.isReferenceOnly(), user, ritem)) {
//                    continue;
//                }
                ((JPAItemDAO) getItemDAO()).labelForArchive(user, ritem);
                getItemDAO().labelWithType(ritem, user);

//                //label children if any
//                List<Item> children = new ArrayList<Item>();
//                children.addAll(ritem.getChildren());
//                labelWithTypeAndFilterArchived(query,children, user);
                items.add(ritem);
            }
        }
        return items;
    }

    private List<String> getItemIds(SearchItemsQuery query, EntityManager em) throws Exception {
        String sql = "SELECT it.id FROM item it " +
                "LEFT OUTER JOIN item_recipients itr ON (itr.item_id=it.id) "+
                "LEFT OUTER JOIN item_tags itt ON (it.id=itt.item_id) WHERE " +
                "to_tsVector(it.task) @@ plainto_tsquery('" + query.getSearchTerm() + "') or " +
                "to_tsVector(it.sender) @@ plainto_tsquery('" + query.getSearchTerm() + "') or " +
                "to_tsVector(it.recipient) @@ plainto_tsquery('" + query.getSearchTerm() + "') or " +
                "to_tsVector(it.subject) @@ plainto_tsquery('" + query.getSearchTerm() + "') or " +
                "to_tsVector(itr.recipients) @@ plainto_tsquery('" + query.getSearchTerm() + "') or " +
                "to_tsVector(itt.tags) @@ plainto_tsquery('" + query.getSearchTerm() + "')" +
                ";";
        System.out.println("sql="+sql);
        Query sqlquery = em.createNativeQuery(sql);
        return sqlquery.getResultList();
    }

    private Long count(List<String> itemIds, SearchItemsQuery query, final String user, EntityManager em) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PUNAME);
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Long> criCount = cb.createQuery(Long.class);
        Root<Item> ritem = criCount.from(Item.class);

        JPAFilterGroup filterGroup = new JPAFilterGroup();
        List<JPAItemsFilter> filters = JPAItemsFilterFinder.get(query.getFilters(), query.getParameters());
        filterGroup.setFilters(filters);
        filterGroup.setKeywords(query.getSearchTerm());

        //Query with JPA
        Long count = null;
        if (filterGroup.getKeywords() != null) {
            if (itemIds.isEmpty() == false) {
                Predicate existInSearch = ritem.get("id").in(itemIds);
                Predicate filtered = filterGroup.filter(user, cb, ritem);
                TypedQuery<Long> tquery = em.createQuery(criCount.select(cb.count(ritem)).where(cb.and(existInSearch, filtered)));
                count = tquery.getSingleResult();
            } else {
                count = new Long(0);
            }
        } else {
            Predicate filtered = filterGroup.filter(user, cb, ritem);
            TypedQuery<Long> tquery = em.createQuery(criCount.select(cb.count(ritem)).where(filtered));
            count = tquery.getSingleResult();
        }

        return count;
    }

    private List<Item> run(List<String> itemIds, SearchItemsQuery query, final String user, final int startRow, final int resultCount, EntityManager em) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PUNAME);
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Item> criItem = cb.createQuery(Item.class);
        Root<Item> ritem = criItem.from(Item.class);

        JPAFilterGroup filterGroup = new JPAFilterGroup();
        List<JPAItemsFilter> filters = JPAItemsFilterFinder.get(query.getFilters(), query.getParameters());
        filterGroup.setFilters(filters);
        filterGroup.setKeywords(query.getSearchTerm());

        //Query with JPA
        List<Item> ftsItems = null;
        if (filterGroup.getKeywords() != null) {
            if (itemIds.isEmpty() == false) {
                Predicate existInSearch = ritem.get("id").in(itemIds);
                Predicate filtered = null;
                if (query.isArchiveIncluded()) {
                    filtered = filterGroup.filter(user, cb, ritem);
                } else {
                    filtered = cb.and(filterGroup.filter(user, cb, ritem), (new JPAItemsNotArchivedFilter()).filter(user, cb, ritem));
                }
                Order orderByItemId = cb.asc(ritem.get("id"));

                TypedQuery<Item> tquery = em.createQuery(criItem.where(cb.and(existInSearch, filtered)).orderBy(orderByItemId));
                tquery.setFirstResult(startRow * resultCount);
                tquery.setMaxResults(resultCount);
                ftsItems = tquery.getResultList();
            } else {
                ftsItems = new ArrayList<Item>();
            }
        } else {
            Predicate filtered = filterGroup.filter(user, cb, ritem);
            TypedQuery<Item> tquery = em.createQuery(criItem.where(filtered));
            tquery.setFirstResult(startRow * resultCount);
            tquery.setMaxResults(resultCount);
            ftsItems = tquery.getResultList();
        }

        return ftsItems;
    }

    /**
     * @return the itemDAO
     */
    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    /**
     * @param itemDAO the itemDAO to set
     */
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
}
