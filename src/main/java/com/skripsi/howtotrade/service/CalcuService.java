package com.skripsi.howtotrade.service;

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

    public void saveResult(Calculator calc){
        Double totalInvestasi = Double.parseDouble(calc.getTotalInvestasi());
        int persentase;
        persentase = calcuMapper.getCoinReturn(calc.getKoin());

        if("10".equals(calc.getJenisPerhitungan())){
            // return "pilihan satu";
        }
        else if("20".equals(calc.getJenisPerhitungan())){
            int waktu = calc.getWaktu() * 12; //dikali 12 bulan
            Double perBulan = Double.valueOf(calc.getPerBulan());
            for(int i=0 ; i<waktu ; i++){
                totalInvestasi = (totalInvestasi + perBulan) * 1/12 * persentase;
            }
            System.out.println("===============total investasi: "+totalInvestasi);
            
            //insert calculation
            int userId = userService.getUserId("alvin"); //FIXME: when authentication has been set, change into 'userLogged'
            System.out.println("================koin: "+calc.getKoin());
            int coinId = calcuMapper.getCoinId(calc.getKoin());
            calcuMapper.insertCalculate(totalInvestasi, calc.getWaktu(), userId, coinId, calc.getPerBulan());
        }
    }

    public Double calculateResult(String jenisPerhitungan, String temp, String koin, int waktu, int perBulan){
        Double totalInvestasi = Double.parseDouble(temp);
        Double persentase = Double.valueOf(calcuMapper.getCoinReturn(koin));
        if("10".equals(jenisPerhitungan)){
            return 0.0;
        }
        else if("20".equals(jenisPerhitungan)){
            waktu *= 12; //dikali 12 bulan
            for(int i=0 ; i<waktu ; i++){
                totalInvestasi = (totalInvestasi + perBulan) * 1/12 * persentase;
            }
            System.out.println("===============total investasi: "+totalInvestasi);
            return totalInvestasi;
        }
        return 0.0;
    }
    
}
