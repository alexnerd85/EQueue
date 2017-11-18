/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.Admin;
import com.alexnerd.data.EQueue;
import com.alexnerd.listeners.InitEQueueListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Popov Aleksey 2017
 */
@WebServlet(name = "EQueueServlet", urlPatterns = {"/equeuemain"})
public class EQueueServlet extends HttpServlet {  

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EQueueServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EQueueServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response )       
        String url = "/WEB-PAGES/equeuemain.jsp";
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        HttpSession session = request.getSession();
        session.setAttribute("user", equeue.getUser(0));
        
        String action = request.getParameter("main_action");
                    
        /*if(equeue == null){
            equeue = new EQueue();
        }
        Configuration config = new Configuration();
        HttpSession session = request.getSession();
        session.setAttribute("equeue", equeue);*/
        
        
        
        if(action != null){
            switch (action) {
                case "administration":
                    url = "/admin";
                    break;
                case "terminal":
                    url = "/terminal";
                    break;
                case "table":
                    url = "/WEB-PAGES/TABLE/table.jsp";
                    break;
                case "operator":
                    url = "/operator";                    
                    break;
                case "window":
                    url = "/WEB-PAGES/WINDOW/window.jsp";
                    break;
                case "checkConfig":                    
                    /*if (equeue.getProperties().getProperty("dbAdress").isEmpty()) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("true");
                            out.flush();
                        }
                    }*/                    
                    
                    try (PrintWriter out = response.getWriter()) {
                            out.println("true");
                            out.flush();
                    }
                    
                    break;
                case "add-config":
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
                default:
                    throw new ServletException("Unknown request");
            }
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
