/*
 *   Created on : 19.11.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.servlets;

import com.alexnerd.data.TerminalButton;
import com.alexnerd.data.ticket.Ticket;
import com.alexnerd.utils.db.EQueueDB;
import com.alexnerd.utils.db.TerminalButtonDB;
import com.alexnerd.utils.db.TicketDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TerminalServlet", urlPatterns = {"/terminal"})
public class TerminalServlet extends HttpServlet{
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
        String url = "/WEB-PAGES/TERMINAL/terminal.jsp";
        
        String buttonId = request.getParameter("buttonId");
        if(buttonId != null){
            
            TerminalButton terminalButton = EQueueDB.
                getTerminalButtonById(Long.valueOf(buttonId));
            Ticket ticket = TerminalButtonDB.getTicket(terminalButton);
            
            TerminalButtonDB.removeTicket(terminalButton);
            EQueueDB.addTicket(ticket);
            TicketDB.setPickDate(ticket, LocalDateTime.now());
                        
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<div>");
                out.println("<h3>" + EQueueDB.getCompanyName() + "</h3>");
                out.println("<hr>");
                out.println("<h5>Ваш номер:</h5>");
                out.println("<p>" + ticket.toString() + "</p>");
                out.println("<hr>");
                out.println("<p>Дата и время взятия талона: " + 
                       TicketDB.getPickDate(ticket).format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")) + "</p>");
                out.println("<p>Перед вами человек: " + (EQueueDB.getQueueLength() - 1));
                out.println("</div>");
                out.flush();
            }
        }
        if(!response.isCommitted())
            getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
