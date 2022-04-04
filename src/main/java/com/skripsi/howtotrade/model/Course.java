package com.skripsi.howtotrade.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Course {
	int courseId;
	String coursename;
	String coursedesc;
	Time courseduration;
	Timestamp enrollDate;
	List<Activity> activityList;
	boolean isenroll;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCoursedesc() {
		return coursedesc;
	}
	public void setCoursedesc(String coursedesc) {
		this.coursedesc = coursedesc;
	}
	public Time getCourseduration() {
		return courseduration;
	}
	public void setCourseduration(Time courseduration) {
		this.courseduration = courseduration;
	}
	public Timestamp getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}
	public List<Activity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	public boolean isIsenroll() {
		return isenroll;
	}
	public void setIsenroll(boolean isenroll) {
		this.isenroll = isenroll;
	}
}
