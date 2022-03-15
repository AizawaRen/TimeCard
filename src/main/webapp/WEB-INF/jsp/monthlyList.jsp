<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="model.AttendanceData"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Employee"%>    

<%
	Employee emp = (Employee) session.getAttribute("employee");
	
	if(emp == null) {
		response.sendRedirect("/timecard-app/LogoutServlet");
	}
	
	String enumber = emp.getNumber();
	String name = emp.getName();
	Calendar calendar = Calendar.getInstance();
	List<AttendanceData> ThisMonthList =(List<AttendanceData>) session.getAttribute("thisMonth");
		
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/monthly.css">
<title>Monthly</title>
</head>
<body>


		<div>
		<table>
		<tr>
		<th>日にち</th>
		<th>出勤</th>
		<th>退勤</th>
		<th>休憩入り</th>
		<th>休憩戻り</th>
		<th>休憩時間</th>
		<th>実働時間</th>
		</tr>

		<%	
			
			int dom = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
			
			for(int i = 1; i <= dom; i++) {
				Boolean chk = false;
		%>
				<tr>
				<td><%= (calendar.get(Calendar.MONTH) + 1) + "/" + i  %></td>

			<%
				for(AttendanceData atd: ThisMonthList) {
					String workDate = atd.getWorkDate().format(DateTimeFormatter.ofPattern("dd"));
					if(Integer.parseInt(workDate) == i) {
			%>
						<td><% if(atd.getStartTime() != null) { %>
							<%= atd.getStartTime().format(tFormat) %><% } %>
						</td>
						<td><% if(atd.getFinishTime() != null) { %>
							<%= atd.getFinishTime().format(tFormat) %><% } %>
						</td>
						<td><% if(atd.getStartBreakTime() != null) { %>
							<%= atd.getStartBreakTime().format(tFormat) %><% } %>
						</td>
						<td><% if(atd.getFinishBreakTime() != null) { %>
							<%= atd.getFinishBreakTime().format(tFormat) %><% } %>
						</td>
						<td><% if(atd.getBreakTime() != null) {%>
							<%= tFormat.format(LocalTime.MIDNIGHT.plus(atd.getBreakTime())) %>
							<% } %>
						</td>
						<td><% if(atd.getWorkingHours() != null) { %>
							<%= tFormat.format(LocalTime.MIDNIGHT.plus(atd.getWorkingHours())) %>
							<% } %>
						</td>
			<%
						chk = true;
						break;
					}
				}
				if(!chk) {
			%>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					

		<%
				}
			}
		%>
		
				</tr>

		</table>
		</div>

		<div class="out">
			<a href ="/timecard-app/CheckAttServlet">出退勤画面に戻る</a>
		</div>

</body>
</html>