package com.interview.Models;


import java.util.*;

public class Meeting {

public Meeting(String meeting_id, String meeting_description, String meeting_locatin, String startTime, String endTime,
			String host_id,List<String> pct_ids) {
		super();
		this.meeting_id = meeting_id;
		this.meeting_description = meeting_description;
		this.meeting_locatin = meeting_locatin;
		this.startTime = startTime;
		this.endTime = endTime;
		this.host_id = host_id;
		this.participant_ids = new ArrayList<String>();
		this.participant_ids = pct_ids;
	}
String meeting_id;
String meeting_description;
String meeting_locatin;
String startTime;
String endTime;
String host_id;
List<String> participant_ids;
public String getMeeting_id() {
	return meeting_id;
}
public void setMeeting_id(String meeting_id) {
	this.meeting_id = meeting_id;
}
public String getMeeting_description() {
	return meeting_description;
}
public void setMeeting_description(String meeting_description) {
	this.meeting_description = meeting_description;
}
public String getMeeting_locatin() {
	return meeting_locatin;
}
public void setMeeting_locatin(String meeting_locatin) {
	this.meeting_locatin = meeting_locatin;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getHost_id() {
	return host_id;
}
public void setHost_id(String host_id) {
	this.host_id = host_id;
}
public List<String> getParticipant_ids() {
	return participant_ids;
}
public void setParticipant_ids(List<String> participant_ids) {
	this.participant_ids = participant_ids;
}

}
