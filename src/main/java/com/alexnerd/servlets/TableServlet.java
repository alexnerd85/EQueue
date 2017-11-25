/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.users.EQueueUser;
import com.alexnerd.data.users.Operator;
import com.alexnerd.data.users.UserRole;
import com.alexnerd.ticket.Ticket;
import com.alexnerd.ticket.TicketStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *   @Created    : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

@WebServlet(name = "TableServlet", urlPatterns = {"/table"})
public class TableServlet extends HttpServlet {

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
            out.println("<title>Servlet TableServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TableServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        //HttpSession session = request.getSession();
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        String url = "/WEB-PAGES/table.jsp";
        
        String action = request.getParameter("action");
        if(action != null && action.equals("get-operators")){
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                List<EQueueUser> users = equeue.getUsers();
                System.out.println("1");
                for(EQueueUser user : users){
                    System.out.println("2");
                    if(user.getUserRole() == UserRole.OPERATOR){
                        Operator operator = (Operator) user;
                        Ticket ticket = operator.getTicket();
                        if(operator.isAvailable() && ticket != null)
                            out.print(operator.getNumWindow() +
                                "," + ticket + "," + ticket.getStatus() + ";");
                    }
                }                
                out.flush();
            }
        }
        if(action != null && action.equals("change-ticket-status")){
            int numWindow = Integer.valueOf(request.getParameter("window"));
            String ticket = request.getParameter("ticket");
            TicketStatus status = TicketStatus.valueOf(request.getParameter("status"));
            List<EQueueUser> users = equeue.getUsers();
            for(EQueueUser user : users){
                if(user.getUserRole() == UserRole.OPERATOR){
                    Operator operator = (Operator) user;
                    if(operator.getNumWindow() == numWindow){
                        Ticket t = operator.getTicket();
                        if(t.toString().equals(ticket))
                            t.setStatus(status);
                    }
                }
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
