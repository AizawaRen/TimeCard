<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.Employee, java.util.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.ZonedDateTime" %>
<%@page import="java.time.ZoneId" %>
<%
	Employee emp = (Employee) session.getAttribute("employee");
	String start = (String) session.getAttribute("start");
	String finish = (String) session.getAttribute("finish");
	String startBreak = (String) session.getAttribute("startBreak");
	String finishBreak = (String) session.getAttribute("finishBreak");
	Double time = (Double) session.getAttribute("time") / 60;
	Double pay = (Double) session.getAttribute("pay");
	LocalDateTime now = LocalDateTime.now();
	ZonedDateTime zonedJapan = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/main.js"></script>
		<title>出退勤システム</title>
	</head>
	<body>
		<h2>出退勤システム</h2>		
		<p><%=zonedJapan.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))%></p>
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
		<%
			if(start != null && finish == null){
		%>
		<p><%= start %>に出勤しました</p>
		<%
			}else if(start != null && finish != null){
		%>
		<p><%= finish %>に退勤しました。</p>
		<p>今日も一日お疲れさまでした！</p>
		<%
			}
		%>
		
		<% 
			if(time != 0){ 
		%>
			<p>今月の実働時間（目安）：<%= String.format("%.1f", time) %>時間</p>
			<p>今月の給料（目安）：<%= String.format("%.0f", pay) %>円</p>
		<%
			}
		%>
		<div>
		<a href="/timecard-app/MonthlyDataServlet">今月の出退勤記録を見る</a>
		</div>
		
		<div>
		<a href="/timecard-app/LogoutServlet">ログアウト</a>
		</div>
	</body>
</html>