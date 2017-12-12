/*
 *   Created on : 10.12.2017, 18:21:44
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.utils.db;

import com.alexnerd.data.ticket.Ticket;
import com.alexnerd.data.ticket.TicketStatus;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Aleksey
 */
public class TicketDB {
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("com.alexnerd_EQueue_war_0.1PU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
    
    public static void setStatus(Ticket t, TicketStatus status) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            Ticket ticket = (Ticket) equeueService.getTicket(t.getTicketId());
            ticket.setStatus(status);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION TicketDB setStatus " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void setActionDate(Ticket t, LocalDateTime actionTime) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            Ticket ticket = (Ticket) equeueService.getTicket(t.getTicketId());
            ticket.setActionDate(actionTime);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION TicketDB setActionDate " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void setPickDate(Ticket t, LocalDateTime pickTime) {
        //System.out.println("************************** TicketDB pick date " + pickTime);
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            Ticket ticket = (Ticket) equeueService.getTicket(t.getTicketId());
            //System.out.println("************************** TicketDB2 pick date " + pickTime);
            ticket.setPickDate(LocalDateTime.now());
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION TicketDB setPickDate " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static LocalDateTime getPickDate(Ticket t){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        Ticket ticket = (Ticket) equeueService.getTicket(t.getTicketId());
        LocalDateTime pickDate = ticket.getPickDate();
        System.out.println("************************** TicketDB getPickDate " + pickDate);
        em.close();
        return pickDate;
    }
}
