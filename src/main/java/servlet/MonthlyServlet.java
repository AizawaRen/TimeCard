package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttendanceDataDAO;
import model.AttendanceData;
import model.Employee;

/**
 * Servlet implementation class MonthlyServlet
 */
public class MonthlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		Employee emp = (Employee) session.getAttribute("employee");
		
		if(emp == null) {
			response.sendRedirect("/timecard-app/LogoutServlet");
		}else {
			
		
			String enumber = emp.getNumber();
			AttendanceDataDAO add = new AttendanceDataDAO();
			List<AttendanceData> ThisMonthList = add.MonthlyList(enumber);
			
			
			session.setAttribute("thisMonth", ThisMonthList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/monthlyList.jsp");
			dispatcher.forward(request, response);
		
		}
		
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		Employee emp = (Employee) session.getAttribute("employee");
		
		if(emp == null) {
			response.sendRedirect("/timecard-app/LogoutServlet");
		}else {
		
			String enumber = emp.getNumber();
			AttendanceDataDAO add = new AttendanceDataDAO();
			List<AttendanceData> ThisMonthList = add.MonthlyList(enumber);
			
			
			session.setAttribute("thisMonth", ThisMonthList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/monthlyList.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	

}
