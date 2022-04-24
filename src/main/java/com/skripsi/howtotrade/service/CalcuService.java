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
        calcuMapper.insertCalculate(calc.getTotalInvestasi(), calc.getWaktu(), calc.getPerBulan(), calc.getJenisPerhitungan(), calc.getResults(), calc.getKoin(), userId); 
    }

    public String calcCoin(String totalInvestasi, int waktu, int perBulan){
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
            retval = "Koin yang disarankan: "+coin.toString()+ "\n"  
            +"Perhitungan tabungan per bulan, waktu, dan total investasi terlalu jauh!\n"
            +"Kami menyaranakan Anda menabung " + "Rp" + String.valueOf(round(saranTambahan,0))  + " per bulan.\n"
            +"(Perlu penambahan Rp" + String.valueOf(round((saranTambahan-perBulan), 0))  + " dari yang Anda rencanakan)";
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
    public Double calcTotalInvestment(String koin, int waktu, int perBulan){
        Double totalInvestasi = 0.0;
        Double persentase = Double.valueOf(calcuMapper.getCoinReturn(koin));
        persentase /= 100;
        System.out.println("persentase: "+persentase);
        waktu *= 12; //dikali 12 bulan
        Double temp = 0.0;
        for(int i=0 ; i<waktu ; i++){
            temp = (totalInvestasi + perBulan) * persentase / 12;
            totalInvestasi = totalInvestasi + perBulan + temp;
        }

        System.out.println("===============calcTotalInvestment(): "+totalInvestasi);
        totalInvestasi = round(totalInvestasi, 2);
        return totalInvestasi;
    }

    public Double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
