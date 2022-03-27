package com.skripsi.howtotrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.skripsi.howtotrade.model.Activity;
import com.skripsi.howtotrade.model.Course;
import com.skripsi.howtotrade.model.Question;

@Mapper
public interface CourseMapper {
	
	@Select("SELECT * FROM course")
	List<Course> getAllCourse();
	
	@Select("SELECT * FROM course WHERE courseid = #{courseId}")
	Course getCourseById(int courseId);
	
	@Select("SELECT * FROM activity where courseId = #{courseId}")
	List<Activity> getAllCourseActivity(int courseId);
	
	@Select("SELECT * FROM question where activityId = #{activityId}")
	Question getQuestion(int activityId);
	
	@Select("SELECT * FROM f_save_course_enroll(#{userId}, #{courseId}")
	void saveCourseEnroll(int userId, int courseId);
}
