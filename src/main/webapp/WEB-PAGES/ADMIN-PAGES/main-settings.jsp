<%-- 
    Document   : main-settings
    Created on : 16.10.2017, 19:18:20
    Author     : Popov Aleksey 2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/admin-pages.css">
    </head>
    <body>
        <div class="main-inner">
            <div class="text-field">    
                Название организации:               
            </div>  
            <input class="admin-input" name="companyName" type="text" value="<c:out value='${equeue.companyName}'/>">
        </div>        
        <div class="main-inner">
            <div class="text-field">    
                Адрес базы данных:               
            </div> 
            <input class="admin-input" name="companyName" type="text" value="<c:out value='${equeue.companyName}'/>">
        </div>
        <div class="main-inner">
            <div class="text-field">    
                Имя базы:              
            </div> 
            <input class="admin-input" name="companyName" type="text" value="<c:out value='${equeue.companyName}'/>">
        </div>
        <div class="main-inner">
            <div class="text-field">    
                Имя пользователя:                
            </div> 
            <input class="admin-input" name="companyName" type="text" value="<c:out value='${equeue.companyName}'/>">
        </div>
        <div class="main-inner">
            <div class="text-field">    
                Пароль:              
            </div> 
            <input class="admin-input" name="companyName" type="text" value="<c:out value='${equeue.companyName}'/>">
        </div>
        <div class="footer-inner">
            <div class="footer-button">    
                <button class="admin-pages-button">Сохранить</button>
            </div>
        </div>
    </body>
</html>
