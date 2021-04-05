package com.interview.Calender;

import java.util.*;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.interview.Exception.UserAlreadyPresentException;
import com.interview.Exception.UseridNotPresentException;
import com.interview.Models.Employee;
import com.interview.Models.FreeslotRequest;
import com.interview.Models.Meeting;
import com.interview.serviceLayer.CalenderService;


@SpringBootApplication
@RestController
public class CalenderApplication {
    CalenderService calenderService = new CalenderService();
	public static void main(String[] args) {
		SpringApplication.run(CalenderApplication.class, args);
	}
  

	
	
 @GetMapping("/allEmployee")
  public List<Employee> getAllEmployee() {
	return 	calenderService.getAllEmployee();
   }
 
@PostMapping(value = "/createEmployee", consumes = "application/json", produces = "application/json")
	public Employee createPerson(@RequestBody Employee employee) throws UserAlreadyPresentException {
	  return calenderService.addEmployee(employee.getName(), employee.getAge(), employee.getEmpid(), employee.getLogintime(), employee.getLogofftime());
	}
	
	@PostMapping(value = "/checkconflicts", consumes = "application/json", produces = "application/json")
	public List<String> checkConflicts(@RequestBody Meeting meeting) throws UseridNotPresentException {
	    return calenderService.getConflicts(meeting);
	}
	
	@PostMapping(value = "/bookMeeting", consumes = "application/json", produces = "application/json")
	public Meeting bookMeeting(@RequestBody Meeting meeting) throws UseridNotPresentException {
	    return calenderService.CreateMeeting(meeting);
	}
	
	@PostMapping(value = "/getfreeslots", consumes = "application/json", produces = "application/json")
	public TreeMap<Date,Date> getFreeSlots(@RequestBody FreeslotRequest request) throws UseridNotPresentException {
	    return calenderService.getEmployeesandFreeSlots(request);
	}
	
	
}
