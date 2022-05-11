package com.skripsi.howtotrade.model;

public class Candle {
	private int candleId;
	private String candleTime;
	private Float openPrice;
	private Float highPrice;
	private Float lowPrice;
	private Float closePrice;
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
	public Float getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Float openPrice) {
		this.openPrice = openPrice;
	}
	public Float getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Float highPrice) {
		this.highPrice = highPrice;
	}
	public Float getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Float lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Float getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Float closePrice) {
		this.closePrice = closePrice;
	}
}
