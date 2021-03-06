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
	private String dateRange;
	
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

	public boolean getIsQuestion() {
		return this.isQuestion;
	}

	public void setIsQuestion(boolean isQuestion) {
		this.isQuestion = isQuestion;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

}
