package com.skripsi.howtotrade.model;

public class Activity {

	private int activityId;
	private int stepNo;
	private String activityType;
	private String activityDesc;
	private String imageUrl;
	private boolean isQuestion;
	private Question question;
	private Chart chart;
	private int courseId;
	private int activityTypeId;

	private int chartMasterId;
	private String startDate;
	private String endDate;
	
	public int getActivityId() {
		return this.activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getStepNo() {
		return this.stepNo;
	}

	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}

	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityDesc() {
		return this.activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Chart getChart() {
		return this.chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getActivityTypeId() {
		return this.activityTypeId;
	}

	public void setActivityTypeId(int activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	public int getChartMasterId() {
		return chartMasterId;
	}

	public void setChartMasterId(int chartMasterId) {
		this.chartMasterId = chartMasterId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean getIsQuestion() {
		return isQuestion;
	}

	public void setIsQuestion(boolean isQuestion) {
		this.isQuestion = isQuestion;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	

	
}
