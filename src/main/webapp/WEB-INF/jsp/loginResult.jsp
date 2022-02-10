<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Employee, java.util.*"%>
<%
	Employee emp = (Employee) session.getAttribute("employee");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>出退勤システム</h1>
<p>こんにちは</p>
<input type="button" value="出勤">
<input type="button" value="退勤">
</body>
</html>