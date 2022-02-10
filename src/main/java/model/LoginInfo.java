package model;

import java.io.Serializable;

public class LoginInfo implements Serializable{

		private String number;
		private String pass;
		
		public LoginInfo() {}
		
		public LoginInfo(String number, String pass) {
			this.number = number;
			this.pass = pass;
		}

		public String getNumber() {
			return number;
		}

		public String getPass() {
			return pass;
		}
}
