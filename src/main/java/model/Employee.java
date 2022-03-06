package model;
import java.io.Serializable;

public class Employee implements Serializable{
	private String number;
	private String pass;
	private String name;
	private int yph;
	
	public Employee() {};
	
	public Employee(String number, String pass, String name, int yph) {
		this.number = number;
		this.pass = pass;
		this.name = name;
		this.yph = yph;
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
	public int getYph() {
		return yph;
	}
	
	
}
