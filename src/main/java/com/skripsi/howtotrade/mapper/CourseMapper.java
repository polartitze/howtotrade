package com.skripsi.howtotrade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skripsi.howtotrade.model.Activity;
import com.skripsi.howtotrade.model.Candle;
import com.skripsi.howtotrade.model.Chart;
import com.skripsi.howtotrade.model.Course;
import com.skripsi.howtotrade.model.Question;

@Mapper
public interface CourseMapper {
	
	@Select ("SELECT COUNT(*) FROM course_enroll WHERE userid = #{userId} AND courseid = #{courseId}")
	int isExistCourseEnroll(int userId, int courseId);
	
	@Select("SELECT * FROM course WHERE ISSAVED = '1' ORDER BY courseOrder ")
	List<Course> getAllCourse();
	
	@Select("SELECT * FROM course WHERE courseid = #{courseId}")
	Course getCourseById(int courseId);
	
	@Select("SELECT a.*, at.name as activitytype FROM activity a "
			+ "INNER JOIN activity_type at ON at.id = a.activitytypeid "
			+ "WHERE courseId = #{courseId} "
			+ "ORDER BY a.stepNo asc")
	List<Activity> getAllCourseActivity(int courseId);

	@Select("SELECT * FROM question where activityId = #{activityId}")
	Question getQuestion(int activityId);
	
	@Select("SELECT * FROM fn_save_course_enroll(#{userId}, #{courseId})")
	String saveCourseEnroll(int userId, int courseId);
	
	@Select("SELECT * from chart where activityId = #{activityId}")
	Chart getChartByActivityId(int activityId);
	
	@Select("SELECT * from chart where activityid = 0 order by chartid")
	List<Chart> getChartMaster();
	
	@Select("SELECT * FROM candle where chartId = #{chartId}")
	List<Candle> getAllCandleByChartId(int chartId);
	
	@Select("SELECT * FROM fn_islocked_course(#{userId}, #{courseId})")
	boolean getCourseLock(int userId, int courseId);

	@Select("SELECT COURSEID, COURSENAME FROM COURSE "
			+ "WHERE COURSEID NOT IN (SELECT DISTINCT COURSEID FROM QUIZ) AND ISSAVED = '1' ")
	List<Map<String,String>> getAllCourseName();

	@Insert("INSERT INTO course(coursename, coursedesc, imageurl) "
	+ "VALUES (#{coursename}, #{coursedesc}, #{imageurl} )")
	void addCourse(String coursename, String coursedesc, String imageurl);

	@Select("SELECT COURSEID FROM COURSE ORDER BY COURSEID DESC FETCH FIRST ROW ONLY")
	int getLatestCourseId();
	
	@Select("SELECT STEPNO FROM ACTIVITY WHERE COURSEID = #{coureseId} ORDER BY STEPNO DESC FETCH FIRST ROW ONLY")
	Integer getLatestStepNo(int courseId);
	
	@Select("SELECT ACTIVITYID FROM ACTIVITY ORDER BY ACTIVITYID DESC FETCH FIRST ROW ONLY")
	int getLatestActivityId();

	
	@Delete("DELETE FROM ACTIVITY WHERE ACTIVITYID = #{activityId}")
	void deleteActivity(int activityId);

	@Update("UPDATE COURSE SET ISSAVED = '1', COURSEORDER = #{courseOrder} WHERE COURSEID = #{courseId}")
	void saved(int courseId, int courseOrder);

	@Select("INSERT INTO activity(courseid, stepno, activitytypeid, activitydesc, imageurl, isquestion) "
			+ "VALUES (#{courseid}, #{stepno}, #{activitytypeid}, #{activitydesc}, #{imageurl}, #{isQuestion}) "
			+ "returning activityid")
	int addActivity(int courseid, int stepno, int activitytypeid, String activitydesc, String imageurl, boolean isQuestion);

	@Insert("INSERT INTO question(quizid, activityid, stepno, questiondesc, correctanswer, useranswer, choiceone, choicetwo, choicethree, choicefour, imageurl) "
			+ "VALUES (NULL, #{activityId}, #{stepno}, #{questiondesc}, #{correctanswer}, NULL, #{choiceone}, #{choicetwo}, #{choicethree}, #{choicefour}, NULL)")
	void addActivityQuestion(int activityId, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour);

	@Select("SELECT * FROM ACTIVITY_TYPE")
	List<Map<String,String>> getAllActivityType();

	@Select("SELECT COUNT(ACTIVITYID) FROM ACTIVITY WHERE COURSEID = #{COURSEID}")
	int countListActivity(int courseId);
	
	@Select("SELECT * FROM fn_insert_chart_activity(#{activityId}, #{chartMasterId}, #{startDate}, #{endDate})")
	int saveActivityChart(int activityId, int chartMasterId, String startDate, String endDate);

	@Delete("DELETE FROM COURSE WHERE COURSEID = #{courseId}")
	void deleteCourseById(int courseId);
	
	@Select("SELECT COURSEORDER FROM COURSE WHERE ISSAVED = '1' ORDER BY COURSEORDER DESC FETCH FIRST ROW ONLY ")
	Integer getLatestCourseOrder();

	@Update("UPDATE COURSE SET COURSEORDER = #{courseOrder} WHERE COURSEID = #{courseId}")
	void changeOrder(int courseOrder, int courseId);

	@Update("UPDATE COURSE SET COURSEORDER = COURSEORDER-1 WHERE COURSEID IN (SELECT COURSEID FROM COURSE WHERE COURSEORDER > #{courseOrder} )")
	void updateOrderBeforeDelete(int courseOrder);
}
