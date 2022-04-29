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
	
	public List<Quiz> getAllQuiz(int userId) {
		List<Quiz> quizList = mapper.getAllQuiz();
		
		for (Quiz quiz : quizList) {
			quiz.setEnroll(isQuizEnroll(userId, quiz.getQuizId()));
			
			if(quiz.isEnroll()) {
				quiz.setHighestScore(getQuizHighestScore(userId, quiz.getQuizId()));
			}
		}
		
		return quizList;
	}
	
	public Quiz getQuizById(int quizId) {
		Quiz quiz = mapper.getQuizById(quizId);
		quiz.setQuestionList(mapper.getAllQuizQuestion(quizId));
		
		return quiz;
	}
	
	private boolean isQuizEnroll(int userId, int courseId) {
		try {
			if(mapper.isExistQuizEnroll(userId, courseId) > 0) {
				return true;
			} else {
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private int getQuizHighestScore(int userId, int quizId) {
		try {
			return mapper.getQuizHighestScore(userId, quizId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
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