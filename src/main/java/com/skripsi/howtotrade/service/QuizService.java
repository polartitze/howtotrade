package com.skripsi.howtotrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skripsi.howtotrade.mapper.QuizMapper;
import com.skripsi.howtotrade.model.Question;
import com.skripsi.howtotrade.model.Quiz;

@Service
public class QuizService {
	
	@Autowired
	QuizMapper mapper;
	
	public List<Quiz> getAllQuiz() {
		return mapper.getAllQuiz();
	}
	
	public Quiz getQuizById(int quizId) {
		Quiz quiz = mapper.getQuizById(quizId);
		quiz.setQuestionList(mapper.getAllQuizQuestion(quizId));
		
		return quiz;
	}
	
	public void finishQuiz(int quizId, int userId, int score) {
		
		mapper.saveQuizEnroll(userId, quizId, score);
		return;
	}
	
	public int countScore(Quiz quiz) {
		int score = 0;
		
		for (Question question : quiz.getQuestionList()) {
			if (question.getCorrectAnswer() == question.getUserAnswer()) {
				score++;
			}
		}
		return score;
	}
	
	public void saveQuizEnroll(int quizId, int userId, int score) {
		mapper.saveQuizEnroll(userId, quizId, score);
	}
}