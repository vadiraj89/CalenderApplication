package com.interview.Calender;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.Exception.UserAlreadyPresentException;
import com.interview.Exception.UseridNotPresentException;
import com.interview.Models.Employee;
import com.interview.Models.FreeslotRequest;
import com.interview.Models.Meeting;
import com.interview.serviceLayer.CalenderService;
import com.sun.tools.javac.util.Assert;

@SpringBootTest
class CalenderApplicationTests {
	CalenderService calenderService;
	@BeforeEach
	public void setUp() {
		 calenderService = new CalenderService();
	}
	@Test
	void AddEmployeeTest() {
		try {
			calenderService.addEmployee("vadi", 23, "emp1", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			calenderService.addEmployee("vasu", 23, "emp2", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Employee> emp = calenderService.getAllEmployee();
	    Assert.check(emp.size()==2);
	}
	
	@Test
	void getEmployeeTest() {
		try {
			calenderService.addEmployee("vadi", 23, "emp1", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			calenderService.addEmployee("vasu", 23, "emp2", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Employee emp1 = calenderService.getCalenderdao().getEmployee("emp1");
		Employee emp2 = calenderService.getCalenderdao().getEmployee("emp2");
		Assert.check(emp1.getAge()==23);
		Assert.check(emp2.getAge()==23);
		Assert.check(emp1.getName()=="vadi");
		Assert.check(emp2.getName()=="vasu");
	}
	
	@Test
	void getConflictsTest() throws UseridNotPresentException {
		try {
			calenderService.addEmployee("vadi", 23, "emp1", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			calenderService.addEmployee("vasu", 23, "emp2", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> pct_ids = new ArrayList<String>();
		pct_ids.add("emp2");
		List<String> output = calenderService.getConflicts(new Meeting("txt","Meeting1","Room No 11","31-Jan-2021 11:00:00","31-Jan-2021 12:00:00","emp1",pct_ids));
		Assert.check(output.size()==1);
		Assert.check(output.get(0).equals("No Conflicts"));
		Meeting meet = calenderService.CreateMeeting(new Meeting("txt","Meeting1","Room No 11","31-Jan-2021 11:00:00","31-Jan-2021 12:00:00","emp1",pct_ids));
		List<String> outputaftercreate = calenderService.getConflicts(new Meeting("txt","Meeting1","Room No 11","31-Jan-2021 11:00:00","31-Jan-2021 12:00:00","emp1",pct_ids));
		Assert.check(outputaftercreate.size()==2);
		Assert.check(outputaftercreate.contains("emp1"));
		Assert.check(outputaftercreate.contains("emp2"));
	}
	
	@Test
	void bookMeetingTest() throws UseridNotPresentException {
		try {
			calenderService.addEmployee("vadi", 23, "emp1", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			calenderService.addEmployee("vasu", 23, "emp2", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> pct_ids = new ArrayList<String>();
		pct_ids.add("emp2");
		Meeting output = calenderService.CreateMeeting(new Meeting("txt","Meeting1","Room No 11","31-Jan-2021 11:00:00","31-Jan-2021 12:00:00","emp1",pct_ids));
		Assert.check(output.getMeeting_id().equals("Meeting1Room No 1131-Jan-2021 11:00:0031-Jan-2021 12:00:00"));
	    Assert.check(calenderService.getCalenderdao().getMeetings().size()==1);
	    Employee emp1 = calenderService.getCalenderdao().getEmployee("emp1");
	    Employee emp2 = calenderService.getCalenderdao().getEmployee("emp2");
	    Assert.check(emp1.getCal().getMeetings().get(0).getRole().equals("Host"));
	    Assert.check(emp2.getCal().getMeetings().get(0).getRole().equals("participant"));
	}
	
	@Test
	void getFreeSlotsTest() throws UseridNotPresentException {
		try {
			calenderService.addEmployee("vadi", 23, "emp1", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			calenderService.addEmployee("vasu", 23, "emp2", "10:00:00", "17:00:00");
		} catch (UserAlreadyPresentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Employee emp1 = calenderService.getCalenderdao().getEmployee("emp1");
	    Employee emp2 = calenderService.getCalenderdao().getEmployee("emp2");
	    List<String> pct_ids = new ArrayList<String>();
		pct_ids.add("emp2");
		List<String> ids = new ArrayList<String>();
		ids.add("emp2");
		ids.add("emp1");
		TreeMap<Date,Date> slots1 = calenderService.getEmployeesandFreeSlots(new FreeslotRequest(ids,60,"31-Jan-2021"));
		Assert.check(slots1.size()==7);
		Meeting output = calenderService.CreateMeeting(new Meeting("txt","Meeting1","Room No 11","31-Jan-2021 11:00:00","31-Jan-2021 12:00:00","emp1",pct_ids));
		TreeMap<Date,Date> slots2 = calenderService.getEmployeesandFreeSlots(new FreeslotRequest(ids,60,"31-Jan-2021"));
		Assert.check(slots2.size()==6);
	}

}
