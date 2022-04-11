package com.skripsi.howtotrade.model;

public class Question {
	int questionId;
	int stepNo;
	String questionDesc;
	int correctAnswer;
	int userAnswer;
	String choiceOne;
	String choiceTwo;
	String choiceThree;
	String choiceFour;
	String imageUrl;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public int getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getChoiceOne() {
		return choiceOne;
	}
	public void setChoiceOne(String choiceOne) {
		this.choiceOne = choiceOne;
	}
	public String getChoiceTwo() {
		return choiceTwo;
	}
	public void setChoiceTwo(String choiceTwo) {
		this.choiceTwo = choiceTwo;
	}
	public String getChoiceThree() {
		return choiceThree;
	}
	public void setChoiceThree(String choiceThree) {
		this.choiceThree = choiceThree;
	}
	public String getChoiceFour() {
		return choiceFour;
	}
	public void setChoiceFour(String choiceFour) {
		this.choiceFour = choiceFour;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}	
}
