package com.skripsi.howtotrade.model;

public class Coin {
	private String coinCode;
	private String coinName;
	private String coinReturn;
	private boolean isActive;
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public String getCoinReturn() {
		return coinReturn;
	}
	public void setCoinReturn(String coinReturn) {
		this.coinReturn = coinReturn;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
