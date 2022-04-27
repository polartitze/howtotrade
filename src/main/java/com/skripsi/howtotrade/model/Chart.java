package com.skripsi.howtotrade.model;

import java.util.List;

public class Chart {
	int chartId;
	String chartName;
	List<Candle> candleList;
	
	public int getChartId() {
		return chartId;
	}
	public void setChartId(int chartId) {
		this.chartId = chartId;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public List<Candle> getCandleList() {
		return candleList;
	}
	public void setCandleList(List<Candle> candleList) {
		this.candleList = candleList;
	}
}
