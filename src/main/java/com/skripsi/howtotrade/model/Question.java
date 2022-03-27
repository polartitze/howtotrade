package com.skripsi.howtotrade.model;

public class Question {
	int questionId;
	String questionDesc;
	int correctAnswer;
	String choiceOne;
	String choiceTwo;
	String choiceThree;
	String choiceFour;
	String imageURL;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
}
