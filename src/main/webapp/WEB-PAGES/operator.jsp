<%-- 
    Document   : operator
    Created on : 26.09.2017, 18:46:15
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <link type="text/css" rel="stylesheet" href="styles/operator.css">
        <title>Оператор</title>
    </head>
    <body>
        <div class="header">
            <h1>Окно № ${empty sessionScope.hiddenUser ? sessionScope.user.numWindow : sessionScope.hiddenUser.numWindow}</h1>
        </div>
        <div class="main">
            <div class="ticket">
                <c:choose>
                    <c:when test="${empty sessionScope.hiddenUser ? sessionScope.user.available : sessionScope.hiddenUser.available}">
                        Талон №<span style="color: green;">${empty sessionScope.hiddenUser ? sessionScope.user.ticket : sessionScope.hiddenUser.ticket}</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color:red; font-size: 60%;">ОКНО НЕ АКТИВНО</span>
                    </c:otherwise>
                </c:choose>                
            </div>            
            <form  action="operator" method="post">
                <div class="buttons-block">
                    <button class="button" type="submit" name="operator_action" value="begin">Начать работу</button>
                    <button class="button" type="submit" name="operator_action" value="next">Следующий</button>
                    <button class="button" type="submit" name="operator_action" value="repeat">Повторить</button>
                    <button class="button" type="submit" name="operator_action" value="skip">Пропустить</button>
                    <button class="button" type="submit" name="operator_action" value="cancel">Отменить</button>
                    <button class="button" type="submit" name="operator_action" value="off">Завершить работу</button>
                </div>
            </form>
        </div>
        <div class="footer">
            Очередь:
            <br/>
            Общее количество человек: ${equeue.getQueueLength()}
        </div>       
    </body>
</html>
