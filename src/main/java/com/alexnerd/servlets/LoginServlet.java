/*
 *   Created on : 19.11.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.servlets;

import com.alexnerd.data.users.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alexnerd.data.users.EQueueUser;
import com.alexnerd.utils.db.EQueueDB;


public class LoginServlet extends HttpServlet {
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
                
        HttpSession session = request.getSession();
        //EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        
        String action = request.getParameter("action");
        if(action != null){
            switch(action){
                case "check-config":
                    System.out.println("*************************************** CHECK CONFIG NOT NULL"); 
                    System.out.println("*************************************** Properties " + EQueueDB.getProperties());
                    //if (equeue.getProperties().getProperty("dbAdress").isEmpty()) {
                    //if (EQueueDB.getProperties().getProperty("dbAdress").isEmpty()) {
                    if (EQueueDB.getProperties() == null) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("true");
                            out.flush();
                        }
                    } else {
                        System.out.println("*************************************** dbAdress " + EQueueDB.getProperties().getProperty("dbAdress"));                
                        System.out.println("*************************************** dbName " + EQueueDB.getProperties().getProperty("dbName"));                
                        System.out.println("*************************************** dbLogin " + EQueueDB.getProperties().getProperty("dbLogin"));                
                        System.out.println("*************************************** dbPassword " + EQueueDB.getProperties().getProperty("dbPassword"));                
                        System.out.println("*************************************** appLanguage " + EQueueDB.getProperties().getProperty("appLanguage"));                
                        
                    }
                    
                    /*
                    System.out.println("*************************************** CHECK CONFIG NOT NULL");                    
                    try (PrintWriter out = response.getWriter()) {
                        out.println("true");
                        out.flush();
                    }
                    */
                    break;
                    
                case "add-config":
                    System.out.println("********************************* ADD_CONFIG");
                    //Properties props = equeue.getProperties();
                    Properties props = new Properties();
                    props.setProperty(
                            "dbAdress", request.getParameter("dbAdress"));
                    props.setProperty(
                            "dbName", request.getParameter("dbName"));
                    props.setProperty(
                            "dbLogin", request.getParameter("dbLogin"));
                    props.setProperty(
                            "dbPassword", request.getParameter("dbPassword"));
                    props.setProperty(
                            "appLanguage", request.getParameter("appLanguage"));
                    //equeue.setProperties(props);
                    EQueueDB.setProperties(props);
                    System.out.println("************************** add-config getProperties " + EQueueDB.getProperties());
                    /*
                    equeue.addUser(new Admin(
                            request.getParameter("adminLogin"),
                            request.getParameter("adminPassword"),
                            "",
                            "",
                            ""
                    ));
                    */
                    
                    EQueueDB.addUser(new Admin(
                            request.getParameter("adminLogin"),
                            request.getParameter("adminPassword"),
                            "",
                            "",
                            ""
                    ));
                    
                    break;
                    
                case "login":
                    String username = request.getParameter("user_login");
                    String password = request.getParameter("user_password");
                    if (username.isEmpty() || password.isEmpty()) {
                        //redirect to error page
                        throw new ServletException("Login and Password fields must not be empty!");
                    } else {
                        //EQueueUser user = equeue.getUserByLogin(username);
                        EQueueUser user = EQueueDB.getUserByLogin(username);
                        if (user != null && user.getPassword().equals(password)) {
                            session.setAttribute("user", user);
                            url = "/EQueue/equeuemain";
                        } else {
                            //redirect to error page
                            throw new ServletException("Wrong user login or password!");
                        }
                    }
                    break;
                    
                default:
                    throw new ServletException("Unknown request");
            }
        }
        if(!response.isCommitted())
            response.sendRedirect(url);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login servlet";
    }// </editor-fold>

}
