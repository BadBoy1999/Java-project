<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action= Chat method ="get">  
<textarea name ="text"></textarea> 
<input type ="submit" value ="Send"> 
</form>
<%@page import = "ru.Chat.DTO" %>
<%@ page import = "java.util.*" %>
<% DTO dto = (DTO)request.getAttribute("dto"); %>
<%ArrayList<String> com = dto.show(); %>
<% for(String c:com){
	out.println(c);
}
%>
</body>
</html>