package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import model.Employee;
import model.LoginInfo;
import model.LoginLogic;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String number = request.getParameter("number");
		String pass = request.getParameter("pass");
		
		LoginInfo li = new LoginInfo(number, pass);
		LoginLogic logic = new LoginLogic();
		boolean isLogin = logic.execute(li);
		
		if(isLogin) {
			HttpSession session = request.getSession();
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = dao.findByLogin(li);
			session.setAttribute("employee", emp);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("/WEB-INF/jsp/loginResult.jsp");
		}
	}

}
