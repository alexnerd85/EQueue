<%-- 
    Document   : Table
    Created on : 11.06.2017, 16:47:03
    Author     : Popov Aleksey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/Table.css"> 
        <title>JSP Page</title>
    </head>
    <body>
	<header>${equeue.configuration.companyName}</header>
	<table>
            <tr>
		<th>ОКНО</th>
		<th>НОМЕР<br>ТАЛОНА</th>
		<th colspan="4">ОЧЕРЕДЬ</th>
            </tr>
            <tr>
		<td>ОКНО 1</td>
		<td id="nTalon">Т2</td>
		<td id="QueueUpper" colspan="4"></td>
            </tr>
            <tr>
		<td>ОКНО 2</td>
		<td id="nTalon"></td>
		<td class="Queue"></td>
                <td class="Queue"></td>
                <td class="Queue"></td>
                <td class="Queue"></td>
            </tr>
		<tr>
                <td>ОКНО 3</td>
		<td id="nTalon">Т1</td>
		<td colspan="4"></td>
            </tr>
	</table>
	<footer>Бегущая строка</footer>
</body>
</html>