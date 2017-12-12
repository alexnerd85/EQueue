/*
 *   Created on : 07.12.2017, 22:15:36
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.utils.db;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.data.ticket.Ticket;
import com.alexnerd.data.users.EQueueUser;
import javax.persistence.EntityManager;


public class EQueueService {
    protected EntityManager em;
    
    public EQueueService(EntityManager em){
        this.em = em;
    }
    
    public EQueue createEQueue(){
        EQueue equeue = em.find(EQueue.class, 1L);
        if(equeue == null){
            equeue = new EQueue();
            em.persist(equeue);
        }
        return equeue;
    }
    
    public EQueueUser getOperator(long id){
        return em.find(EQueueUser.class, id);
    }
    
    public TerminalButton getTerminalButton(long id){
        return em.find(TerminalButton.class, id);
    }
    
    public Ticket getTicket(long id){
        return em.find(Ticket.class, id);
    }
    
}
