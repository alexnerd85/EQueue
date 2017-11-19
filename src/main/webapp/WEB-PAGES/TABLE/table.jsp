<%-- 
    Document   : Table
    Created on : 11.06.2017, 16:47:03
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page import="com.alexnerd.data.UserRole"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <link type="text/css" rel="stylesheet" href="styles/table.css">
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            $(function(){
                window.setInterval(function(){
                   $('#dinamic_content').load('WEB-PAGES/TABLE/table-content.jsp');
                }, 1000);
            });
        </script>
        <title>Очередь</title>
    </head>
    <body>
        <div class="table-main">
            <header class="table-company">${equeue.companyName}</header>
            <hr/>
            <div class="table-header">
                <div class="header-window">Окно</div>
                <div class="header-ticket">Талон</div>
            </div>
            <hr/>
            <div id="dinamic_content">        
            </div>
        </div>
	
</body>
</html>