package com.skripsi.howtotrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skripsi.howtotrade.mapper.CourseMapper;
import com.skripsi.howtotrade.model.Activity;
import com.skripsi.howtotrade.model.Chart;
import com.skripsi.howtotrade.model.Course;

@Service
public class CourseService {

	@Autowired
	CourseMapper mapper;
	
	public List<Course> getAllCourse(int userId){
		List<Course> courseList = mapper.getAllCourse();
		for (Course course : courseList) {
			course.setEnroll(isCourseEnroll(userId, course.getCourseId()));
		}
		return courseList;
	}
	
	public Course getCourseById(int courseId) {
		Course course = mapper.getCourseById(courseId);
		course.setActivityList(getCourseActivity(courseId));
		
		return course;
	}
	
	private List<Activity> getCourseActivity(int courseId) {
		List<Activity> activityList = mapper.getAllCourseActivity(courseId);
		for (Activity activity : activityList) {
			if(activity.isIsquestion()) {
				activity.setQuestion(mapper.getQuestion(activity.getActivityId()));
			}
			if(activity.getActivityType().equals("chart")) {
				activity.setChart(getChart(activity.getActivityId()));				
			}
		}
		return activityList;
	}
	
	
	private boolean isCourseEnroll(int userId, int courseId) {
		try {
			if(mapper.isExistCourseEnroll(userId, courseId) > 0) {
				return true;
			} else {
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private Chart getChart(int activityId) {
		Chart chart = mapper.getChartByActivityId(activityId);
		chart.setCandleList(mapper.getAllCandleByChartId(chart.getChartId()));
		
		return chart;
	}
	
	public boolean saveProgress(int userId, int courseId) {
		try {
			mapper.saveCourseEnroll(userId, courseId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
