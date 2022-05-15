package com.skripsi.howtotrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skripsi.howtotrade.model.Question;
import com.skripsi.howtotrade.model.Quiz;

@Mapper
public interface QuizMapper {
	
	@Select("SELECT * FROM quiz WHERE quizid = #{quizId}")
	public Quiz getQuizById(int quizId);
	
	@Select("SELECT Q.* FROM QUIZ Q LEFT JOIN COURSE C ON Q.COURSEID = C.COURSEID WHERE Q.ISSAVED = '1' ORDER BY C.COURSEORDER ")
	public List<Quiz> getAllQuiz();
	
	@Select("SELECT * FROM question where quizid = #{quizId} order by quizid asc, stepno asc")
	public List<Question> getAllQuizQuestion(int quizId);
	
	@Select("SELECT count(1) FROM question where quizid = #{quizId}")
	public int getQuizTotalQuestion(int quizId);
	
	@Select("SELECT * FROM fn_save_quiz_enroll(#{userId}, #{quizId}, #{score})")
	public String saveQuizEnroll(int userId, int quizId, int score);
	
	@Select ("SELECT COUNT(*) FROM quiz_enroll WHERE userid = #{userId} AND quizid = #{quizId}")
	public int isExistQuizEnroll(int userId, int quizId);
	
	@Select ("SELECT score FROM quiz_enroll WHERE userid = #{userId} AND quizid = #{quizId}")
	public int getQuizHighestScore(int userId, int quizId);
	
	@Select("SELECT * FROM fn_islocked_quiz(#{userId}, #{quizId})")
	public boolean getQuizLock(int userId, int quizId);

	@Insert("INSERT INTO quiz(courseid, quizname, quizdesc, imageurl) "
			+ "VALUES (#{courseId}, #{quizName}, #{quizDesc}, #{imageUrl})")
	public void addQuiz(int courseId, String quizName, String quizDesc, String imageUrl);
	
	@Insert("INSERT INTO question(quizid, activityid, stepno, questiondesc, correctanswer, useranswer, choiceone, choicetwo, choicethree, choicefour, imageurl) "
			+ "VALUES (#{quizid}, NULL, #{stepno}, #{questiondesc}, #{correctanswer}, NULL, #{choiceone}, #{choicetwo}, #{choicethree}, #{choicefour}, #{imageurl} )")
	public void addQuestion(int quizid, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour, String imageurl);
	
	@Select("SELECT QUIZID FROM QUIZ ORDER BY QUIZID DESC FETCH FIRST ROW ONLY")
	public int getLatestQuizId();

	@Select("SELECT STEPNO FROM QUESTION WHERE QUIZID = #{quizId} ORDER BY STEPNO DESC FETCH FIRST ROW ONLY")
	public Integer getLatestStepNo(int quizId);

	@Delete("DELETE FROM QUESTION WHERE QUESTIONID = #{questionId}")
	public void deleteQuestion(int questionId);

	@Select("SELECT COUNT(QUESTIONID) FROM QUESTION WHERE QUIZID = #{quizId}")
	public int countListQuestion(int quizId);

	@Update("UPDATE QUIZ SET ISSAVED = '1' WHERE QUIZID = #{quizId}")
	public void saved(int quizId);

	@Select("SELECT SCORE FROM USERS U INNER JOIN COURSE_ENROLL CE ON U.USERID = CE.USERID INNER JOIN QUIZ_ENROLL QE ON U.USERID = QE.USERID "
			+"WHERE USERNAME = #{userName} AND COURSEID = #{courseId} AND QUIZID = #{quizId}")
	public int isPassCourseAndQuiz(String userName, int courseId, int quizId);

	@Delete("DELETE FROM QUIZ WHERE QUIZID = #{quizId}")
	public void deleteQuizById(int quizId);
}