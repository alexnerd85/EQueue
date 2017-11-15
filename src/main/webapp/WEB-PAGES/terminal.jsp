<%--
    Document   : Terminal
    Created on : 10.06.2017, 18:29:05
    Author     : Popov Aleksey
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <p><h1>${equeue.configuration.companyName}</h1></p>
            <hr class="hr-style">
        </div>
        <div class="flex-item">
            <form action="terminal" method="post">
                <c:forEach var="item" items="${equeue.configuration.terminalButtons}">
                    <c:if test="${item.available}">
                        <br>
                        <button type="submit" class="button" name="btn" value="${item.name}">${item.name}</button>
                        <br>
                    </c:if>
                </c:forEach>
            </form>
        </div>
      </div>
    </body>
</html>
