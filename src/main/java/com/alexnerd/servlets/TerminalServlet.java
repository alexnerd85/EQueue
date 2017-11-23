/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.ticket.Ticket;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *   @Created on : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

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
        
        //processRequest(request, response);
        /*HttpSession session = request.getSession();
        EQueue equeue = (EQueue) session.getAttribute("equeue");*/
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        
        /*String button = request.getParameter("buttonId");
        System.out.println("**************************************************  " + button);
        
        if(button != null){
            byte[] ptext = button.getBytes(ISO_8859_1); 
            button = new String(ptext, UTF_8); 
        }
        System.out.println("**************************************************  " + button);
        List<TerminalButton> list = equeue.getTerminalButtons();
        
        
        for(TerminalButton terminalButton : list){
            if(terminalButton.getName().equals(button)){
                Ticket ticket = terminalButton.getTicket();
                ticket.setPickDate(LocalDateTime.now());
                equeue.addTicket(ticket);
                terminalButton.removeTicket();
            }
        }*/
        
        String buttonId = request.getParameter("buttonId");
        if(buttonId != null){
            TerminalButton terminalButton = equeue.
                getTerminalButtonById(Long.valueOf(buttonId));
            Ticket ticket = terminalButton.getTicket();
            ticket.setPickDate(LocalDateTime.now());
            equeue.addTicket(ticket);
            terminalButton.removeTicket();
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<div>");
                out.println("<h3>" + equeue.getCompanyName() + "</h3>");
                out.println("<hr>");
                out.println("<h5>Ваш номер:</h5>");
                out.println("<p>" + ticket.toString() + "</p>");
                out.println("<hr>");
                out.println("<p>Дата и время взятия талона: " + 
                        ticket.getPickDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")) + "</p>");
                out.println("<p>Перед вами человек: " + (equeue.getQueueLength() - 1));
                out.println("</div>");
                out.flush();
            }
        }            
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
