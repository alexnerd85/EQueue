<%--
    Document   : EQueueMain
    Created on : 10.06.2017, 17:15:12
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="EQueue icon" href="favicon.ico"/>
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <title>Электронная очередь - Главное меню</title>
    </head>
    <body>
        <div class="flex-container">
            <div class="flex-item">
              <p><h1>ГЛАВНОЕ МЕНЮ</h1></p>
              <hr class="hr-style">
            </div>
            <div class="flex-item">
                <form  action="equeuemain" method="post">
                    <c:if test="${sessionScope.user.userRole eq 'ADMIN'}">
                        <br>
                        <p><button class="button" name="main_action" value="administration">Администрирование</button></p>
                        <br>
                    </c:if>
                    <c:if test="${sessionScope.user.userRole eq 'ADMIN' or sessionScope.user.userRole eq 'USER'}">
                        <p><button class="button" name="main_action" value="terminal">Терминал</button></p>
                        <br>
                        <p><button class="button" name="main_action" value="table">Расписание</button></p>
                        <br>
                    </c:if>
                    <c:if test="${sessionScope.user.userRole eq 'ADMIN' or sessionScope.user.userRole eq 'OPERATOR'}">
                        <p><button class="button" name="main_action" value="operator">Оператор</button></p>
                        <br>
                        <p><button class="button" name="main_action" value="window">Окно</button></p>
                        <br>
                    </c:if>
                    <c:if test="${sessionScope.user.userRole eq 'ADMIN'}">
                        <p><button class="button" name="main_action" value="statistic">Статистика</button></p>
                        <br>
                    </c:if>                   
                    <p><button class="button" name="main_action" value="exit">Выход</button></p>
                    <br>
                </form>
            </div>
        </div>
    </body>
</html>