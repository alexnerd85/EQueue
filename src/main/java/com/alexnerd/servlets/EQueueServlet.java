/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.User;
import com.alexnerd.data.UserRole;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        String url = "/WEB-PAGES/equeuemain.jsp";
        
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        
        HttpSession session = request.getSession();    
        
        String action = request.getParameter("main_action");
                
        if(action != null){
            User user = (User) session.getAttribute("user");;
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
                    if(user.getUserRole() == UserRole.OPERATOR){
                        url = "/operator";
                    } else if(user.getUserRole() == UserRole.ADMIN){
                        url="/WEB-PAGES/select-operator.jsp";
                    }
                    break;
                case "window":
                    if(user.getUserRole() == UserRole.OPERATOR){
                        url = "/WEB-PAGES/WINDOW/window.jsp";
                    } else if(user.getUserRole() == UserRole.ADMIN){
                        url="/WEB-PAGES/select-window.jsp";
                    }
                    break;
                case "exit":
                    url = "/login";
                    session.setAttribute("user", null);
                    break;
                default:
                    throw new ServletException("Unknown request");
            }
        }
        String selectOperator = request.getParameter("select_operator");
        if(selectOperator != null){
            session.setAttribute("user", equeue.getUserByIdAndRole(
                    Long.valueOf(selectOperator), UserRole.OPERATOR));
            url="/operator";
        }
        
        String selectWindow = request.getParameter("select_window");
        if(selectWindow != null){
            session.setAttribute("user", equeue.getUserByIdAndRole(
                    Long.valueOf(selectWindow), UserRole.OPERATOR));
            url="/WEB-PAGES/WINDOW/window.jsp";
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
