/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.servlets;

import com.alexnerd.data.EQueue;
import com.alexnerd.data.Operator;
import com.alexnerd.data.TerminalButton;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        //EQueue equeue = (EQueue) request.getAttribute("equeue");
        EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
        String url = "/WEB-PAGES/admin.jsp";
        
        String action = request.getParameter("action");       
        if(action != null){
            if(action.equals("add-user")){
                String login = request.getParameter("userLogin");
                UserRole userRole = UserRole.valueOf(request.getParameter("userRole"));
                String name = request.getParameter("userName");
                String sirname = request.getParameter("userSirname");
                String middlename = request.getParameter("userMiddlename");
                String password = request.getParameter("userPassword");       
                switch(userRole){
                    case ADMIN:                    
                        break;
                    case USER:
                        break;
                    case OPERATOR:
                        equeue.addUser(new Operator(login, password, sirname, name, middlename, false));
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестная роль");                
                }               
            }  
            if(action.equals("delete-user")){
                equeue.deleteUser(Long.parseLong(request.getParameter("userId")));
            }  
            if(action.equals("save-user")){
                System.out.println("***********************************  1  ***************************************");
                System.out.println("****************************************  " + request.getParameter("userId"));
                System.out.println("****************************************  " + request.getParameter("userRole"));
                User user = equeue.getUserByIdAndRole(
                        Long.parseLong(request.getParameter("userId")),
                        UserRole.valueOf(request.getParameter("userRole")));
                System.out.println("***********************************  2  ***************************************");
                // Должна быть проверка на null!!! Иначе сервер возвращает 500
                //return str == null ? true : false;
                user.setName(equeue.checkNullRequest(request.getParameter("userName")));
                System.out.println("***********************************  3  ***************************************");
                System.out.println("************************************  " + equeue.checkNullRequest(request.getParameter("userName")));
                user.setSirname(equeue.checkNullRequest(request.getParameter("userSirname")));
                user.setMiddlename(equeue.checkNullRequest(request.getParameter("userMiddlename")));
                user.setPassword(request.getParameter("userPassword"));
                if(UserRole.OPERATOR == user.getUserRole()){
                    ((Operator) user).setNumWindow(Integer.valueOf(request.getParameter("userNumWindow")));
                }                
            }
            if(action.equals("add-button")){
                equeue.addTerminalButton(new TerminalButton(
                                    request.getParameter("buttonName"),
                                    request.getParameter("buttonPrefix"),
                                    Integer.valueOf(request.getParameter("numTickets")),
                                    Boolean.valueOf(request.getParameter("status"))
                                ));
            }
            if(action.equals("save-button")){
                TerminalButton button = equeue.getTerminalButtonById(Long.parseLong(request.getParameter("buttonId")));
                button.setName(request.getParameter("name"));
                button.setPrefix(request.getParameter("prefix"));
                button.setNumTickets(Integer.valueOf(request.getParameter("numTickets")));
                button.setAvailable(Boolean.valueOf(request.getParameter("status")));
            }
            if(action.equals("delete-button")){
                equeue.deleteTerminalButton(Long.parseLong(request.getParameter("buttonId")));
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
        return "Сервлет для управления панелью администрирования";
    }// </editor-fold>

}
