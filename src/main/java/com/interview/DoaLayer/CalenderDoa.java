package com.interview.DoaLayer;

import java.util.ArrayList;

import java.util.List;

import com.interview.Models.Employee;
import com.interview.Models.Meeting;

public class CalenderDoa {
public CalenderDoa() {
		this.employee = new ArrayList<Employee>();
		this.meetings = new ArrayList<Meeting>();
	}

List<Employee> employee;
List<Meeting> meetings;

public Employee addEmployee(String name,int age ,String empid,String lgntm,String logofftm) {
	Employee emp = new Employee(name,age,empid,lgntm,logofftm);
	employee.add(emp);
	
	return emp;
}

public Employee getEmployee(String empid) {
	for(Employee emp:employee) {
		
		if(emp.getEmpid().equals(empid)) {
			return emp;
		}
	}
	return null;
}

public Meeting addMeeting(Meeting meetingrequest) {
	this.meetings.add(meetingrequest);
	return meetingrequest;
}

public List<Employee> getEmployee() {
	return employee;
}

public void setEmployee(List<Employee> employee) {
	this.employee = employee;
}

public List<Meeting> getMeetings() {
	return meetings;
}

public void setMeetings(List<Meeting> meetings) {
	this.meetings = meetings;
}


}
