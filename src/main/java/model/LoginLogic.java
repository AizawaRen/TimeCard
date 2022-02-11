package model;

import dao.DAO;

public class LoginLogic {
	public boolean execute(LoginInfo li) {
		DAO dao = new DAO();
		Employee emp = dao.findByLogin(li);
		if(emp != null) {
			return true;
			
		}else{
			return false;
		}
	}
}
