/*
 *   Created on : 02.12.2017, 14:47:21
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.utils.db;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.RequestLog;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.data.users.EQueueUser;
import com.alexnerd.data.users.UserRole;
import com.alexnerd.data.ticket.Ticket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author alexnerd.com
 */
public class EQueueDB {
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("com.alexnerd_EQueue_war_0.1PU");
    
    public static EntityManagerFactory getEmFactory(){
        return emf;
    }
    
    
    public static void initEQueue(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        equeueService.createEQueue();
        em.close();
    }
        
    public static void setCompanyName(String companyName){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try{
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.setCompanyName(companyName);
            trans.commit();
        } catch(Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB setCompanyName " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static String getCompanyName(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        String companyName = equeue.getCompanyName();
        System.out.println("************************** EQueueDB getCompanyName " + companyName);
        em.close();
        return companyName;
    }
    
    public static void setRunningString(String runningString){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try{
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.setRunningString(runningString);
            trans.commit();
        } catch(Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB setRunningString " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static String getRunningString(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        String runningString = equeue.getRunningString();
        System.out.println("************************** EQueueDB getRunningString " + runningString);
        em.close();
        return runningString;
    }
    
    public static void setProperties(Properties props){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try{
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.setProperties(props);
            trans.commit();
            System.out.println("************************** setProperties " + props);
        } catch(Exception ex) {
            System.out.println("************************** EXCEPTION setProperties " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Properties getProperties(){
        //Properties props = null;
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        Properties props = equeue.getProperties();
        em.close();
        /*EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try{
            trans.begin();
            EQueue equeue = equeueService.createEQueue(1);
            props = equeue.getProperties();
            trans.commit();
            System.out.println("************************** getProperties " + props);
        } catch(Exception ex) {
            System.out.println("************************** EXCEPTION getProperties " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }*/
        
        return props;
    }
    
    
    public static void addUser(EQueueUser user){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.getUsers().add(user);
            trans.commit();
        } catch(Exception ex){
            System.out.println("************************** EXCEPTION EQueueDB ADD USER " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static List<EQueueUser> getUsers(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        List<EQueueUser> users = equeue.getUsers();
        System.out.println("************************** EQueueDB getUserByIdAndRole " + users);
        em.close();
        return users;
        /*
        EntityManager em = emf.createEntityManager();
        List<EQueueUser> users = em.createQuery("FROM EQueueUser users", EQueueUser.class).getResultList();
        em.close();
        */
    }
    /*
    public static List getUsers(){
        List<EQueueUser> users = null;
        EntityManager em = emf.createEntityManager();
        //users = em.createQuery("SELECT a FROM ADMINAPP a ORDER BY a.login", EQueueUser.class).getResultList();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try{
            trans.begin();
            //EQueue equeue = equeueService.createEQueue(1);
            trans.commit();
        } catch(Exception ex){
            System.out.println("************************** EXCEPTION EQueueDB getUsers() " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        return users;
    }
    */
    
    public static void deleteUser(long id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.deleteUser(id);
            trans.commit();
        } catch (Exception ex){
            System.out.println("************************** EXCEPTION EQueueDB DELETE USER " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void deleteUser(EQueueUser user){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.deleteUser(user);
            trans.commit();
        } catch (Exception ex){
            System.out.println("************************** EXCEPTION EQueueDB DELETE USER " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static EQueueUser getUserByLogin(String login){
        EQueueUser equeueUser = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeueUser = equeue.getUserByLogin(login);
            System.out.println("************************** EQueueDB getUserByLogin " + equeueUser.getLogin());
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB getUserByLogin " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        return equeueUser;
    }
    
    public static EQueueUser getUserByIdAndRole(long userId, UserRole userRole){
        EQueueUser equeueUser = null;
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        equeueUser = equeue.getUserByIdAndRole(userId, userRole);
        System.out.println("************************** EQueueDB getUserByIdAndRole " + equeueUser.getLogin());
        em.close();
        return equeueUser;
    }
    
    public static UserRole[] getUserRoles(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        UserRole[] userRoles = equeue.getUserRoles();
        System.out.println("************************** EQueueDB getUserRoles " + userRoles);
        em.close();
        return userRoles;
    }
    
    public static boolean isUniqueLogin(String login){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        boolean unique = equeue.isUniqueLogin(login);
        System.out.println("************************** EQueueDB isUniqueLogin " + unique);
        em.close();
        return unique;
    }
    
    /*
    public static List<Ticket> initEQueue(EQueue equeue){
        List<Ticket> res = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            res = em.createQuery("from TICKET", Ticket.class).getResultList();
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB initEQueue " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        return res;
    }
*/
    public static void addTerminalButton(TerminalButton terminalButton){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.addTerminalButton(terminalButton);
            System.out.println("************************** EQueueDB addTerminalButton " + terminalButton);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB addTerminalButton " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void deleteTerminalButton(long id){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.deleteTerminalButton(id);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB deleteTerminalButton " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static TerminalButton getTerminalButtonById(long id){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        TerminalButton terminalButton = equeue.getTerminalButtonById(id);
        System.out.println("************************** EQueueDB getTerminalButtonById " + terminalButton);
        em.close();
        return terminalButton;
    }
    
    public static Ticket getAndRemoveTicketFromTerminalButton(long id){
        Ticket ticket = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            TerminalButton terminalButton = equeue.getTerminalButtonById(id);
            ticket = terminalButton.getTicket();
            ticket.setPickDate(LocalDateTime.now());
            terminalButton.removeTicket();
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB getAndRemoveTicketFromTerminalButton " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        return ticket;
    }
    
    public static List<TerminalButton> getTerminalButtons(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        List<TerminalButton> terminalButtons = equeue.getTerminalButtons();
        System.out.println("************************** EQueueDB getTerminalButtons " + terminalButtons);
        em.close();
        return terminalButtons;
    }
    
    public static void addTicket(Ticket ticket){
        //boolean check = false;
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.addTicket(ticket);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB addTicket " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        //return check;
    }
    
     public static boolean isUniqueTerminalButtonName(String name){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        boolean unique = equeue.isUniqueTerminalButtonName(name);
        System.out.println("************************** EQueueDB isUniqueTerminalButtonName " + unique);
        em.close();
        return unique;
    }
    
    public static Ticket getFirstTicket(){
        Ticket ticket = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            ticket = equeue.getFirstTicket();
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB getFirstTicket " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
        return ticket;
    }
    
    public static int getQueueLength(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        int length = equeue.getQueueLength();
        System.out.println("************************** EQueueDB getQueueLength " + length);
        em.close();
        return length;
    }
    
    public static String[] getQueueTickets(){
        EntityManager em = emf.createEntityManager();
        EQueueService equeueService = new EQueueService(em);
        EQueue equeue = equeueService.createEQueue();
        String[] arr = equeue.getQueueTickets();
        System.out.println("************************** EQueueDB getQueueTickets " + arr);
        em.close();
        return arr;
    }
    
    public static void addLogEntry(RequestLog log){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.addLogEntry(log);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB addLogEntry " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void removeLogEntry(RequestLog log){
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        EQueueService equeueService = new EQueueService(em);
        try {
            trans.begin();
            EQueue equeue = equeueService.createEQueue();
            equeue.removeLogEntry(log);
            trans.commit();
        } catch (Exception ex) {
            System.out.println("************************** EXCEPTION EQueueDB removeLogEntry " + ex.getMessage());
            trans.rollback();
        } finally {
            em.close();
        }
    }

}
