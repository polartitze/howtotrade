package com.skripsi.howtotrade.model;

import java.util.List;
import java.util.Map;

public class Quiz {
	int quizid;
	int courseId;
	String quizname;
	String quizdesc;
	List<Question> questionList;
	boolean isEnroll;
	List<Map<String, String>> scoreList;
	int finalScore;
	
	public int getQuizid() {
		return quizid;
	}
	public void setQuizid(int quizid) {
		this.quizid = quizid;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getQuizname() {
		return quizname;
	}
	public void setQuizname(String quizname) {
		this.quizname = quizname;
	}
	public String getQuizdesc() {
		return quizdesc;
	}
	public void setQuizdesc(String quizdesc) {
		this.quizdesc = quizdesc;
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
	public List<Map<String, String>> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<Map<String, String>> scoreList) {
		this.scoreList = scoreList;
	}
	public int getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
}