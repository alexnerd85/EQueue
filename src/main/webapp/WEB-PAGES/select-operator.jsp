<%-- 
    Document   : select-operator
    Created on : 18.11.2017, 19:07:34
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
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            /*$(document).ready(function() {
                $("button").click(function(e){
                    $.post("equeuemain",{
                        main_action: "select_operator",
                        operatorId: e.target.value
                    });
                }); 
                $("form").on("submit", function (e) {
                    e.preventDefault();
                });
            });  */
        </script>
        <title>Выбор оператора</title>
    </head>
    <body>
        <div class="flex-container">
            <div class="flex-item">
              <p><h1>Выберите оператора:</h1></p>
              <hr class="hr-style">
            </div>
        </div>
        <div class="flex-item">
            <form  action="equeuemain" method="post">
                <c:forEach var="user" items="${equeue.users}">
                    <c:if test="${user.userRole eq 'OPERATOR'}">
                        <br>
                            <p><button type="submit" class="button" name="select_operator" value="${user.userId}">${user.userId}</button></p>
                        <br>
                    </c:if>
                </c:forEach>
            </form>
        </div> 
    </body>
</html>
