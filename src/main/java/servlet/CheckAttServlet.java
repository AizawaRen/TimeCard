package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CheckAttDAO;
import model.Employee;

public class CheckAttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		if(emp == null) {
			response.sendRedirect("/timecard-app/LogoutServlet");
		}
		
		String enumber = emp.getNumber();
		int yph = emp.getYph();
		CheckAttDAO cad = new CheckAttDAO();
			Double totalTime = cad.checkTotalTime(enumber);
			session.setAttribute("time", totalTime);
			Double totalPay = totalTime * yph / 60;
			session.setAttribute("pay", totalPay);
			String ckStart = cad.checkStart(enumber);
			session.setAttribute("start", ckStart);

			if(ckStart != null) {
				String ckFinish = cad.checkFinish(enumber);
				session.setAttribute("finish", ckFinish);

				if (ckFinish == null) {
					String ckStartBreak = cad.checkStartBreak(enumber);
					session.setAttribute("startBreak", ckStartBreak);

					if (ckStartBreak != null) {
						String ckFinishBreak = cad.checkFinishBreak(enumber);
						session.setAttribute("finishBreak", ckFinishBreak);

					}
				}
			}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendanceMain.jsp");
		dispatcher.forward(request, response);
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		String enumber = emp.getNumber();
		int yph = emp.getYph();
		CheckAttDAO cad = new CheckAttDAO();
			Double totalTime = cad.checkTotalTime(enumber);
			session.setAttribute("time", totalTime);
			Double totalPay = totalTime * yph / 60;
			session.setAttribute("pay", totalPay);
			String ckStart = cad.checkStart(enumber);
			session.setAttribute("start", ckStart);

			if(ckStart != null) {
				String ckFinish = cad.checkFinish(enumber);
				session.setAttribute("finish", ckFinish);

				if (ckFinish == null) {
					String ckStartBreak = cad.checkStartBreak(enumber);
					session.setAttribute("startBreak", ckStartBreak);

					if (ckStartBreak != null) {
						String ckFinishBreak = cad.checkFinishBreak(enumber);
						session.setAttribute("finishBreak", ckFinishBreak);

					}
				}
			}

		

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/attendanceMain.jsp");
			dispatcher.forward(request, response);
	}

}
