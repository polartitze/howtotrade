package com.skripsi.howtotrade.model;

public class Question {
	int quizId;
	int activityId;
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

	public int getQuizId() {
		return this.quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getStepNo() {
		return this.stepNo;
	}

	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}

	public String getQuestionDesc() {
		return this.questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public int getCorrectAnswer() {
		return this.correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getUserAnswer() {
		return this.userAnswer;
	}

	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getChoiceOne() {
		return this.choiceOne;
	}

	public void setChoiceOne(String choiceOne) {
		this.choiceOne = choiceOne;
	}

	public String getChoiceTwo() {
		return this.choiceTwo;
	}

	public void setChoiceTwo(String choiceTwo) {
		this.choiceTwo = choiceTwo;
	}

	public String getChoiceThree() {
		return this.choiceThree;
	}

	public void setChoiceThree(String choiceThree) {
		this.choiceThree = choiceThree;
	}

	public String getChoiceFour() {
		return this.choiceFour;
	}

	public void setChoiceFour(String choiceFour) {
		this.choiceFour = choiceFour;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
