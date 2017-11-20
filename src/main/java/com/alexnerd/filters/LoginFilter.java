/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
    Created on : 19.11.2017
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue

*/

package com.alexnerd.filters;

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
 * @author Aleksey
 */
public class LoginFilter implements Filter{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);
        String loginURI = "/EQueue/login";
        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        
        if(checkURI(path) || loggedIn || loginRequest ){
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean checkURI(String uri){
        List<String> allowedURI = Arrays.asList("/EQueue",
                                                "/EQueue/",
                                                "/EQueue/login",
                                                //"/EQueue/favicon.ico",
                                                "/EQueue/styles/index.css",
                                                "/EQueue/jquery-3.2.1.min.js",
                                                "/EQueue/jquery-ui-1.12.1.custom/jquery-ui.min.js",
                                                "/EQueue/jquery-ui-1.12.1.custom/jquery-ui.css");
        return allowedURI.stream().anyMatch(uri::equals);
    }    
}
