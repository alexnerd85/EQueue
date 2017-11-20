/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.listeners;

import com.alexnerd.data.Admin;
import com.alexnerd.data.EQueue;
import com.alexnerd.data.Operator;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.ticket.Ticket;
import com.alexnerd.ticket.TicketPriority;
import com.alexnerd.ticket.TicketStatus;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Popov Aleksey 2017
 */

@WebListener
public class InitEQueueListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ServletContext servletContext = sce.getServletContext();
        EQueue equeue = new EQueue();
        equeue.addUser(new Operator("pupkin","123", "Пупкин", "Василий", "Васильевич", false));
        equeue.addUser(new Operator("kondr","123", "Кондратенко", "Андрей", "Аркадьевич", false));
        equeue.addUser(new Operator("mefodiy","123", "Мефодий", "Кирилл", "Афанасьевич", false));
        equeue.addUser(new Admin("admin","123","Иванов", "Иван", "Иванович"));
        /*equeue.getUser(0).setUserId(1);
        equeue.getUser(1).setUserId(2);
        equeue.getUser(2).setUserId(3);*/
        equeue.addTicket(new Ticket(1,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));
        equeue.addTicket(new Ticket(2,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));
        equeue.addTicket(new Ticket(3,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));
        equeue.addTicket(new Ticket(4,"Т",TicketPriority.NORMAL, TicketStatus.INQUEUE));        
                
        equeue.addTerminalButton(new TerminalButton("Терапевт","Т", 3, true));
        equeue.addTerminalButton(new TerminalButton("Невролог","Н", 4, true));
        equeue.addTerminalButton(new TerminalButton("Хирург","Х", 2, true));
        equeue.addTerminalButton(new TerminalButton("Стоматолог","С", 0, false));
        
        equeue.setCompanyName("Камчатский краевой кардиологический диспансер");
        equeue.setRunningString("Бегущая строка");
        servletContext.setAttribute("equeue", equeue); 
                
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
