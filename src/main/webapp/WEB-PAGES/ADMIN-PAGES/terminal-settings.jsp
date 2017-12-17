<%-- 
    Document   : terminal-settings
    Created on : 14.10.2017, 14:22:33
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
               $('.main-inner').load('WEB-PAGES/ADMIN-PAGES/button-table.jsp'); 
            });
            $( function() {
                var dialog, form,
                  // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                  name = $( "#name" ),
                  prefix = $( "#prefix" ),
                  numTickets = $( "#numTickets" ),
                  status = $( "#status" ),
                  allFields = $( [] ).add(name).add(prefix).add(numTickets).add(status),
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
                
                function checkStatus(o, n){
                    if(o.val() === null){
                        o.addClass( "ui-state-error" );
                        updateTips( n );
                        return false;
                    } else {
                        return true;
                    }
                }
                
                function checkName(o, n){
                    var isUnique;
                    $.ajax({
                        url: "admin",
                        type: "POST",
                        data: {action: "check-button-name", buttonName: o.val()},
                        success: function(data){
                            if($.trim(data) === 'false'){
                            o.addClass( "ui-state-error" );
                            updateTips( n );
                            isUnique = false;
                        } else{
                            isUnique = true;
                        }
                        },
                        dataType: "text",
                        async: false
                    });
                    return isUnique;
                }

                function addButton() {                    
                    var valid = true;
                    allFields.removeClass( "ui-state-error" );

                    valid = valid && checkLength( name, "name", 1, 30);
                    valid = valid && checkLength( prefix, "prefix", 1, 10);
                    valid = valid && checkLength( numTickets, "numTickets", 1, 6 );
                    valid = valid && checkStatus( status, "Выберите статус кнопки");
                    
                    valid = valid && checkRegexp( name, /^([a-zA-Zа-яА-Я])([0-9a-zA-Zа-яА-Я_])+$/,"Название должно начинаться с буквы и может состоять из латинских букв, цифр и нижнего подчеркивания");
                    valid = valid && checkName( name, "Кнопка с таким именем уже существует");    
                    valid = valid && checkRegexp( prefix, /^([a-zA-Zа-яА-Я])+$/i, "Префикс талона должен состоять только из букв и начинаться с заглавной буквы" );            
                    valid = valid && checkRegexp( numTickets, /^([0-9])+$/i, "Количество талонов должно состоять только из цифр" );

                    if ( valid ) {                      
                        $.post("admin",{action:"add-button",
                                        buttonName:name.val(),
                                        buttonPrefix:prefix.val(),
                                        numTickets:numTickets.val(),
                                        status:status.val()});
                        $( document ).ready(function() {
                            $('.main-inner').load('WEB-PAGES/ADMIN-PAGES/button-table.jsp');
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
                    "Добавить кнопку": addButton,
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

                $( "#create-button" ).button().on( "click", function() {
                  dialog.dialog( "open" );
                });
              } );
        </script>
        <title>Настройки терминала</title>
    </head>
    <body>
        <div class="main-inner"></div>
        <div class="footer-inner">
            <div class="footer-button">
                <button id="create-button">Добавить</button>
            </div>
        </div>
    </body>
</html>
<div id="dialog-form" title="Добавить новую кнопку">
    <p class="validateTips">Все поля обязательны.</p> 
    <form action="admin" method="post">
        <fieldset>
            <label for="name">Название</label>
            <input type="text" name="name" id="name" placeholder="Введите название кнопки" class="text ui-widget-content ui-corner-all">
            <label for="prefix">Префикс</label>
            <input type="text" name="prefix" id="prefix" placeholder="Введите префикс для талона" class="text ui-widget-content ui-corner-all">
            <label for="numTickets">Количество талонов</label>
            <input type="text" name="numTickets" id="numTickets" placeholder="Введите количество талонов" class="text ui-widget-content ui-corner-all">
            <label for="status">Статус кнопки</label>
            <select name="status" id="status">
                <option disabled selected="selected">Выберите статус</option>
                <option value="true">Активна</option>
                <option value="false">Неактивна</option>
            </select>                    
            <!-- Allow form submission with keyboard without duplicating the dialog button -->
            <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
        </fieldset>
    </form>
</div> 
