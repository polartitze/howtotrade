package com.skripsi.howtotrade.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.skripsi.howtotrade.mapper.CalcuMapper;
import com.skripsi.howtotrade.model.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcuService {
    
    @Autowired
    private CalcuMapper calcuMapper;

    @Autowired
    private UserService userService;

    public List<Map<String,String>> getAllCoin(){
        return calcuMapper.getAllCoin();
    } 
    
    public List<Map<String,String>> checkInvestmentData(String username){
        int userId = userService.getUserId(username);
        System.out.println("==========="+calcuMapper.checkInvestmentData(userId));
        return calcuMapper.checkInvestmentData(userId);
    }

    public void saveResult(Calculator calc, String username){
        int userId = userService.getUserId(username); 

        if("".equals(calc.getTotalInvestasi()) || calc.getTotalInvestasi() == null){
            calc.setTotalInvestasi("-");
        }
        if("".equals(calc.getKoin()) || calc.getKoin() == null){
            calc.setKoin("-");
        }
        if("".equals(calc.getPerBulan()) || calc.getPerBulan() == null){
            calc.setPerBulan("-");
        }
        if("".equals(calc.getInvestasiAwal()) || calc.getInvestasiAwal() == null){
            calc.setInvestasiAwal("-");
        }
        if("".equals(calc.getWaktu()) || calc.getWaktu() == null){
            calc.setWaktu("-");
        }
        calc.setTotalInvestasi(calc.getTotalInvestasi().replaceAll(",", ""));
        calc.setPerBulan(calc.getPerBulan().replaceAll(",", ""));
        calc.setInvestasiAwal(calc.getInvestasiAwal().replaceAll(",", ""));
        
        calcuMapper.insertCalculate(calc.getWaktu(), calc.getInvestasiAwal(), calc.getPerBulan(), calc.getJenisPerhitungan(), calc.getResults(), calc.getKoin(), userId); 
    }

    public String calcCoin(String totalInvestasi, String waktuString, String perBulanString){
        int perBulan = Integer.parseInt(perBulanString);
        int waktu = Integer.parseInt(waktuString);
        Double temp = Double.valueOf(perBulan * waktu * 12); //aktual tabungan
        Double temp1 = Double.parseDouble(totalInvestasi);
        Double temp2 = temp1 - temp; //kurang berapa utk capai target

        Double persentaseYgDibutuhkan = temp2/temp*100;
        List<Double> list = calcuMapper.getAllCoinReturn();
     
        //cari ke database mana yg mendekati
        //sorting
        int n = list.size();
        Double calc = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (list.get(j - 1) > list.get(j)) {
                    calc = list.get(j - 1);
                    list.set(j-1, list.get(j));
                    list.set(j, calc);
                }
            }
        }
        System.out.println("list.toString(): "+list.toString());

        //cari yg terdekat
        Double persentaseDariDB = 0.0;
        int flag = 0;
        for(int i= 0; i<n; i++){
            if(persentaseYgDibutuhkan <= list.get(i)){
                persentaseDariDB = list.get(i);
                break;
            }
            if(i == n-1 && persentaseDariDB == 0.0){
                persentaseDariDB = list.get(i);
                flag++;
                break;
            }
        }
        System.out.println("persentaseDariDB: "+ persentaseDariDB);
        
        ///output koin + catatan nabung berapa
        List<String> coin = calcuMapper.getRecommendedCoin(persentaseDariDB.intValue());
        
        //catatan bila ada
        String retval = "";
        if(flag > 0){
            Double saranTambahan =  temp1 / (1 + (persentaseDariDB/100)) / 12;
            BigDecimal saran = new BigDecimal(saranTambahan);
            saran = round(saranTambahan, 2);

            Double temporary = saranTambahan-perBulan;
            BigDecimal penambahan = new BigDecimal(temporary);
            penambahan = round(temporary, 2);
            
            retval = "Koin yang disarankan: "+coin.toString()+ "\n"  
            +"Perhitungan tabungan per bulan, waktu, dan total investasi terlalu jauh!\n"
            +"Kami menyaranakan Anda menabung " + "Rp" + saran.toPlainString()  + " per bulan.\n"
            +"(Perlu penambahan Rp" + penambahan.toPlainString()  + " dari yang Anda rencanakan)";
        }
        else{
            retval = "Koin yang disarankan: "+coin.toString();
        }

        return retval;
    }
    
    public void deletePlanning(int planningId){
        calcuMapper.deletePlanning(planningId);
    }

    //ekspektasi mendapatkan TOTAL INVESTASI 
    public String calcTotalInvestment(String coinCode, String waktuString, String perBulanString, String investasiAwalString){
        // A = [ P(1+r/n) pangkat(nt) ] + [ PMT × (((1 + r/n) pangkat (nt) - 1) / (r/n)) ]
        // Keterangan: 
        // A = Total Investasi Akhir
        // P = Nilai Investasi Awal
        // r = Persentase Keuntungan (dalam desimal)
        // n = 12 bulan (perhitungan bulan dalam 1 tahun)
        // t = tahun
        // PMT = Nilai Investasi per bulan
        int perBulan = Integer.parseInt(perBulanString);
        int waktu = Integer.parseInt(waktuString);
        int investasiAwal = Integer.parseInt(investasiAwalString);

        Double persentase = Double.valueOf(calcuMapper.getCoinReturn(coinCode));
        persentase /= 100;

        Double temp = Math.pow((1 + (persentase/12)), (12 * waktu)); //  (1+r/n) pangkat(nt) 
        Double totalInvestasiAkhir = (investasiAwal * temp) + (perBulan * ((temp - 1) / (persentase/12)));
        // Double  = 0.0;
        // totalInvestasiAkhir

        // Double totalInvestasi = 0.0;
        // Double persentase = Double.valueOf(calcuMapper.getCoinReturn(koin));
        // System.out.println("persentase: "+persentase);
        // waktu *= 12; //dikali 12 bulan
        // Double temp = 0.0;
        // for(int i=0 ; i<waktu ; i++){
        //     temp = (totalInvestasi + perBulan) * persentase / 12;
        //     totalInvestasi = totalInvestasi + perBulan + temp;
        // }

        System.out.println("===============calcTotalInvestment(): "+totalInvestasiAkhir);
        // totalInvestasiAkhir = round(totalInvestasiAkhir, 2);
        BigDecimal bd = new BigDecimal(totalInvestasiAkhir);
        bd = round(totalInvestasiAkhir, 2);

        String totalInvestString = "Hasil investasi Anda dalam waktu mendatang : " + bd.toPlainString();
        return totalInvestString;
    }

    public String calcCompounding(String investasiAwalString, String waktuInvestasiString, String coinCode){
        // A = P(1 + i) pangkat (n)
        // Keterangan:  
        // A = Total Investasi Akhir 
        // P = Nilai Investasi Awal 
        // i = Persentase Keuntungan 
        // n = Waktu Investasi (tahunan)
        Double waktuInvestasi = Double.parseDouble(waktuInvestasiString);
        Double investasiAwal = Double.parseDouble(investasiAwalString);

        Double totalInvestasi = 0.0;
        Double persentase = Double.valueOf(calcuMapper.getCoinReturn(coinCode));
        persentase /= 100;
        Double temp = (1 + persentase);
        Double temp2 =  Math.pow(temp, waktuInvestasi);
        totalInvestasi= investasiAwal * temp2;
        // totalInvestasi = round(totalInvestasi, 2);
        BigDecimal bd = new BigDecimal(totalInvestasi);
        bd = round(totalInvestasi, 2);
        String totalInvestString = "Hasil investasi Anda dalam waktu mendatang : " + bd.toPlainString();
        return totalInvestString;

    }

    public BigDecimal round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        // return bd.doubleValue();
        return bd;
    }
}
