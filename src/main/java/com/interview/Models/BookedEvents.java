package com.interview.Models;

import java.util.Date;

public class BookedEvents  {
public BookedEvents(String meetingid, Date start_time, Date endTime, String role) {
		super();
		this.meetingid = meetingid;
		this.start_time = start_time;
		EndTime = endTime;
		Role = role;
	}
String meetingid;
Date start_time;
Date EndTime;
String Role;
public String getMeetingid() {
	return meetingid;
}
public void setMeetingid(String meetingid) {
	this.meetingid = meetingid;
}
public Date getStart_time() {
	return start_time;
}
public void setStart_time(Date start_time) {
	this.start_time = start_time;
}
public Date getEndTime() {
	return EndTime;
}
public void setEndTime(Date endTime) {
	EndTime = endTime;
}
public String getRole() {
	return Role;
}
public void setRole(String role) {
	Role = role;
}
}
