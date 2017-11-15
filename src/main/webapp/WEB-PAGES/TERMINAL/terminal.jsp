<%--
    Document   : Terminal
    Created on : 10.06.2017, 18:29:05
    Author     : Popov Aleksey
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $( document ).ready(function() {
                 $('#button-block').load('WEB-PAGES/TERMINAL/terminal-button.jsp');
            });
    $(function(){
                window.setInterval(function(){
                   $('#button-block').load('WEB-PAGES/TERMINAL/terminal-button.jsp');
            }, 3000);
        });    
</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <title>Терминал</title>
    </head>
    <body>        
      <div class="flex-container">
        <div class="flex-item">
            <p><h1>${equeue.companyName}</h1></p>
            <hr class="hr-style">
        </div>
        <div id="button-block" class="flex-item">           
        </div>
      </div>
    </body>
</html>
