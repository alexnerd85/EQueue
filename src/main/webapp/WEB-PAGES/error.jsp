<%-- 
    Document   : error
    Created on : 27.09.2017, 12:12:34
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <title>Ошибка</title>
    </head>
    <body>
        <h1>Ой! Произошло что-то плохое :(</h1>
        <p>
            Ошибка: ${pageContext.exception}
        </p>
    </body>
</html>
