<%-- 
    Document   : users-settings
    Created on : 16.10.2017, 18:58:25
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
        <link type="text/css" rel="stylesheet" href="styles/admin-pages.css">
        <link type="text/css" rel="stylesheet" media="screen" href="jquery-ui-1.12.1.custom/jquery-ui.css"/>
        <style>
            label, input { display:block; }
            input.text { margin-bottom:12px; width:95%; padding: .4em; }
            fieldset { padding:0; border:0; margin-top:25px; }
            h1 { font-size: 1.2em; margin: .6em 0; }
            div#users-contain { width: 350px; margin: 20px 0; }
            div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
            div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
            .ui-dialog .ui-state-error { padding: .3em; }
            .validateTips { border: 1px solid transparent; padding: 0.3em; }
        </style>
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script type="text/javascript">
            $( document ).ready(function() {
                 $('.main-inner').load('WEB-PAGES/ADMIN-PAGES/user-table.jsp');
            });
            $( function() {
                var dialog, form,
                  // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                  login = $( "#login" ),
                  role = $( "#role" ),
                  sirname = $( "#sirname" ),
                  name = $( "#name" ),
                  middlename = $( "#middlename" ),
                  password = $( "#password" ),
                  allFields = $( [] ).add(login).add(role).add(sirname).add(name).add(middlename).add(password),
                  tips = $( ".validateTips" );

                function updateTips( t ) {
                  tips
                    .text( t )
                    .addClass( "ui-state-highlight" );
                  setTimeout(function() {
                    tips.removeClass( "ui-state-highlight", 1500 );
                  }, 500 );
                }

                function checkLength( o, n, min, max ) {
                  if ( o.val().length > max || o.val().length < min ) {
                    o.addClass( "ui-state-error" );
                    updateTips( "Длина поля должна быть между " +
                      min + " и " + max + " символами." );
                    return false;
                  } else {
                    return true;
                  }
                }

                function checkRegexp( o, regexp, n ) {
                  if ( !( regexp.test( o.val() ) ) ) {
                    o.addClass( "ui-state-error" );
                    updateTips( n );
                    return false;
                  } else {
                    return true;
                  }
                }
                
                function checkRole(o, n){
                    if(o.val() === null){
                        o.addClass( "ui-state-error" );
                        updateTips( n );
                        return false;
                    } else {
                        return true;
                    }
                }

                function addUser() {                    
                    var valid = true;
                    allFields.removeClass( "ui-state-error" );

                    valid = valid && checkLength( login, "login", 3, 16);
                    valid = valid && checkLength( sirname, "sirname", 3, 16);
                    valid = valid && checkLength( name, "username", 3, 16 );
                    valid = valid && checkLength( middlename, "middlename", 3, 16 );
                    valid = valid && checkLength( password, "password", 5, 16 );

                    valid = valid && checkRegexp( login, /^([a-zA-Z])([0-9a-zA-Z_])+$/,"Логин должен начинаться с латинской буквы и может состоять из латинских букв, цифр и нижнего подчеркивания");
                    valid = valid && checkRole( role, "Выберите роль пользователя");
                    valid = valid && checkRegexp( sirname, /^([A-ZА-Я])([a-zа-я])+$/i, "Фамилия пользователя должна состоять только из букв и начинаться с заглавной буквы" );            
                    valid = valid && checkRegexp( name, /^([A-ZА-Я])([a-zа-я])+$/i, "Имя пользователя должно состоять только из букв и начинаться с заглавной буквы" );
                    valid = valid && checkRegexp( middlename, /^([A-ZА-Я])([a-zа-я])+$/i, "Отчество пользователя должно состоять только из букв и начинаться с заглавной буквы" );
                    valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Допустимые символы для пароля: a-z A-Z 0-9" );

                    if ( valid ) {                      
                        $.post("admin",{action:"add-user",
                                        userLogin:login.val(),
                                        userRole:role.val(),
                                        userSirname:sirname.val(),
                                        userName:name.val(),
                                        userMiddlename:middlename.val(),
                                        userPassword:password.val()});
                        $( document ).ready(function() {
                            $('.main-inner').load('WEB-PAGES/ADMIN-PAGES/user-table.jsp');
                        });
                        dialog.dialog( "close" );                     
                    }
                    return valid;
                }

                dialog = $( "#dialog-form" ).dialog({
                  autoOpen: false,
                  height: 600,
                  width: 450,
                  modal: true,
                  buttons: {
                    "Добавить пользователя": addUser,
                    "Отмена": function() {
                      dialog.dialog( "close" );
                    }
                  },
                  close: function() {
                    form[ 0 ].reset();
                    allFields.removeClass( "ui-state-error" );
                  }
                });

                form = dialog.find( "form" ).on( "submit", function( event ) {
                  event.preventDefault();
                  addUser();
                });

                $( "#create-user" ).button().on( "click", function() {
                  dialog.dialog( "open" );
                });
              } );
        </script>
        <title>Пользователи</title>
    </head>
    <body>
        <div id="dialog-form" title="Добавить нового пользователя">
            <p class="validateTips">Все поля обязательны.</p> 
            <form action="admin" method="post">
                <fieldset>
                    <label for="login">Логин</label>
                    <input type="text" name="login" id="login" placeholder="Введите логин" class="text ui-widget-content ui-corner-all">
                    <label for="role">Роль</label>
                    <select name="role" id="role">
                        <option disabled selected="selected">Выберите роль</option>
                        <c:forEach var="role" items="${equeue.userRoles}">
                            <option value="${role}">${role}</option>      
                        </c:forEach>
                    </select>
                    <br><br>
                    <label for="sirname">Фамилия</label>
                    <input type="text" name="sirname" id="sirname" placeholder="Введите фамилию" class="text ui-widget-content ui-corner-all">
                    <label for="name">Имя</label>
                    <input type="text" name="name" id="name" placeholder="Введите имя" class="text ui-widget-content ui-corner-all">
                    <label for="middlename">Отчество</label>
                    <input type="text" name="middlename" id="middlename" placeholder="Введите отчество" class="text ui-widget-content ui-corner-all">                    
                    <label for="password">Пароль</label>
                    <input type="password" name="password" id="password" class="text ui-widget-content ui-corner-all">
                    <!-- Allow form submission with keyboard without duplicating the dialog button -->
                    <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
                </fieldset>
             </form>
        </div> 
        <div class="main-inner"></div>
        <div class="footer-inner">
            <div class="footer-button">
                <button id="create-user">Добавить</button>
            </div>
        </div>
    </body>
</html>
