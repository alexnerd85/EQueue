/*
 *   Created on : 14.12.2017, 21:14:43
 *   Author     : Popov Aleksey
 *   Site       : alexnerd.com
 *   Email      : alexnerd85@gmail.com
 *   GitHub     : https://github.com/alexnerd85/EQueue
 */

package com.alexnerd.filters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Aleksey
 */
public class CompressionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("************************************** FILTER COMPRESSION");
        if(((HttpServletRequest) request).getHeader("Accept-Encoding").contains("gzip")){
            System.out.println("Encoding requested");
            ((HttpServletResponse) response).setHeader("Content-Encoding", "gzip");
            ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse)response);
            try{
                chain.doFilter(request, wrapper);
            } finally {
                try{
                    wrapper.finish();
                } catch (Exception ex) {
                    System.out.println("do Filter CompressionFilter exception " + ex.getMessage());
                }
            }
        } else {
            System.out.println("Encoding nit requested");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static class ResponseWrapper extends HttpServletResponseWrapper{
        
        private GZIPServletOutputStream outputStream;
        private PrintWriter writer;
        
        public ResponseWrapper(HttpServletResponse response){
            super(response);
        }
        
        @Override
        public synchronized ServletOutputStream getOutputStream() throws IOException{
            if(writer != null)
                throw new IllegalStateException("getWriter() allready called");
            if(outputStream == null)
                outputStream = new GZIPServletOutputStream(super.getOutputStream());
            return outputStream;
        }
        
        @Override
        public synchronized PrintWriter getWriter() throws IOException{
            if(writer == null && outputStream != null)
                throw new IllegalStateException("getOutputStream() already called");
            if(writer == null){
                outputStream = new GZIPServletOutputStream(super.getOutputStream());
                writer = new PrintWriter(new OutputStreamWriter(outputStream, this.getCharacterEncoding()));
            }
            return writer;
        }
        
        @Override
        public void flushBuffer() throws IOException{
            if(writer != null)
                writer.flush();
            else if(outputStream != null)
                outputStream.flush();
            super.flushBuffer();
        }
        
        @Override
        public void setHeader(String name, String value){
            if(!"content-length".equalsIgnoreCase(name))
                super.setHeader(name, value);
        }
        
        @Override
        public void addHeader(String name, String value){
            if(!"content-length".equalsIgnoreCase(name))
                super.addHeader(name, value);
        }
        
        @Override
        public void setIntHeader(String name, int value){
            if(!"content-length".equalsIgnoreCase(name))
                super.setIntHeader(name, value);
        }
        
        @Override
        public void addIntHeader(String name, int value){
            if(!"content-length".equalsIgnoreCase(name))
                super.addIntHeader(name, value);
        }

        private void finish() throws IOException {
            if(writer != null)
               writer.close();
            else if(outputStream != null)
                outputStream.finish();
        }
    }
    
    private static class GZIPServletOutputStream extends ServletOutputStream{
        
        private final ServletOutputStream servletOutputStream;
        private final GZIPOutputStream gzipStream;
        
        public GZIPServletOutputStream(ServletOutputStream servletOutputStream) 
                throws IOException{
            this.servletOutputStream = servletOutputStream;
            this.gzipStream = new GZIPOutputStream(servletOutputStream);
        }

        @Override
        public boolean isReady() {
            return servletOutputStream.isReady();
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            servletOutputStream.setWriteListener(writeListener);
        }

        @Override
        public void write(int b) throws IOException {
            gzipStream.write(b);
        }
        
        @Override
        public void close() throws IOException{
            gzipStream.close();
        }
        
        @Override
        public void flush() throws IOException{
            this.gzipStream.flush();
        }
        
        public void finish() throws IOException{
            this.gzipStream.finish();
        }
    }
    
}
