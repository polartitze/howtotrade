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
	private QuizMapper quizMapper;
	
	public List<Quiz> getAllQuiz(int userId) {
		List<Quiz> quizList = quizMapper.getAllQuiz();
		
		for (Quiz quiz : quizList) {
			quiz.setLock(quizMapper.getQuizLock(userId, quiz.getQuizId()));
			
			quiz.setEnroll(isQuizEnroll(userId, quiz.getQuizId()));
			if(quiz.isEnroll()) {
				quiz.setHighestScore(getQuizHighestScore(userId, quiz.getQuizId()));
				int totalQuestion = getQuizTotalQuestion(quiz.getQuizId());
				if((float)quiz.getHighestScore() /totalQuestion  >= 0.75) {
					quiz.setPass(true);
				}else {
					quiz.setPass(false);
				}
			}
		}
		
		return quizList;
	}
	
	public List<Quiz> getAllQuiz() {
		List<Quiz> quizList = quizMapper.getAllQuiz();
		
		for (Quiz quiz : quizList) {
			quiz.setLock(false);
			
			quiz.setEnroll(false);
			quiz.setPass(false);
		}
		
		return quizList;
	}
	
	public Quiz getQuizById(int quizId) {
		Quiz quiz = quizMapper.getQuizById(quizId);
		quiz.setQuestionList(quizMapper.getAllQuizQuestion(quizId));
		
		return quiz;
	}
	
	private boolean isQuizEnroll(int userId, int quizId) {
		try {
			if(quizMapper.isExistQuizEnroll(userId, quizId) > 0) {
				return true;
			} else {
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private int getQuizTotalQuestion (int quizId) {
		return quizMapper.getQuizTotalQuestion(quizId);
	}
	
	public int getQuizHighestScore(int userId, int quizId) {
		try {
			return quizMapper.getQuizHighestScore(userId, quizId);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean saveProgress(int userId, int quizId, int score) {
		try {
			quizMapper.saveQuizEnroll(userId, quizId, score);
			return true;			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
		quizMapper.saveQuizEnroll(userId, quizId, score);
	}

	public void addQuiz(int courseId, String quizName, String quizDesc, String imageUrl) {
		quizMapper.addQuiz(courseId, quizName, quizDesc, imageUrl);
	}

	// public List<Map<String,String>> getAllQuizName() {
	// 	return quizMapper.getAllQuizName();
	// }

	public void addQuestion(int quizid, int stepno, String questiondesc, int correctanswer,  String choiceone, String choicetwo, String choicethree, String choicefour, String imageurl) {
		quizMapper.addQuestion(quizid, stepno, questiondesc, correctanswer, choiceone, choicetwo, choicethree, choicefour, imageurl);
	}

	public int getLatestQuizId(){
		return quizMapper.getLatestQuizId();
	}
	
	public int getLatestStepNo(int quizId){
		Integer latestStepNo = quizMapper.getLatestStepNo(quizId);
		int result;
		if (latestStepNo == null) {
			result = 0;
		}
		else result = latestStepNo;
		return result;
	}

	public List<Question> getAllQuizQuestion(int quizId){
		return quizMapper.getAllQuizQuestion(quizId);
	}

	public void deleteQuestion(int questionId){
		quizMapper.deleteQuestion(questionId);
	}

	public int countListQuestion(int quizId){
		return quizMapper.countListQuestion(quizId);
	}

	public void saved(int quizId){
		quizMapper.saved(quizId);
	}

	public boolean isPassCourseAndQuiz(String userName, int courseId, int quizId){
		int totalQuestion = getQuizTotalQuestion(quizId);
		int score = quizMapper.isPassCourseAndQuiz(userName, courseId, quizId);

		if((float) score / totalQuestion  >= 0.75) {
			return true;
		}
		return false;
	}

	public void deleteQuizById(int quizId){
		quizMapper.deleteQuizById(quizId);
	}
}