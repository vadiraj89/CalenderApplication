package com.interview.Models;

import java.util.List;

public class FreeslotRequest {

public FreeslotRequest(List<String> empids, int duration, String date) {
		super();
		this.empids = empids;
		this.duration = duration;
		this.date = date;
	}
List<String> empids;
int duration;
String date;
public List<String> getEmpids() {
	return empids;
}
public void setEmpids(List<String> empids) {
	this.empids = empids;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}
