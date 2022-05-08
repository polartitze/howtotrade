package com.skripsi.howtotrade.model;

public class Coin {
	private String coinCode;
	private String coinName;
	private String coinReturn;
	private boolean isActive;


	public String getCoinCode() {
		return this.coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getCoinName() {
		return this.coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getCoinReturn() {
		return this.coinReturn;
	}

	public void setCoinReturn(String coinReturn) {
		this.coinReturn = coinReturn;
	}

	public boolean isIsActive() {
		return this.isActive;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
