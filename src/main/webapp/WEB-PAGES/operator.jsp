<%-- 
    Document   : operator
    Created on : 26.09.2017, 18:46:15
    Author     : Popov Aleksey 2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <link type="text/css" rel="stylesheet" href="styles/operator.css">
        <title>Operator</title>
    </head>
    <body>
        <div class="header">
            <h1>Окно № ${sessionScope.user.numWindow}</h1>
        </div>
        <div class="main">
            <div class="ticket">
                Талон №<span style="color: green;">${sessionScope.user.ticket}</span>
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
