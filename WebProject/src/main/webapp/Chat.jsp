<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.ArrayList" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello</title>
</head>
<body>
<form action="Chat" method ="post">
<input name ="text" required placeholder="You comment"> 
<input type="submit" class="send" value="Send">
</form>
<%
  String nul = (String)request.getAttribute("null");
 if(nul!=null){out.println(nul);}%>
 <c:set var ="dto" value="${requestScope.dto}"></c:set>
 <c:set var="list" value="${dto.comments}"></c:set>
 <c:forEach items="${list}" var="dt">
    <c:out value="dt"></c:out>
 </c:forEach>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src ="script.js"></script>
</body>
</html>