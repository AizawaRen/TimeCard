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
	Double time = (Double) session.getAttribute("time");
	Double pay = (Double) session.getAttribute("pay");
	ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/main.js"></script>
		<title>出退勤システム</title>
	</head>
	<body class="at">
		<div class="left">
			<div class="box">
				<div class="box-title">Employee's infomation</div>
				<p>従業員名：<%= emp.getName() %></p>
				<p>従業員番号：<%= emp.getNumber() %></p>
				<p>時給：<%= emp.getYph() %>円</p>
			</div>
			<div class="box">
				<div class="box-title">Today's log</div>
				
				<%
					if(start != null){
				%>
					<p><%= start %>に出勤しました</p>
				<%
					}
					if(startBreak != null){
				%>
					<p><%= startBreak %>に休憩開始しました</p>
				<%
					}
					if( finishBreak != null){
				%>
					<p><%= finishBreak %>に休憩終了しました</p>
				
				<%
					}
					if( finish != null){
				%>
					<p><%= finish %>に退勤しました</p>
				<%
					}
				%>
			</div>
		</div>
		<div class="main">
			<h1>Attendance</h1>		
			<p class="date"><%=now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))%></p>
			<p id="RealtimeClockArea"></p>
			<%
				if (start == null) {
			%>
				<form action="/timecard-app/AttendanceServlet" method="post">
					<input type="hidden" name="param" value="start">
					<input type="submit" value="出勤"  class="start">
				</form>
			
			<%
				}else{
			%>	
				<form action="/timecard-app/AttendanceServlet" method="post">
					<input type="hidden" name="param" value="start">
					<input type="submit" value="出勤" disabled class="disabled">
				</form>
			
			<%
				}
			%>
			<%

				if (startBreak == null && finish == null && start != null) {
			%>
		
			<form action="/timecard-app/AttendanceServlet" method="post">
				<input type="hidden" name="param" value="startBreak">
				<input type="submit" name="submit" value="休憩開始"class="startBreak">
			</form>
		
			<%
				} else {
			%>
		
			<form>
				<input type="submit" name="submit" value="休憩開始" disabled class="disabled">
			</form>
		
			<%
				}
					if (finishBreak == null && finish == null && startBreak != null) {
			%>
		
			<form action="/timecard-app/AttendanceServlet" method="post">
				<input type="hidden" name="param" value="finishBreak">
				<input	type="submit" name="submit" value="休憩終了" class="finishBreak">
			</form>
		
			<%
				} else {
			%>
		
			<form>
				<input	type="submit" name="submit" value="休憩終了" disabled class="disabled">
			</form>
		
			<%
				}
			%>
			<%
				if (finish != null || start == null || start != null && startBreak != null && finishBreak == null){
			%>
				<form action="/timecard-app/AttendanceServlet" method="post">
					<input type="hidden" name="param" value="finish">
					<input type="submit" value="退勤" disabled class="disabled">
				</form>
			
			<%
				}else{
			%>
				<form action="/timecard-app/AttendanceServlet" method="post">
					<input type="hidden" name="param" value="finish">
					<input type="submit" value="退勤" class="finish">
				</form>
			<%
				}
			%>
		
			
			
			<div class="out">
				<a href="/timecard-app/LogoutServlet">ログアウト</a>
			</div>
		</div>
		
		<div class="right">
			<div class="box month">
				<div class="box-title">Monthly report</div>
				<p>実働：<%= String.format("%.0f", time) %>分</p>
				<p>給与：<span><%= String.format("%.0f", pay) %></span>円</p><br>
				<a href="/timecard-app/MonthlyDataServlet">出退勤記録を見る</a>
			</div>
			<div class="box break">
				<div class="box-title">休憩時間について</div>
				<p>実働6時間以上：45分</p>
				<p>実働8時間以上：60分<p>
			</div>
		</div>
	</body>
</html>