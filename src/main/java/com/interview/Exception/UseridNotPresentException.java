package com.interview.Exception;

public class UseridNotPresentException extends Exception{
  public UseridNotPresentException(String empi) {
		super();
		this.empid = empi;
	}

String empid;

@Override
public String toString() {
	return "UseridNotPresentException [empid=" + empid + "]";
}
}
