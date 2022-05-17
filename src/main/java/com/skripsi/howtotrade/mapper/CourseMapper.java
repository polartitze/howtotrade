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
	public int isExistCourseEnroll(int userId, int courseId);
	
	@Select("SELECT * FROM course WHERE ISSAVED = '1' ORDER BY courseOrder ")
	public List<Course> getAllCourse();
	
	@Select("SELECT * FROM course WHERE courseid = #{courseId}")
	public Course getCourseById(int courseId);
	
	@Select("SELECT a.*, at.name as activitytype FROM activity a "
			+ "INNER JOIN activity_type at ON at.id = a.activitytypeid "
			+ "WHERE courseId = #{courseId} "
			+ "ORDER BY a.stepNo asc")
	public List<Activity> getAllCourseActivity(int courseId);

	@Select("SELECT * FROM question where activityId = #{activityId}")
	public Question getQuestion(int activityId);
	
	@Select("SELECT * FROM fn_save_course_enroll(#{userId}, #{courseId})")
	public String saveCourseEnroll(int userId, int courseId);
	
	@Select("SELECT * from chart where activityId = #{activityId}")
	public Chart getChartByActivityId(int activityId);
	
	@Select("SELECT * from chart where activityid = 0 order by chartid")
	public List<Chart> getChartMaster();
	
	@Select("SELECT * FROM candle where chartId = #{chartId}")
	public List<Candle> getAllCandleByChartId(int chartId);
	
	@Select("SELECT * FROM fn_islocked_course(#{userId}, #{courseId})")
	public boolean getCourseLock(int userId, int courseId);

	@Select("SELECT COURSEID, COURSENAME FROM COURSE "
			+ "WHERE COURSEID NOT IN (SELECT DISTINCT COURSEID FROM QUIZ) AND ISSAVED = '1' ")
	public List<Map<String,String>> getAllCourseName();

	@Insert("INSERT INTO course(coursename, coursedesc, imageurl) "
	+ "VALUES (#{coursename}, #{coursedesc}, #{imageurl} )")
	public void addCourse(String coursename, String coursedesc, String imageurl);

	@Select("SELECT COURSEID FROM COURSE ORDER BY COURSEID DESC FETCH FIRST ROW ONLY")
	public int getLatestCourseId();
	
	@Select("SELECT STEPNO FROM ACTIVITY WHERE COURSEID = #{coureseId} ORDER BY STEPNO DESC FETCH FIRST ROW ONLY")
	public Integer getLatestStepNo(int courseId);
	
	@Select("SELECT ACTIVITYID FROM ACTIVITY ORDER BY ACTIVITYID DESC FETCH FIRST ROW ONLY")
	public int getLatestActivityId();
	
	@Delete("DELETE FROM ACTIVITY WHERE ACTIVITYID = #{activityId}")
	public void deleteActivity(int activityId);

	@Update("UPDATE COURSE SET ISSAVED = '1', COURSEORDER = #{courseOrder} WHERE COURSEID = #{courseId}")
	public void saved(int courseId, int courseOrder);

	@Select("INSERT INTO activity(courseid, stepno, activitytypeid, activitydesc, imageurl, isquestion) "
			+ "VALUES (#{courseid}, #{stepno}, #{activitytypeid}, #{activitydesc}, #{imageurl}, #{isQuestion}) "
			+ "returning activityid")
	public int addActivity(int courseid, int stepno, int activitytypeid, String activitydesc, String imageurl, boolean isQuestion);

	@Insert("INSERT INTO question(quizid, activityid, stepno, questiondesc, correctanswer, useranswer, choiceone, choicetwo, choicethree, choicefour, imageurl) "
			+ "VALUES (NULL, #{activityId}, #{stepno}, #{questiondesc}, #{correctanswer}, NULL, #{choiceone}, #{choicetwo}, #{choicethree}, #{choicefour}, NULL)")
	public void addActivityQuestion(int activityId, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour);

	@Select("SELECT * FROM ACTIVITY_TYPE")
	public List<Map<String,String>> getAllActivityType();

	@Select("SELECT COUNT(ACTIVITYID) FROM ACTIVITY WHERE COURSEID = #{COURSEID}")
	public int countListActivity(int courseId);
	
	@Select("SELECT * FROM fn_insert_chart_activity(#{activityId}, #{chartMasterId}, #{startDate}, #{endDate})")
	public int saveActivityChart(int activityId, int chartMasterId, String startDate, String endDate);

	@Delete("DELETE FROM COURSE WHERE COURSEID = #{courseId}")
	public void deleteCourseById(int courseId);
	
	@Select("SELECT courseorder FROM course WHERE courseId = ${courseId}")
	public Integer getCourseOrder(int courseId);
	
	@Select("SELECT COURSEORDER FROM COURSE WHERE ISSAVED = '1' ORDER BY COURSEORDER DESC FETCH FIRST ROW ONLY ")
	public Integer getLatestCourseOrder();

	@Update("UPDATE COURSE SET COURSEORDER = #{courseOrder} WHERE COURSEID = #{courseId}")
	public void changeOrder(int courseOrder, int courseId);

	@Update("UPDATE COURSE SET COURSEORDER = COURSEORDER-1 WHERE COURSEID IN (SELECT COURSEID FROM COURSE WHERE COURSEORDER > #{courseOrder} )")
	public void updateOrderBeforeDelete(int courseOrder);
}
