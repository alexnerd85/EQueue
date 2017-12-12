<%-- 
    Document   : table-content
    Created on : 29.10.2017, 19:49:03
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="EQueueDB" uri="/tlds/EQueueDB.tld"%>
<!DOCTYPE html>

<c:forEach var="operator" items="${EQueueDB:getUsers()}">
    <c:if test="${operator.userRole eq 'OPERATOR'}">
        <c:if test="${operator.available}">
            <div class="table-content">
                <div class="table-window">
                    Окно № ${operator.numWindow}
                </div>
                <div class="table-ticket">
                    ${operator.ticket}
                </div>            
            </div>
        <hr/>
        </c:if>        
    </c:if>
</c:forEach>