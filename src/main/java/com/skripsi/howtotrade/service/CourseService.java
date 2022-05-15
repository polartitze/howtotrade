package com.skripsi.howtotrade.service;

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
	private CourseMapper courseMapper;
	
	public List<Course> getAllCourse(int userId){
		List<Course> courseList = courseMapper.getAllCourse();
		for (Course course : courseList) {
			course.setEnroll(isCourseEnroll(userId, course.getCourseId()));
			course.setLock(courseMapper.getCourseLock(userId, course.getCourseId()));
		}
		return courseList;
	}
	
	public List<Course> getAllCourse(){
		List<Course> courseList = courseMapper.getAllCourse();
		for (Course course : courseList) {
			course.setEnroll(false);
			course.setLock(false);
		}
		return courseList;
	}
	
	public Course getCourseById(int courseId) {
		Course course = courseMapper.getCourseById(courseId);
		course.setActivityList(getCourseActivity(courseId));
		
		return course;
	}
	
	public List<Activity> getCourseActivity(int courseId) {
		List<Activity> activityList = courseMapper.getAllCourseActivity(courseId);
		for (Activity activity : activityList) {
			if(activity.getIsQuestion()) {
				activity.setQuestion(courseMapper.getQuestion(activity.getActivityId()));
			}
			if(activity.getActivityType().equals("chart")) {
				activity.setChart(getChart(activity.getActivityId()));				
			}
		}
		return activityList;
	}
	
	
	private boolean isCourseEnroll(int userId, int courseId) {
		try {
			if(courseMapper.isExistCourseEnroll(userId, courseId) > 0) {
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
		Chart chart = courseMapper.getChartByActivityId(activityId);
		chart.setCandleList(courseMapper.getAllCandleByChartId(chart.getChartId()));
		
		return chart;
	}
	
	public List<Chart> getChartMaster() {
		List<Chart> chartList = courseMapper.getChartMaster();
		for (Chart chart : chartList) {
			chart.setCandleList(courseMapper.getAllCandleByChartId(chart.getChartId()));
		}
		return chartList;
	}
	
	public boolean saveProgress(int userId, int courseId) {
		try {
			courseMapper.saveCourseEnroll(userId, courseId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public List<Map<String,String>> getAllCourseName(){
        return courseMapper.getAllCourseName();
    } 

	public void addCourse(String coursename, String coursedesc, String imageurl){
        courseMapper.addCourse(coursename, coursedesc, imageurl);
    } 

	public int getLatestCourseId(){
		return courseMapper.getLatestCourseId();
	}
	
	public int getLatestActivityId(){
		return courseMapper.getLatestActivityId();
	}

	public int getLatestStepNo(int courseId){
		Integer latestStepNo = courseMapper.getLatestStepNo(courseId);
		int result;
		if (latestStepNo == null) {
			result = 0;
		}
		else result = latestStepNo;
		return result;
	}

	public int getLatestCourseOrder(int courseId){
		Integer currentOrder = courseMapper.getCourseOrder(courseId);
		
		if (currentOrder != null) {
			return currentOrder;
		}
		
		Integer latestOrder = courseMapper.getLatestCourseOrder();
		
		if (latestOrder == null) {
			return 0;
		}
		
		return latestOrder;
	}

	public List<Activity> getAllCourseActivity(int courseId){
		return courseMapper.getAllCourseActivity(courseId);
	}
	
	public void deleteActivity(int activityId){
		courseMapper.deleteActivity(activityId);
	}

	public void saved(int courseId, int courseOrder){
		courseMapper.saved(courseId, courseOrder);
	}
	
	public int addActivity(int courseId, int stepNo, int activityTypeId, String activityDesc, String imageUrl, boolean isQuestion){
		return courseMapper.addActivity(courseId, stepNo, activityTypeId, activityDesc, imageUrl, isQuestion);
	}

	public List<Map<String,String>> getAllActivityType(){
		return courseMapper.getAllActivityType();
	}

	public int countListActivity(int courseId){
		return courseMapper.countListActivity(courseId);
	}

	public void addActivityQuestion(int activityId, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour){
		courseMapper.addActivityQuestion(activityId, stepno, questiondesc, correctanswer, choiceone, choicetwo, choicethree, choicefour);
	}
	
	public int saveActivityChart(int activityId, int chartMasterId, String startDate, String endDate) {
		return courseMapper.saveActivityChart(activityId, chartMasterId, startDate, endDate);
	}
	
	public void deleteCourseById(int courseId){
		//get course order that will be delete
		Course course = courseMapper.getCourseById(courseId);
		//update all course with course order > courseWhoWillBeDeleted
		courseMapper.updateOrderBeforeDelete(course.getCourseOrder());
		// the course
		courseMapper.deleteCourseById(courseId);
	}
	
	public void changeOrder(int courseOrder, int courseId){
		courseMapper.changeOrder(courseOrder, courseId);
	}
}
