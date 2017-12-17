/*
 *   Created on : 12.06.2017, 21:06:20
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.servlets;

import com.alexnerd.data.users.Operator;
import com.alexnerd.data.TerminalButton;
import com.alexnerd.data.users.Admin;
import com.alexnerd.data.users.UserRole;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alexnerd.data.users.EQueueUser;
import com.alexnerd.data.users.User;
import com.alexnerd.utils.db.EQueueDB;
import java.util.Properties;


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
        //HttpSession session = request.getSession();
        //EQueue equeue = (EQueue) request.getAttribute("equeue");
        //EQueue equeue = (EQueue) getServletContext().getAttribute("equeue");
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
                        EQueueDB.addUser(new Admin(login, password, sirname, name, middlename));
                        //EQueueDB.merge(null);
                        break;
                    case USER:
                        EQueueDB.addUser(new User(login, password, sirname, name, middlename));
                        //EQueueDB.merge(null);
                        break;
                    case OPERATOR:
                        EQueueDB.addUser(new Operator(login, password, sirname, name, middlename, false));
                        //EQueueDB.merge(null);
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестная роль");                
                }               
            }  
            if(action.equals("delete-user")){
                EQueueDB.deleteUser(Long.parseLong(request.getParameter("userId")));
            }  
            if(action.equals("save-user")){
                EQueueUser user = EQueueDB.getUserByIdAndRole(
                        Long.parseLong(request.getParameter("userId")),
                        UserRole.valueOf(request.getParameter("userRole")));
                user.setName(request.getParameter("userName"));
                user.setSirname(request.getParameter("userSirname"));
                user.setMiddlename(request.getParameter("userMiddlename"));
                user.setPassword(request.getParameter("userPassword"));
                if(UserRole.OPERATOR == user.getUserRole()){
                    ((Operator) user).setNumWindow(Integer.valueOf(request.getParameter("userNumWindow")));
                }                
            }
            if(action.equals("add-button")){
                EQueueDB.addTerminalButton(new TerminalButton(
                                    request.getParameter("buttonName"),
                                    request.getParameter("buttonPrefix"),
                                    Integer.valueOf(request.getParameter("numTickets")),
                                    Boolean.valueOf(request.getParameter("status"))
                                ));
            }
            if(action.equals("save-button")){
                TerminalButton button = EQueueDB.getTerminalButtonById(Long.parseLong(request.getParameter("buttonId")));
                button.setName(request.getParameter("name"));
                button.setPrefix(request.getParameter("prefix"));
                button.setNumTickets(Integer.valueOf(request.getParameter("numTickets")));
                button.setAvailable(Boolean.valueOf(request.getParameter("status")));
            }
            if(action.equals("delete-button")){
                EQueueDB.deleteTerminalButton(Long.parseLong(request.getParameter("buttonId")));
            }
            if(action.equals("check-login")){                
                try (PrintWriter out = response.getWriter()) {
                    out.println(EQueueDB.isUniqueLogin(request.getParameter("login")));
                    out.flush();
                }
            }
            if(action.equals("check-button-name")){
                try (PrintWriter out = response.getWriter()) {
                    out.println(EQueueDB.isUniqueTerminalButtonName(request.getParameter("buttonName")));
                    out.flush();
                }
            }
            if(action.equals("save-properties")){
                EQueueDB.setCompanyName(request.getParameter("companyName"));
                Properties props = new Properties();
                System.out.println("1");
                props.setProperty("dbAdress", request.getParameter("dbAdress"));
                System.out.println("2");
                props.setProperty("dbName", request.getParameter("dbName"));
                System.out.println("3");
                props.setProperty("dbLogin", request.getParameter("dbLogin"));
                System.out.println("4");
                props.setProperty("dbPassword", request.getParameter("dbPassword"));
                System.out.println("5");
                EQueueDB.setProperties(props);
            }
        }
        
        if(!response.isCommitted())
            getServletContext().getRequestDispatcher(url).forward(request, response);;
        
       //getServletContext().getRequestDispatcher(url).forward(request, response);
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
