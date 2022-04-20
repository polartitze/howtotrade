package com.skripsi.howtotrade.model;

public class Activity {

	int activityId;
	int stepNo;
	String activityType;
	String activityDesc;
	String imageUrl;
	boolean isquestion;
	Question question;
	
	
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityName(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageURL(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isIsquestion() {
		return isquestion;
	}
	public void setIsquestion(boolean isquestion) {
		this.isquestion = isquestion;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
