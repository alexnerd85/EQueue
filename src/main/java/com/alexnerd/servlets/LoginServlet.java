/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.Admin;
import com.alexnerd.data.EQueue;
import com.alexnerd.data.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aleksey
 */
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
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        
        String action = request.getParameter("action");
        if(action != null){
            switch(action){
                case "check-config":
                    /*if (equeue.getProperties().getProperty("dbAdress").isEmpty()) {
                    try (PrintWriter out = response.getWriter()) {
                        out.println("true");
                        out.flush();
                    }
                    }*/
                    System.out.println("*************************************** CHECK CONFIG NOT NULL");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("true");
                        out.flush();
                    }
                    break;
                    
                case "add-config":
                    System.out.println("********************************* ADD_CONFIG");
                    Properties props = equeue.getProperties();
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
                    equeue.setProperties(props);

                    equeue.addUser(new Admin(
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
                        User user = equeue.getUserByLogin(username);
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
