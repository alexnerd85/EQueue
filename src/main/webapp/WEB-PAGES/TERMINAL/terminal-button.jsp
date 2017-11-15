<%-- 
    Document   : terminal-button
    Created on : 11.11.2017, 20:07:26
    Author     : Popov Aleksey 2017
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link type="text/css" rel="stylesheet" href="styles/main.css">
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $("button").click(function(e){
        $.post("terminal",{buttonId: e.target.value},function(data){
            var divPrint = $(data).html();
            var printWindow = window.open('', '', 'height=700,width=800');
            printWindow.document.write(divPrint);
            printWindow.document.close();
            printWindow.print();
        });
        $('#button-block').load('WEB-PAGES/TERMINAL/terminal-button.jsp');
    });
    $( "form" ).on("submit", function(e){
        e.preventDefault();
    });
    /*$( ".button" ).click(function(){
            var divPrint = $("<div><p>TEST PRINT</p></div>").html();
            var printWindow = window.open('', '', 'height=700,width=800');
            printWindow.document.write(divPrint);
            printWindow.document.close();
            printWindow.print();            
            
        }); */   
    //Loop through the data and append each value wrapped in a p to the div
    //results.append("<p>" + myData[i].displayName + "</p>");
    //results.append("<p>" + myData[i].displayNameEvent + "</p>");
    //results.append("<p>" + myData[i].displayNameArtist + "</p>");

    //append the div to the body
    
</script>
<!DOCTYPE html>
<form action="terminal" method="post">
    <c:forEach var="item" items="${equeue.terminalButtons}">
        <c:if test="${item.available}">
            <br>
            <button type="submit" class="button" name="btn" value="${item.buttonId}">${item.name}</button>
            <br>
        </c:if>
    </c:forEach>
</form>