package com.skripsi.howtotrade.service;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			course.setLock(mapper.getCourseLock(userId, course.getCourseId()));
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
	
	public List<Map<String,String>> getAllCourseName(){
        return mapper.getAllCourseName();
    } 

	public void addCourse(String coursename, String coursedesc, String imageurl){
        mapper.addCourse(coursename, coursedesc, imageurl);
    } 

	public int getLatestCourseId(){
		return mapper.getLatestCourseId();
	}
	
	public int getLatestActivityId(){
		return mapper.getLatestActivityId();
	}

	public int getLatestStepNo(int courseId){
		Integer latestStepNo = mapper.getLatestStepNo(courseId);
		int result;
		if (latestStepNo == null) {
			result = 0;
		}
		else result = latestStepNo;
		return result;
	}

	public List<Activity> getAllCourseActivity(int courseId){
		return mapper.getAllCourseActivity(courseId);
	}
	
	public List<HashMap<String,String>> getAllCourseActivityMap(int courseId){
		return mapper.getAllCourseActivityMap(courseId);
	}
	public void deleteActivity(int activityId){
		mapper.deleteActivity(activityId);
	}

	public void saved(int courseId){
		mapper.saved(courseId);
	}
	
	public void addActivity(int courseId, int stepNo, int activityTypeId, String activityDesc, String imageUrl, boolean isQuestion){
		mapper.addActivity(courseId, stepNo, activityTypeId, activityDesc, imageUrl, isQuestion);;
	}

	public List<Map<String,String>> getAllActivityType(){
		return mapper.getAllActivityType();
	}

	public int countListActivity(int courseId){
		return mapper.countListActivity(courseId);
	}

	public void addActivityQuestion(int activityId, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour){
		mapper.addActivityQuestion(activityId, stepno, questiondesc, correctanswer, choiceone, choicetwo, choicethree, choicefour);
	}
}
