<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.ArrayList" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello</title>
<style>
</style>
</head>
<body>
<form id="chat">
<input name ="text" type ="text"/> 
<input type ="submit"/> 
</form>
<div id ="com">
</div>
<%
  String nul = (String)request.getAttribute("null");
 if(nul!=null){out.println(nul);}%>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var text = $('#chat input[name=text]').val();
	 $("#chat").submit(function() {
	  if(text ==""){
		 alert("Введите сообщение")
	  }else{
	   $.ajax({
	   type: "POST",
	   url: "Chat",
	   data: $("#chat").serialize(),
	   success: function(data){
		   $('#com').empty();
		   var com = JSON.stringify(data);
		   data = JSON.parse(com);
		  for (var i in data) {
			 $('#com').append(data[i]+"<br>");
		  }
		   
		}
	   });
	   return false;
	  }
	});
	 
});
</script>
</body>
</html>