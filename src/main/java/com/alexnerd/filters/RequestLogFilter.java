/*
 *   Created on : 15.12.2017, 20:22:43
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.filters;

import com.alexnerd.data.RequestLog;
import com.alexnerd.data.users.EQueueUser;
import com.alexnerd.utils.db.EQueueDB;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.StopWatch;

/**
 *
 * @author Aleksey
 */
public class RequestLogFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LocalDateTime dateTime = LocalDateTime.now();
        StopWatch timer = new StopWatch();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        EQueueUser user = (EQueueUser) session.getAttribute("user");
        try {
            timer.start();
            chain.doFilter(request, response);
        } finally {
            timer.stop();
            HttpServletRequest in = (HttpServletRequest) request;
            HttpServletResponse out = (HttpServletResponse) response;
            String length = out.getHeader("Content-Length");
            if(length == null || length.length() == 0)
                length = "-";
            
            RequestLog log = new RequestLog(in.getRemoteAddr(), dateTime, 
                    in.getMethod(), in.getRequestURI(), in.getProtocol(), 
                    out.getStatus(), length, timer.toString(), user);
            EQueueDB.addLogEntry(log);
            System.out.println(log);
        }
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
