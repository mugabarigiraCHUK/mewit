/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.azrul.epice.jpa.daoimpl.queryimpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author azrulhasni
 */
public class JPAItemsQueryUtil{
    public static String PUNAME = "MEWITPU";
    public static CriteriaBuilder getCriteriaBuilder(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PUNAME);
        return emf.getCriteriaBuilder();
    }
}
