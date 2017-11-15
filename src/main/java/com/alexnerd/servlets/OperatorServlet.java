/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.Operator;
import com.alexnerd.data.User;
import com.alexnerd.ticket.Ticket;
import com.alexnerd.ticket.TicketStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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
@WebServlet(name = "OperatorServlet", urlPatterns = {"/operator"})
public class OperatorServlet extends HttpServlet {

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
            out.println("<title>Servlet OperatorServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OperatorServlet at " + request.getContextPath() + "</h1>");
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
        String url = "/WEB-PAGES/operator.jsp";
        
        String action = request.getParameter("operator_action");
        HttpSession session = request.getSession();
        Operator operator = (Operator) session.getAttribute("user");
        
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");        
        
        if(action != null){
            if(!operator.isAvailable()){
                if(action.equals("begin")){
                    operator.setAvailable(true);
                }
            } else {
                Ticket ticket = null;
                switch (action) {
                    case "next":
                        if(equeue.getQueueLength() > 0){
                            ticket = equeue.getFirstTicket();
                            ticket.setStatus(TicketStatus.INWORK);
                            ticket.setActionDate(LocalDateTime.now());                            
                        }
                        operator.setTicket(ticket);
                        break;
                    case "repeat":
                        break;
                    case "skip":
                        if(operator.getTicket() != null){
                            ticket = operator.getTicket();
                            ticket.setStatus(TicketStatus.MISSED);
                            equeue.addTicket(ticket);
                            operator.cancelTiket();
                        }                        
                        break;
                    case "cancel":
                        if(operator.getTicket() != null){
                            operator.cancelTiket();
                        }
                        break;
                    case "off":
                        if(operator.getTicket() != null){
                            operator.cancelTiket();
                        }
                        operator.setAvailable(false);
                        break;
                    default:
                        break;
                }
            }
        }                
    
        
        /*HttpSession session = request.getSession();
        EQueue equeue = (EQueue) session.getAttribute("queue");*/
        
        //EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        
        //equeue.getOperator(1).getNumber();
        
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
