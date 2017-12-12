/*
 *   Created on : 10.12.2017, 18:40:05
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.utils.db;

import com.alexnerd.data.TerminalButton;
import com.alexnerd.data.ticket.Ticket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Aleksey
 */
public class TerminalButtonDB {
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("com.alexnerd_EQueue_war_0.1PU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
    
    public static void removeTicket(TerminalButton tb) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            TerminalButton terminalButton = 
                    (TerminalButton) equeueService.getTerminalButton(tb.getButtonId());
            terminalButton.removeTicket();
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION TerminalButtonDB removeTicket " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Ticket getTicket(TerminalButton tb) {
        Ticket ticket = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            TerminalButton terminalButton = 
                    (TerminalButton) equeueService.getTerminalButton(tb.getButtonId());
            ticket = terminalButton.getTicket();
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION TerminalButtonDB getTicket " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        return ticket;
    }
    
}
