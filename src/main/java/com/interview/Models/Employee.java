package com.interview.Models;

public class Employee {
	
	public Employee(String name, int age, String empid,String logintime,String logofftime) {
		super();
		this.name = name;
		this.age = age;
		this.empid = empid;
		this.setLogintime(logintime);
		this.setLogofftime(logofftime);
		  

		this.cal = new Calender(logintime,logofftime);
		
	}
	String name;
    int age;
    String empid;
    Calender cal;
    private String logintime;
    private String logofftime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public Calender getCal() {
		return cal;
	}
	public void setCal(Calender cal) {
		this.cal = cal;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLogofftime() {
		return logofftime;
	}
	public void setLogofftime(String logofftime) {
		this.logofftime = logofftime;
	}

}
