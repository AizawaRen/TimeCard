<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Employee, java.util.*"%>
<%
	Employee emp = (Employee) session.getAttribute("employee");
	String start = (String) session.getAttribute("start");
	String finish = (String) session.getAttribute("finish");
	String startBreak = (String) session.getAttribute("startBreak");
	String finishBreak = (String) session.getAttribute("finishBreak");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>出退勤システム</h1>
<%
	if (start == null) {
%>
<h2><%=emp.getName() %>さんおはようございます！</h2>
<form action="/timecard-app/AttendanceServlet" method="post">
	<input type="hidden" name="param" value="start">
	<input type="submit" value="出勤">
</form>

<%
	}else{
%>
<form action="/timecard-app/AttendanceServlet" method="post">
	<input type="hidden" name="param" value="start">
	<input type="submit" value="出勤" disabled>
</form>

<%
	}
%>

<%
	if (finish != null || start == null || start != null && startBreak != null && finishBreak == null){
%>
<form action="/timecard-app/AttendanceServlet" method="post">
	<input type="hidden" name="param" value="finish">
	<input type="submit" value="退勤" disabled>
</form>

<%
	}else{
%>
<form action="/timecard-app/AttendanceServlet" method="post">
	<input type="hidden" name="param" value="finish">
	<input type="submit" value="退勤">
</form>
<%
	}
%>
<div>
<a href="/timecard-app/LogoutServlet">ログアウト</a>
</div>
</body>
</html>