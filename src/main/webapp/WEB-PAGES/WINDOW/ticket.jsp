<%-- 
    Document   : ticket
    Created on : 29.10.2017, 10:49:27
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${sessionScope.user.available}">
        Талон №<span style="color:green;">${sessionScope.user.ticket}</span>
    </c:when>
    <c:otherwise>
        <span style="color:red;">ОКНО НЕ АКТИВНО</span>
    </c:otherwise>
</c:choose>
        