<%-- 
    Document   : Window
    Created on : 11.06.2017, 18:38:18
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <link type="text/css" rel="stylesheet" href="styles/window.css">
        <title>Окно № ${equeue.getOperator(1).getNumber()}</title>
    </head>
    <body>
        <div class="header">
            Окно № ${equeue.getOperator(1).getNumber()}
        </div>
        <hr>
        <div class="main">
            Талон №<span style="color:green;">${equeue.getFirstTicket()}</span>
        </div>
        <hr>
        <div class="footer">
            ${equeue.configuration.companyName}
        </div>
    </body>
</html>
