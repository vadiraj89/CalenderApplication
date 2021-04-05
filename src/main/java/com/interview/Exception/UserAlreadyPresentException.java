package com.interview.Exception;

public class UserAlreadyPresentException extends Exception{
	
	public UserAlreadyPresentException(String empid) {
		super();
		this.empid = empid;
	}

	String empid;

	@Override
	public String toString() {
		return "UserAlreadyPresentException [empid=" + empid + "]";
	}
	

}
