package com.skripsi.howtotrade.model;

public class Calculator {
	private int planningId;
    private String totalInvestasi;
    private Coin coin;
    private String calculatorType;
    private String duration;
    private String investasiAwal;
    private String investasiPerbulan;
    private String result;
    
	public int getPlanningId() {
		return planningId;
	}
	public void setPlanningId(int planningId) {
		this.planningId = planningId;
	}
	public String getTotalInvestasi() {
		return totalInvestasi;
	}
	public void setTotalInvestasi(String totalInvestasi) {
		this.totalInvestasi = totalInvestasi;
	}
	public Coin getCoin() {
		return coin;
	}
	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	public String getCalculatorType() {
		return calculatorType;
	}
	public void setCalculatorType(String calculatorType) {
		this.calculatorType = calculatorType;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getInvestasiAwal() {
		return investasiAwal;
	}
	public void setInvestasiAwal(String investasiAwal) {
		this.investasiAwal = investasiAwal;
	}
	public String getInvestasiPerbulan() {
		return investasiPerbulan;
	}
	public void setInvestasiPerbulan(String investasiPerbulan) {
		this.investasiPerbulan = investasiPerbulan;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
