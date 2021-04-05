package com.interview.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Calender {
  public Calender(String loginTime, String logOffTime) {
		super();
		LoginTime = loginTime;
		LogOffTime = logOffTime;
		meetings = new ArrayList<BookedEvents>();
		schedule = new TreeMap<Date,Date>();
	}
String LoginTime;
  String LogOffTime ;
  List<BookedEvents> meetings;
  TreeMap<Date,Date> schedule;
  
public Date getLoginTimeDate(String date) {
	SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	Date date1=new Date();
	try {
		date1 = formatter.parse(date+" "+LoginTime);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return date1;
}
public void setLoginTime(String loginTime) {
	LoginTime = loginTime;
}
public Date getLogOffTimeDate(String date) {
	SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	Date date1=new Date();
	try {
		date1 = formatter.parse(date+" "+LogOffTime);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return date1;
	
}
public void setLogOffTime(String logOffTime) {
	LogOffTime = logOffTime;
}
public List<BookedEvents> getMeetings() {
	return meetings;
}
public void setMeetings(List<BookedEvents> meetings) {
	this.meetings = meetings;
}
public TreeMap<Date, Date> getSchedule() {
	return schedule;
}
public void setSchedule(TreeMap<Date, Date> schedule) {
	this.schedule = schedule;
}

public boolean isTimeConflicts(Date start,Date end) {
	  Date prev = schedule.floorKey(start),next = schedule.ceilingKey(start);
      if ((prev == null || start.compareTo(schedule.get(prev))>=0) &&(next == null || next.compareTo(end)>=0)) {
       
          return true;
      }
      return false;
	
}

public void addMeeting(String id,Date start,Date end,String role) {
	this.meetings.add(new BookedEvents(id,start,end,role));
	this.schedule.put(start, end);
}
public String getLoginTime() {
	return LoginTime;
}
public String getLogOffTime() {
	return LogOffTime;
}

}
