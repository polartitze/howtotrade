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
	CourseMapper mapper;
	
	public List<Course> getAllCourse(int userId){
		List<Course> courseList = mapper.getAllCourse();
		for (Course course : courseList) {
			course.setEnroll(isCourseEnroll(userId, course.getCourseId()));
			course.setLock(mapper.getCourseLock(userId, course.getCourseId()));
		}
		return courseList;
	}
	
	public List<Course> getAllCourse(){
		List<Course> courseList = mapper.getAllCourse();
		for (Course course : courseList) {
			course.setEnroll(false);
			course.setLock(false);
		}
		return courseList;
	}
	
	public Course getCourseById(int courseId) {
		Course course = mapper.getCourseById(courseId);
		course.setActivityList(getCourseActivity(courseId));
		
		return course;
	}
	
	public List<Activity> getCourseActivity(int courseId) {
		List<Activity> activityList = mapper.getAllCourseActivity(courseId);
		for (Activity activity : activityList) {
			if(activity.getIsQuestion()) {
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
	
	public List<Chart> getChartMaster() {
		List<Chart> chartList = mapper.getChartMaster();
		for (Chart chart : chartList) {
			chart.setCandleList(mapper.getAllCandleByChartId(chart.getChartId()));
		}
		return chartList;
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

	public int getLatestCourseOrder(){
		Integer latestOrder = mapper.getLatestCourseOrder();
		int result;
		if (latestOrder == null) {
			result = 0;
		}
		else result = latestOrder;
		return result;
	}

	public List<Activity> getAllCourseActivity(int courseId){
		return mapper.getAllCourseActivity(courseId);
	}
	
	// public List<HashMap<String,String>> getAllCourseActivityMap(int courseId){
	// 	return mapper.getAllCourseActivityMap(courseId);
	// }
	public void deleteActivity(int activityId){
		mapper.deleteActivity(activityId);
	}

	public void saved(int courseId, int courseOrder){
		mapper.saved(courseId, courseOrder);
	}
	
	public int addActivity(int courseId, int stepNo, int activityTypeId, String activityDesc, String imageUrl, boolean isQuestion){
		return mapper.addActivity(courseId, stepNo, activityTypeId, activityDesc, imageUrl, isQuestion);
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
	
	public int saveActivityChart(int activityId, int chartMasterId, String startDate, String endDate) {
		return mapper.saveActivityChart(activityId, chartMasterId, startDate, endDate);
	}
	
	public void deleteCourseById(int courseId){
		//get course order that will be delete
		Course course = mapper.getCourseById(courseId);
		//update all course with course order > courseWhoWillBeDeleted
		mapper.updateOrderBeforeDelete(course.getCourseOrder());
		// the course
		mapper.deleteCourseById(courseId);
	}
	
	public void changeOrder(int courseOrder, int courseId){
		mapper.changeOrder(courseOrder, courseId);
	}
}
