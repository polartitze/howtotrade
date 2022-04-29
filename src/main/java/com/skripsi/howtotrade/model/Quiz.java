package com.skripsi.howtotrade.model;

import java.util.List;
import java.util.Map;

public class Quiz {
	int quizId;
	int courseId;
	String quizName;
	String quizDesc;
	List<Question> questionList;
	boolean isEnroll;
	int highestScore;
	int finalScore;
	String imageUrl;
	
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public String getQuizDesc() {
		return quizDesc;
	}
	public void setQuizDesc(String quizDesc) {
		this.quizDesc = quizDesc;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	public boolean isEnroll() {
		return isEnroll;
	}
	public void setEnroll(boolean isEnroll) {
		this.isEnroll = isEnroll;
	}
	public int getHighestScore() {
		return highestScore;
	}
	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}
	public int getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}