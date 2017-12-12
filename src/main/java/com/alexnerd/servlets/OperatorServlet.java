/*
 *   Created on : 19.11.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.servlets;

import com.alexnerd.data.users.Operator;
import com.alexnerd.data.ticket.Ticket;
import com.alexnerd.data.ticket.TicketStatus;
import com.alexnerd.utils.db.EQueueDB;
import com.alexnerd.utils.db.EQueueUserDB;
import com.alexnerd.utils.db.TicketDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
        
        Operator operator = (Operator) session.getAttribute("hiddenUser");
        
        if( operator == null){
            operator = (Operator) session.getAttribute("user");
        } 
        
        //EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");        
        
        if(action != null){
            if(!EQueueUserDB.isAvailable(operator)){
                if(action.equals("begin")){
                    //operator.setAvailable(true);
                    EQueueUserDB.setAvailable(operator, true);
                }
            } else {
                Ticket ticket = null;
                switch (action) {
                    case "next":
                        if(EQueueDB.getQueueLength() > 0){
                            ticket = EQueueDB.getFirstTicket();
                            //ticket.setStatus(TicketStatus.INWORK);
                            TicketDB.setStatus(ticket, TicketStatus.INWORK);
                            //ticket.setActionDate(LocalDateTime.now());  
                            TicketDB.setActionDate(ticket, LocalDateTime.now());
                        }
                        //operator.setTicket(ticket);
                        EQueueUserDB.setTicket(operator, ticket);
                        break;
                    case "repeat":
                        if(EQueueUserDB.getTicket(operator) != null){
                            ticket = EQueueUserDB.getTicket(operator);
                            //ticket.setStatus(TicketStatus.REPEAT);
                            TicketDB.setStatus(ticket, TicketStatus.REPEAT);
                        }
                        break;
                    case "skip":
                        if(EQueueUserDB.getTicket(operator) != null){
                            ticket = EQueueUserDB.getTicket(operator);
                            //ticket.setStatus(TicketStatus.MISSED);
                            TicketDB.setStatus(ticket, TicketStatus.MISSED);
                            EQueueDB.addTicket(ticket);
                            //operator.cancelTiket();
                            EQueueUserDB.cancelTicket(operator);
                        }                        
                        break;
                    case "cancel":
                        if(EQueueUserDB.getTicket(operator) != null){
                            //operator.cancelTiket();
                            EQueueUserDB.cancelTicket(operator);
                        }
                        break;
                    case "off":
                        if(EQueueUserDB.getTicket(operator) != null){
                            //operator.cancelTiket();
                            EQueueUserDB.cancelTicket(operator);
                        }
                        EQueueUserDB.setAvailable(operator, false);
                        //operator.setAvailable(false);
                        break;
                    default:
                        throw new ServletException("Unknown request");
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
