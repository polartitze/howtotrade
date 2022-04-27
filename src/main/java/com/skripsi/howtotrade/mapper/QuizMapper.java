package com.skripsi.howtotrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.skripsi.howtotrade.model.Question;
import com.skripsi.howtotrade.model.Quiz;

@Mapper
public interface QuizMapper {
	
	@Select("SELECT * FROM quiz WHERE quizid = #{quizId}")
	Quiz getQuizById(int quizId);
	
	@Select("SELECT * FROM quiz ORDER BY quizid")
	List<Quiz> getAllQuiz();
	
	@Select("SELECT * FROM question where quizid = #{quizId}")
	List<Question> getAllQuizQuestion(int quizId);
	
	@Select("SELECT * FROM fn_save_quiz_enroll(#{userId}, #{courseId}, #{score})")
	void saveQuizEnroll(int userId, int quizId, int score);
}