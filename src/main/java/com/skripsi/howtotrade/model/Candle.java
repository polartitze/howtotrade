package com.skripsi.howtotrade.model;

public class Candle {
	int candleId;
	String candleTime;
	Integer openPrice;
	Integer highPrice;
	Integer lowPrice;
	Integer closePrice;
	public int getCandleId() {
		return candleId;
	}
	public void setCandleId(int candleId) {
		this.candleId = candleId;
	}
	public String getCandleTime() {
		return candleTime;
	}
	public void setCandleTime(String candleTime) {
		this.candleTime = candleTime;
	}
	public Integer getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Integer openPrice) {
		this.openPrice = openPrice;
	}
	public Integer getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Integer highPrice) {
		this.highPrice = highPrice;
	}
	public Integer getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Integer lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Integer getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Integer closePrice) {
		this.closePrice = closePrice;
	}
}
