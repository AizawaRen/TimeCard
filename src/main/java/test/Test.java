package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/test/test"})
public class Test extends HttpServlet{

	public void doPost(
			HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException {

		String	radio = req.getParameter("radio");

		req.setAttribute("eng",radio);
		req.getRequestDispatcher("test.jsp").forward(req, res);


	}
}