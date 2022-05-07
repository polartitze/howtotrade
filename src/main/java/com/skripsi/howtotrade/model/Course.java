package com.skripsi.howtotrade.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Course {
	private int courseId;
	private String courseName;
	private String courseDesc;
	private Time courseDuration;
	private Timestamp enrollDate;
	private List<Activity> activityList;
	private boolean isEnroll;
	private boolean isLock;
	private String imageUrl;
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public Time getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(Time courseDuration) {
		this.courseDuration = courseDuration;
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
	public boolean isEnroll() {
		return isEnroll;
	}
	public void setEnroll(boolean isEnroll) {
		this.isEnroll = isEnroll;
	}
	public boolean isLock() {
		return isLock;
	}
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
