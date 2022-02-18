package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttendanceDataDAO;
import model.AttendanceData;
import model.Employee;


public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		Employee emp = (Employee) session.getAttribute("employee");
		String enumber = emp.getNumber();
		String param = request.getParameter("param");
		AttendanceDataDAO add = new AttendanceDataDAO();
		AttendanceData ad = new AttendanceData();
		
		boolean success = false;
		
		if (param.equals("start")) {
			success = add.setStart(enumber);
		} else if (param.equals("finish")) {
			success = add.setFinish(enumber);
		} else if (param.equals("startBreak")) {
			success = add.setStartBreak(enumber);
		} else if (param.equals("finishBreak")) {
			success = add.setFinishBreak(enumber);
			
		}
		
		if(success) {
			session.setAttribute(param, param);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendanceMain.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendanceError.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	

}
