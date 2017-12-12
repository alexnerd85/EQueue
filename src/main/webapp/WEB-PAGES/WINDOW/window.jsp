<%-- 
    Document   : Window
    Created on : 11.06.2017, 18:38:18
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="EQueueDB" uri="/tlds/EQueueDB.tld"%>
<%@taglib prefix="EQueueUserDB" uri="/tlds/EQueueUserDB.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <link type="text/css" rel="stylesheet" href="styles/window.css">
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            $(function(){
                window.setInterval(function(){
                   $('#ticket').load('WEB-PAGES/WINDOW/ticket.jsp');
                }, 1000);
            });
        </script>
        <title>Окно № ${empty sessionScope.hiddenUser ? EQueueUserDB:getNumWindow(sessionScope.user) :EQueueUserDB:getNumWindow(sessionScope.hiddenUser)}</title>
    </head>
    <body>
        <div class="header">
            Окно № ${empty sessionScope.hiddenUser ? EQueueUserDB:getNumWindow(sessionScope.user) : EQueueUserDB:getNumWindow(sessionScope.hiddenUser)}
        </div>
        <hr>
        <div class="main">
            <div id="ticket"></div>            
        </div>
        <hr>
        <div class="footer">
            ${EQueueDB:getCompanyName()}            
        </div>
    </body>
</html>
