<%-- 
    Document   : user-table
    Created on : 07.11.2017, 21:43:14
    Author     : Popov Aleksey 2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $( ".delete-user" ).click(function() {
        var id = $(this).closest('td').find('input[name=userId]').val();
        $.post("admin",{action: "delete-user",
                        userId: id});
        $('.main-inner').load('WEB-PAGES/ADMIN-PAGES/user-table.jsp');
    });
    $( ".save-user" ).click(function() {
        var id = $(this).closest('td').find('input[name=userId]').val();  
        var sirname = $(this).closest('tr').find('input[name=sirname]').val();
        var name = $(this).closest('tr').find('input[name=name]').val();
        var middlename = $(this).closest('tr').find('input[name=middlename]').val();
        var password = $(this).closest('tr').find('input[name=password]').val();
        var role = $(this).closest('tr').find('td[name=userRole]').text();
        var numWindow='none';
        if(role === 'OPERATOR'){
            numWindow = $(this).closest('tr').find('input[name=windowNumber]').val();
        }
        $.post("admin",{action: "save-user",
                        userId: id,
                        userRole: role,
                        userSirname: sirname,
                        userName: name,
                        userMiddlename: middlename,
                        userPassword: password,
                        userNumWindow: numWindow
                    });                    
    });
</script>
<table class="user-table">
    <tr>
        <th>Логин</th>
        <th>Роль</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Пароль</th>
        <th>Номер окна<br>(для операторов)</th>
    </tr>            
    <c:forEach var="user" items="${equeue.users}">
        <tr>
            <td>${user.login}</td>
            <td name="userRole">${user.userRole}</td>
            <td>
                <input name="sirname" size="15" value="<c:out value='${user.sirname}'/>">
            </td>
            <td>
                <input name="name" size="15" value="<c:out value='${user.name}'/>">
            </td>
            <td>
                <input name="middlename" size="15" value="<c:out value='${user.middlename}'/>">
            </td>
            <td>
                <input type="password" name="password" size="15" value="<c:out value='${user.password}'/>">
            </td>
             <td>
                 <c:if test="${user.userRole eq 'OPERATOR'}">
                     <input name="windowNumber" size="15" value="<c:out value='${user.numWindow}'/>">
                 </c:if>                
            </td>
            <td>
                <input type="hidden" name="userId" value="<c:out value='${user.userId}'/>">
                <input class="admin-pages-button save-user" type="submit" value="Сохранить">
            </td>
            <td>
                <input type="hidden" name="userId" value="<c:out value='${user.userId}'/>">
                <input class="admin-pages-button delete-user" type="submit" value="Удалить">                     
            </td>
        </tr>                
    </c:forEach>
</table>
