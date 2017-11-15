<%-- 
    Document   : button-table
    Created on : 11.11.2017, 15:09:56
    Author     : Popov Aleksey 2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $( ".delete-button" ).click(function() {
        var id = $(this).closest('td').find('input[name=buttonId]').val();
        $.post("admin",{action: "delete-button",
                        buttonId: id});
        $('.main-inner').load('WEB-PAGES/ADMIN-PAGES/button-table.jsp');
    });
    $( ".save-button" ).click(function() {
        var id = $(this).closest('td').find('input[name=buttonId]').val();  
        var name = $(this).closest('tr').find('input[name=name]').val();
        var prefix = $(this).closest('tr').find('input[name=prefix]').val();
        var numTickets = $(this).closest('tr').find('input[name=numTickets]').val();
        var status = $(this).closest('tr').find('select[name=status]').val();
        $.post("admin",{action: "save-button",
                        buttonId: id,
                        name: name,
                        prefix: prefix,
                        numTickets: numTickets,
                        status: status
                    });                    
    });
</script>
<!DOCTYPE html>
<table class="user-table">
    <tr>
        <th>Название</th>
        <th>Префикс</th>
        <th>Количество талонов</th>
        <th>Статус</th>
    </tr>
    <c:forEach var="button" items="${equeue.terminalButtons}">
        <tr>
            <td>
                <input name="name" value="<c:out value='${button.name}'/>">
            </td>
            <td>
                <input name="prefix" value="<c:out value='${button.prefix}'/>">
            </td>
            <td>
                <input name="numTickets" value="<c:out value='${button.numTickets}'/>">
            </td>
            <td>
                <select name="status" id="status">
                    <option disabled>Выберите статус</option>                
                    <option value="true" <c:if test="${button.available}">selected="selected"</c:if>>Активна</option>
                    <option value="false" <c:if test="${!button.available}">selected="selected"</c:if>>Неактивна</option>
                </select>
            </td>
            <td>
                <input name="buttonId" type="hidden" value="<c:out value='${button.buttonId}'/>">
                <input class="admin-pages-button save-button" type="submit" value="Сохранить">
            </td>
            <td>
                <input name="buttonId" type="hidden" value="<c:out value='${button.buttonId}'/>">
                <input class="admin-pages-button delete-button" type="submit" value="Удалить">
            </td>
        </tr>        
    </c:forEach>
</table>