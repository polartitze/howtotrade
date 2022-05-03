package com.skripsi.howtotrade.mapper;

import java.util.List;
import java.util.Map;

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
	Quiz getQuizById(int quizId);
	
	@Select("SELECT * FROM quiz ORDER BY quizid WHERE ISSAVED = '1' ")
	List<Quiz> getAllQuiz();
	
	@Select("SELECT * FROM question where quizid = #{quizId} order by quizid asc, stepno asc")
	List<Question> getAllQuizQuestion(int quizId);
	
	@Select("SELECT count(1) FROM question where quizid = #{quizId}")
	int getQuizTotalQuestion(int quizId);
	
	@Select("SELECT * FROM fn_save_quiz_enroll(#{userId}, #{quizId}, #{score})")
	String saveQuizEnroll(int userId, int quizId, int score);
	
	@Select ("SELECT COUNT(*) FROM quiz_enroll WHERE userid = #{userId} AND quizid = #{quizId}")
	int isExistQuizEnroll(int userId, int quizId);
	
	@Select ("SELECT score FROM quiz_enroll WHERE userid = #{userId} AND quizid = #{quizId}")
	int getQuizHighestScore(int userId, int quizId);
	
	@Select("SELECT * FROM fn_islocked_quiz(#{userId}, #{quizId})")
	boolean getQuizLock(int userId, int quizId);

	@Insert("INSERT INTO quiz(courseid, quizname, quizdesc, imageurl) "
			+ "VALUES (#{courseId}, #{quizName}, #{quizDesc}, #{imageUrl})")
	void addQuiz(int courseId, String quizName, String quizDesc, String imageUrl);
	
	@Select("SELECT QUIZID, QUIZNAME FROM QUIZ")
	List<Map<String,String>> getAllQuizName();

	@Insert("INSERT INTO question(quizid, activityid, stepno, questiondesc, correctanswer, useranswer, choiceone, choicetwo, choicethree, choicefour, imageurl) "
			+ "VALUES (#{quizid}, NULL, #{stepno}, #{questiondesc}, #{correctanswer}, NULL, #{choiceone}, #{choicetwo}, #{choicethree}, #{choicefour}, #{imageurl} )")
	void addQuestion(int quizid, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour, String imageurl);
	
	@Select("SELECT QUIZID FROM QUIZ ORDER BY QUIZID DESC FETCH FIRST ROW ONLY")
	int getLatestQuizId();

	@Select("SELECT STEPNO FROM QUESTION WHERE QUIZID = #{quizId} ORDER BY STEPNO DESC FETCH FIRST ROW ONLY")
	Integer getLatestStepNo(int quizId);

	@Delete("DELETE FROM QUESTION WHERE QUESTIONID = #{questionId}")
	void deleteQuestion(int questionId);

	@Select("SELECT COUNT(QUESTIONID) FROM QUESTION WHERE QUIZID = #{quizId}")
	int countListQuestion(int quizId);

	@Update("UPDATE QUIZ SET ISSAVED = '1' WHERE QUIZID = #{quizId}")
	void saved(int quizId);
}