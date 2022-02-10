package model;

public class Employee {
	private String number;
	private String pass;
	private String name;
	
	public Employee(String number, String pass, String name) {
		this.number = number;
		this.pass = pass;
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}
	
	
}
