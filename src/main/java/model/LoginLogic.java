package model;

import dao.LoginDAO;

public class LoginLogic {
	public boolean execute(LoginInfo li) {
		LoginDAO dao = new LoginDAO();
		Employee emp = dao.findByLogin(li);
		if(emp != null) {
			return true;
			
		}else{
			return false;
		}
	}
}
