/*
 *   Created on : 10.12.2017, 17:58:16
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.utils.db;

import com.alexnerd.data.users.Operator;
import com.alexnerd.data.ticket.Ticket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Aleksey
 */
public class EQueueUserDB {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("com.alexnerd_EQueue_war_0.1PU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }

    public static void setAvailable(Operator o, boolean status) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            Operator operator = (Operator) equeueService.getOperator(o.getUserId());
            operator.setAvailable(status);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueUserDB setAvailable " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static boolean isAvailable(Operator o) {
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        Operator operator = (Operator) equeueService.getOperator(o.getUserId());
        boolean status = operator.isAvailable();
        System.out.println("************************** EQueueUserDB isAvailable " + status);
        em.close();
        return status;
    }
    
    public static void setTicket(Operator o, Ticket t){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            Operator operator = (Operator) equeueService.getOperator(o.getUserId());
            operator.setTicket(t);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueUserDB setTicket " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Ticket getTicket(Operator o) {
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        Operator operator = (Operator) equeueService.getOperator(o.getUserId());
        Ticket ticket = operator.getTicket();
        System.out.println("************************** EQueueUserDB getTicket " + ticket);
        em.close();
        return ticket;
    }
    
    public static void cancelTicket(Operator o){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            Operator operator = (Operator) equeueService.getOperator(o.getUserId());
            operator.cancelTiket();
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueUserDB cancelTicket " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static int getNumWindow(Operator o){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        Operator operator = (Operator) equeueService.getOperator(o.getUserId());
        int numWindow = operator.getNumWindow();
        System.out.println("************************** EQueueUserDB getNumWindow " + numWindow);
        em.close();
        return numWindow;
    }

}
