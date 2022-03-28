package com.skripsi.howtotrade.model;

public class Calculator {
    private String jenisPerhitungan;
    private String totalInvestasi;
    private String koin;
    private int waktu;
    private int perBulan;
    private String flag;

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPerBulan() {
        return this.perBulan;
    }

    public void setPerBulan(int perBulan) {
        this.perBulan = perBulan;
    }

    public String getJenisPerhitungan() {
        return this.jenisPerhitungan;
    }

    public void setJenisPerhitungan(String jenisPerhitungan) {
        this.jenisPerhitungan = jenisPerhitungan;
    }

    public String getTotalInvestasi() {
        return this.totalInvestasi;
    }

    public void setTotalInvestasi(String totalInvestasi) {
        this.totalInvestasi = totalInvestasi;
    }

    public String getKoin() {
        return this.koin;
    }

    public void setKoin(String koin) {
        this.koin = koin;
    }

    public int getWaktu() {
        return this.waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }

}
