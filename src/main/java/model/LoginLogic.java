package model;

import dao.EmployeeDAO;

public class LoginLogic {
	public boolean execute(LoginInfo li) {
		EmployeeDAO dao = new EmployeeDAO();
		Employee emp = dao.findByLogin(li);
		if(emp != null) {
			return true;
			
		}else{
			return false;
		}
	}
}
