package aec2.metro.implementacion.undergroundapp;

import java.io.Serializable;

public class Employee implements Serializable {

	private String name;
	private String password;
	
	Employee(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	String getPassword() {
		return password;
	}
	
	String getName() {
		return name;
	}
}
