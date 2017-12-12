/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.listeners;

import com.alexnerd.data.users.Admin;
import com.alexnerd.data.users.Operator;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.utils.db.EQueueDB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class InitEQueueListener implements ServletContextListener {
   
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EQueueDB.initEQueue();
        EQueueDB.setCompanyName("Камчатский краевой кардиологический диспансер");
        EQueueDB.setRunningString("Бегущая строка");
        
        EQueueDB.addTerminalButton(new TerminalButton("Терапевт","Т", 12, true));
        EQueueDB.addUser(new Admin("admin","123","Иванов", "Иван", "Иванович"));
        EQueueDB.addUser(new Operator("pupkin","123", "Пупкин", "Василий", "Васильевич", false));
        
        
                
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
