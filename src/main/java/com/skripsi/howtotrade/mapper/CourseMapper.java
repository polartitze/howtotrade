package com.skripsi.howtotrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.skripsi.howtotrade.model.Activity;
import com.skripsi.howtotrade.model.Course;
import com.skripsi.howtotrade.model.Question;

@Mapper
public interface CourseMapper {
	
	@Select ("SELECT COUNT(*) FROM courseEnroll WHERE userid = #{userId} AND courseid = #{courseId}")
	int isExistCourseEnroll(int userId, int courseId);
	
	@Select("SELECT * FROM course")
	List<Course> getAllCourse();
	
	@Select("SELECT * FROM course WHERE courseid = #{courseId}")
	Course getCourseById(int courseId);
	
	@Select("SELECT a.*, at.name as activitytype FROM activity a "
			+ "JOIN activity_type at ON at.id = a.activitytypeid "
			+ "WHERE courseId = #{courseId}")
	List<Activity> getAllCourseActivity(int courseId);
	
	@Select("SELECT * FROM question where activityId = #{activityId}")
	Question getQuestion(int activityId);
	
	@Select("SELECT * FROM fn_save_course_enroll(#{userId}, #{courseId}")
	void saveCourseEnroll(int userId, int courseId);
}
