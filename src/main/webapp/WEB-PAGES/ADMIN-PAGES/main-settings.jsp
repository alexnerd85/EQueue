<%-- 
    Document   : main-settings
    Created on : 16.10.2017, 19:18:20
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="EQueueDB" uri="/tlds/EQueueDB.tld"%>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $(".admin-pages-button").click(function(){
        $.post("admin",{
            action: "save-properties",
            companyName: $("#companyName").val(),
            dbAdress: $("#dbAdress").val(),
            dbName: $("#dbName").val(),
            dbLogin: $("#dbLogin").val(),
            dbPassword: $("#dbPassword").val()
        });
    });
</script>
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
            <input id="companyName" class="admin-input" name="companyName" type="text" value="<c:out value='${EQueueDB:getCompanyName()}'/>">
        </div>        
        <div class="main-inner">
            <div class="text-field">    
                Адрес базы данных:               
            </div> 
            <input id="dbAdress" class="admin-input" name="dbAdress" type="text" value="<c:out value='${EQueueDB:getProperties().getProperty("dbAdress")}'/>">
        </div>
        <div class="main-inner">
            <div class="text-field">    
                Имя базы:              
            </div> 
            <input id="dbName" class="admin-input" name="dbName" type="text" value="<c:out value='${EQueueDB:getProperties().getProperty("dbName")}'/>">
        </div>
        <div class="main-inner">
            <div class="text-field">    
                Имя пользователя:                
            </div> 
            <input id="dbLogin" class="admin-input" name="dbLogin" type="text" value="<c:out value='${EQueueDB:getProperties().getProperty("dbLogin")}'/>">
        </div>
        <div class="main-inner">
            <div class="text-field">    
                Пароль:              
            </div> 
            <input id="dbPassword" class="admin-input" name="dbPassword" type="text" value="<c:out value='${EQueueDB:getProperties().getProperty("dbPassword")}'/>">
        </div>
        <div class="footer-inner">
            <div class="footer-button">    
                <button class="admin-pages-button" name="save-properties">Сохранить</button>
            </div>
        </div>
    </body>
</html>
