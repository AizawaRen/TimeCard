<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Employee, java.util.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%
	Employee emp = (Employee) session.getAttribute("employee");
	String start = (String) session.getAttribute("start");
	String finish = (String) session.getAttribute("finish");
	String startBreak = (String) session.getAttribute("startBreak");
	String finishBreak = (String) session.getAttribute("finishBreak");
	LocalDateTime now = LocalDateTime.now();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/main.js"></script>
<title>出退勤システム</title>
</head>
<body>
<h1>出退勤システム</h1>
<p><%=now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))%></p>
<p id="RealtimeClockArea"></p>
<%
	if (start == null) {
%>
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
<a href="/timecard-app/MonthlyServlet">今月の出退勤記録</a>
</div>

<div>
<a href="/timecard-app/LogoutServlet">ログアウト</a>
</div>
</body>
</html>