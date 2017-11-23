/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexnerd.filters;

import com.alexnerd.data.users.User;
import com.alexnerd.data.users.UserRole;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *   @Created on : 19.11.2017
 *   @Author     : Popov Aleksey
 *   @Site       : alexnerd.com
 *   @Email      : alexnerd85@gmail.com
 *   @GitHub     : https://github.com/alexnerd85/EQueue
 */

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI();
        HttpSession session = req.getSession();
        String loginURI = "/EQueue/";
        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        
        User user = (User) session.getAttribute("user");
        System.out.println("*************************************** FILTER USER");
        
        if(user != null){
            System.out.println("*************************************** USER PATH " + path);
            if(checkAccess(user.getUserRole(), path)){
                chain.doFilter(req, resp);
            } else{
                throw new ServletException("Access denied for your role");
            }        
        } else {
            chain.doFilter(req, resp);
        }
        
        
    }
    
    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private boolean checkAccess(UserRole userRole, String uri) throws ServletException{
        List<String> allowedURI = null;
        switch(userRole){
            case ADMIN:
                return true;
            case OPERATOR:
                allowedURI = Arrays.asList("/EQueue/equeuemain",
                                            "/EQueue/operator", 
                                            "/EQueue/WEB-PAGES/WINDOW",
                                            "/EQueue/WEB-PAGES/equeuemain.jsp",
                                            "/EQueue/WEB-PAGES/error.jsp");                
                break;
            case USER:
                allowedURI = Arrays.asList("/EQueue/equeuemain", 
                                           "/EQueue/WEB-PAGES/TABLE/",
                                           "/EQueue/WEB-PAGES/TERMINAL/",
                                           "/EQueue/WEB-PAGES/error.jsp");
                break;
            default:
                throw new ServletException("Unknown user role in user filter");
        }
        return allowedURI.stream().anyMatch(uri::startsWith);
    }
}
