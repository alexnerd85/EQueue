<%-- 
    Document   : Table
    Created on : 11.06.2017, 16:47:03
    Author     : Popov Aleksey
    Site       : alexnerd.com
    Email      : alexnerd85@gmail.com
    GitHub     : https://github.com/alexnerd85/EQueue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="styles/main.css">
        <link type="text/css" rel="stylesheet" href="styles/table.css">
        <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
        <script type="text/javascript">            
            /*        
            $(function(){
                window.setInterval(function(){
                   $('#dinamic_content').load('WEB-PAGES/TABLE/table-content.jsp');
                }, 1000);
            });*/
            
            var users=[];
            $(function(){
               window.setInterval(function(){
                   $.post("table",{action:"get-operators"},function(data){
                        if(data.length){
                            var arr = data.split(";").filter(function(v){return v!=='';});
                            for(var i = 0; i < arr.length; i++){
                                var user ={};
                                $.each(arr[i].split(','), function(index, val) {                            
                                    user[index] = val;
                                });
                                var include = false;
                                for(var j = 0; j < users.length; j++){
                                    if(user[0] === users[j][0]){
                                        if(user[1] !== users[j][1]){
                                            $('#windowNum'+user[0]).remove();
                                            users.splice(j,1);
                                        } else {
                                            include = true;
                                        }
                                        if(user[2] === "REPEAT"){
                                            $.post("table",{action: "change-ticket-status",
                                                            window: user[0],
                                                            ticket: user[1],
                                                            status: "INWORK"});
                                            $('#windowNum'+user[0]).remove();
                                            users.splice(j,1);
                                            include = false;
                                        }
                                    } 
                                }                                
                                if(include === false){
                                    users.push(user);
                                    $('#dinamic_content').prepend($('<div id=\"windowNum'+user[0]+'\" class="table-content"><div class="table-window">Окно № '+ user[0] +'</div><div class="table-ticket">'+ user[1] +'</div></div>'));
                                    $('#windowNum'+user[0]).blink(3);
                                }
                            }
                            for(var j = 0; j < users.length; j++){
                            var found = false;                        
                                for(var k = 0; k < arr.length; k++){
                                        var user ={};
                                        $.each(arr[k].split(','), function(index, val) {                            
                                            user[index] = val;
                                        });
                                        if(users[j][0] == user[0]){
                                            found = true;
                                        }

                                }
                                if(found === false){
                                    $('#windowNum'+users[j][0]).remove();
                                    users.splice(j,1);
                                }
                            }
                        }
                    });
                    $('.table-content').each(function(index){
                        if ($(this).offset().top > $(window).height()) {
                            $(this).hide();
                        }
                    });
               }, 2000); 
            });
            /*$.post("table",{action:"get-operators"},function(data){
                if(data.length){
                    var arr = data.split(";").filter(function(v){return v!=='';});
                    for(var i = 0; i < arr.length; i++){
                        var user ={};
                        $.each(arr[i].split(','), function(index, val) {                            
                            user[index] = val;
                        });
                        var include = false;
                        for(var j = 0; j < users.length; j++){
                            if(user[0] === users[j][0]){
                                include = true;
                            } 
                        }
                        if(include === false){
                            users.push(user);
                            $('#dinamic_content').prepend($('<div class="table-content"><div class="table-window">Окно № '+ user[0] +'</div><div class="table-ticket">'+ user[1] +'</div></div>')).blink(3);
                        }
                    }
                }
            });*/
            
            $.fn.blink = function (count) {
                var $this = $(this);
                count = count - 1 || 0;
                $this.animate({opacity: 0}, 650, function () {
                    $this.animate({opacity: 1}, 650, function () {
                        if (count > 0) {
                            $this.blink(count);
                        }
                    });
                });
            };
        </script>
        <title>Очередь</title>
    </head>
    <body>
        <div class="table-main">
            <header class="table-company">${equeue.companyName}</header>
            <hr/>
            <div class="table-header">
                <div class="header-window">Окно</div>
                <div class="header-ticket">Талон</div>
            </div>
            <hr/>
            <div id="dinamic_content">    
                
            </div>
        </div>
	
</body>
</html>