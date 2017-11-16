<%-- 
    Document   : index
    Created on : 12.06.2017, 21:06:20
    Author     : Popov Aleksey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
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
        <link type="text/css" rel="stylesheet" href="styles/index.css">
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
        <script type="text/javascript">     
             $( function() {
                var dialog, form,
                  // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                  dbAdress = $("#dbAdress"),
                  dbName = $( "#dbName" ),
                  dbLogin = $( "#dbLogin" ),
                  dbPassword = $( "#dbPassword" ),
                  adminLogin = $( "#adminLogin" ),
                  adminPassword = $( "#adminPassword" ),
                  allFields = $( [] ).add(dbAdress).add(dbName).add(dbLogin).add(dbPassword).add(adminLogin).add(adminPassword),
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

                function addConfig() {                    
                    var valid = true;
                    allFields.removeClass( "ui-state-error" );

                    valid = valid && checkLength( dbAdress, "dbAdress", 1, 255);
                    valid = valid && checkLength( dbName, "dbName", 1, 255);
                    valid = valid && checkLength( dbLogin, "dbLogin", 3, 16 );
                    valid = valid && checkLength( dbPassword, "dbPassword", 6, 30 );
                    valid = valid && checkLength( adminLogin, "adminLogin", 3, 16 );
                    valid = valid && checkLength( adminPassword, "adminPassword", 5, 16 );

                    valid = valid && checkRegexp( dbAdress, /^([a-zA-Z])([0-9a-zA-Z_])+$/,"Адрес для подключения к базе данных должен начинаться с латинской буквы и может состоять из латинских букв, цифр и нижнего подчеркивания");
                    valid = valid && checkRegexp( dbName, /^([a-zA-Z0-9])+$/i, "Имя базы данных должно состоять только из букв латинского алфавита и цифрв" );            
                    valid = valid && checkRegexp( dbLogin, /^([A-Za-z0-9])+$/i, "Логин для подключения к базе данных должен состоять только из букв латинского алфавита и цифр" );
                    valid = valid && checkRegexp( dbPassword, /^([A-ZА-Яа-яa-z0-9])+$/i, "Пароль может состоять из букв и цифр" );
                    valid = valid && checkRegexp( adminLogin, /^([0-9a-zа-яА-ЯA-Z])+$/, "Логин для подключения к программе может состоять из букв и цифр" );
                    valid = valid && checkRegexp( adminPassword, /^([0-9a-zA-Z])+$/, "Допустимые символы для пароля: a-z A-Z 0-9" );

                    if ( valid ) {                      
                        $.post("equeuemain",{action:"main_action",
                                        main_action:"add-config",
                                        dbAdress:dbAdress.val(),
                                        dbName:dbName.val(),
                                        dbLogin:dbLogin.val(),
                                        dbPassword:dbPassword.val(),
                                        adminLogin:adminLogin.val(),
                                        adminPassword:adminPassword.val()});
                        dialog.dialog( "close" );                     
                    }
                    return valid;
                }

                dialog = $( "#wizard-form" ).dialog({
                  autoOpen: false,
                  height: 600,
                  width: 500,
                  modal: true,
                  show: {
                    effect: 'fade',
                    duration: 800  
                  },
                  buttons: {
                    "Сохранить": addConfig,
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
                  addConfig();
                });

                $( "#wizard-form" ).ready( function() {
                    $.post("equeuemain", {action: "main_action",
                                          main_action:"checkConfig"},function(data){
                        if($.trim(data) === 'true'){
                            dialog.dialog( "open" );
                        }  
                    });                                    
                });
                  
              });
        </script>
        <title>Электронная очередь</title>
    </head>
    <body>
        <div id="wizard-form" title="Начальная настройка">
            <p class="validateTips">Все поля обязательны.</p> 
            <form action="equeuemain" method="post">
                <fieldset>
                    <label for="dbAdress">Адрес базы данных</label>
                    <input type="text" name="dbAdress" id="dbAdress" placeholder="Введите адрес базы данных" class="text ui-widget-content ui-corner-all">
                    <label for="dbName">Имя базы данных</label>
                    <input type="text" name="dbName" id="dbName" placeholder="Введите имя базы данных" class="text ui-widget-content ui-corner-all">
                    <label for="dbLogin">Логин для подключения к базе данных</label>
                    <input type="text" name="dbLogin" id="dbLogin" placeholder="Введите логин для подключения к базе данных" class="text ui-widget-content ui-corner-all">
                    <label for="dbPassword">Пароль для подключения к базе данных</label>
                    <input type="password"  type="text" name="dbPassword" id="dbPassword" placeholder="Введите пароль для подключения к базе данных" class="text ui-widget-content ui-corner-all">                    
                    <label for="adminLogin">Логин администратора электронной очереди</label>
                    <input name="adminLogin" id="adminLogin" placeholder="Введите логин администратора электронной очереди" class="text ui-widget-content ui-corner-all">
                    <label for="adminPassword">Пароль администратора электронной очереди</label>
                    <input type="password" name="adminPassword" id="adminPassword" placeholder="Введите пароль администратора электронной очереди" class="text ui-widget-content ui-corner-all">
                    <!-- Allow form submission with keyboard without duplicating the dialog button -->
                    <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
                </fieldset>
             </form>
        </div> 
        <div class="form-login">
        <form class="form-container">
            <div class="form-title"><h2>Электронная очередь</h2></div>
            <div class="form-title">Логин</div>
                <input class="form-field" type="text" name="firstname" /><br />
            <div class="form-title">Пароль</div>
                <input class="form-field" type="text" name="email" /><br />
            <div class="submit-container">
                <button class="submit-button" type="submit" value="Войти">Войти</button>
            </div>
        </form>
        </div>
    </body>
</html>
