<%-- 
    Document   : Admin
    Created on : 11.06.2017, 18:37:55
    Author     : Popov Aleksey 2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/admin.css">
        <link type="text/css" rel="stylesheet" href="styles/font-awesome-4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#content').load('WEB-PAGES/ADMIN-PAGES/main-settings.jsp');
                $('#title').text('Общие настройки');
            });
        </script>
        <title>Admin panel</title>        
    </head>
    <body>
        <div class="header">
            <div class="main-header">
                <span style="padding: 10px">Настройки</span>
            </div>
            <div class="text-header">
                <span id="title" style="padding: 10px;"></span>
            </div>
        </div> 
        <div class="main">
            <div class="side-nav">
                <nav id="slide-menu">
                    <ul>
                        <a href="#" onclick="
                            $(function(){
                                $('#content').load('WEB-PAGES/ADMIN-PAGES/main-settings.jsp');
                                $('#title').text('Общие настройки');
                            });">
                            <li tabindex="1">
                                <i class="fa fa-tachometer" aria-hidden="true"></i>
                                <span class="text-nav">Общие</span>                                
                            </li>
                        </a>
                        <a href="#" onclick="
                            $(function(){
                                $('#content').load('WEB-PAGES/ADMIN-PAGES/terminal-settings.jsp');
                                $('#title').text('Настройки терминала');
                            });">
                            <li tabindex="1">
                                <i class="fa fa-hand-pointer-o" aria-hidden="true"></i>
                                <span class="text-nav">Терминал</span> 
                            </li>
                        </a>
                        <a href="#" onclick="
                            $(function(){
                                $('#content').load('WEB-PAGES/ADMIN-PAGES/table-settings.jsp');
                                $('#title').text('Настройки расписания');
                            });">
                            <li tabindex="1">
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <span class="text-nav">Расписание</span>
                            </li>
                        </a>
                        <a href="#" onclick="
                            $(function(){
                                $('#content').load('WEB-PAGES/ADMIN-PAGES/window-settings.jsp');
                                $('#title').text('Настройки окна');
                            });">
                            <li tabindex="1">
                                <i class="fa fa-desktop" aria-hidden="true"></i>
                                <span class="text-nav">Окно</span>
                            </li>
                        </a>
                        <a href="#" onclick="
                            $(function(){
                               $('#content').load('WEB-PAGES/ADMIN-PAGES/users-settings.jsp');
                               $('#title').text('Настройки пользователей');
                           });">
                            <li tabindex="1">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span class="text-nav">Пользователи</span>
                            </li>
                        </a>
                        <a href="equeuemain">
                            <li>
                                <i class="fa fa-sign-out" aria-hidden="true"></i>
                                <span class="text-nav">Выход в меню</span>
                            </li>
                        </a>
                    </ul>
                </nav>
            </div>         
            <div class="main-content">
                <div id="content"/>
            </div>
        </div>
    </body>
</html>
