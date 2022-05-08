package com.skripsi.howtotrade.model;

public class Calculator {
	private int calculatorId;
    private Coin coin;
    private String coinCode;
    private CalculatorType calculatorType;
    private String duration;
    private String investasiAwal;
    private String investasiPerbulan;
    private String result;
    private String created;
    

    public int getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(int calculatorId) {
        this.calculatorId = calculatorId;
    }

    public String getCoinCode() {
        return this.coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }



    public Coin getCoin() {
        return this.coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public CalculatorType getCalculatorType() {
        return this.calculatorType;
    }

    public void setCalculatorType(CalculatorType calculatorType) {
        this.calculatorType = calculatorType;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getInvestasiAwal() {
        return this.investasiAwal;
    }

    public void setInvestasiAwal(String investasiAwal) {
        this.investasiAwal = investasiAwal;
    }

    public String getInvestasiPerbulan() {
        return this.investasiPerbulan;
    }

    public void setInvestasiPerbulan(String investasiPerbulan) {
        this.investasiPerbulan = investasiPerbulan;

    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }

    public String getCreated() {
        return this.created;
    }
    
    public void setCreated(String created) {
        this.created = created;
    }
}

