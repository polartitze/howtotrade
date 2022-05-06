package com.skripsi.howtotrade.controller;

import java.security.Principal;

import com.skripsi.howtotrade.model.Calculator;
import com.skripsi.howtotrade.service.CalcuService;
import com.skripsi.howtotrade.utility.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/calculator")
public class CalcuController {
    
    @Autowired
    private CalcuService calcuService;

    @RequestMapping("")
    private String page(Model model, Principal principal){
        System.out.println("================: "+calcuService.getAllCoin());
        model.addAttribute("listCoin", calcuService.getAllCoin());
        model.addAttribute("calculatorForm", new Calculator());
        model.addAttribute("history", calcuService.checkInvestmentData(principal.getName()));
        return "calculator/investplan" ;
    }

    @RequestMapping("/save-calculation")
    private String saveResult(Model model, Calculator calc, Principal principal){
        System.out.println("===========SAVE RESULT===============");
        System.out.println("calc.jenisPerhitungan: "+calc.getJenisPerhitungan());
        System.out.println("calc.totalInvestasi: "+calc.getTotalInvestasi());
        System.out.println("calc.koin: "+calc.getKoin());
        System.out.println("calc.waktu: "+calc.getWaktu());
        System.out.println("calc.perBulan: "+calc.getPerBulan());
        System.out.println("calc.getResult(): "+calc.getResults());

        calcuService.saveResult(calc, principal.getName());
        return "redirect:/calculator" ;
    }

    @ResponseBody
    @RequestMapping("/calculate-result")
    private String calculateResult(Model model, 
        @RequestParam(value =  "jenisPerhitungan", required = true) String jenisPerhitungan,
        @RequestParam(value =  "totalInvestasi", required = false) String totalInvestasi,
        @RequestParam(value =  "koin", required = false) String koin,
        @RequestParam(value =  "waktu", required = false) String waktu,
        @RequestParam(value =  "perBulan", required = false) String perBulan,
        @RequestParam(value =  "investasiAwal", required = false) String investasiAwal){
            
        String retval = "";
        System.out.println("jenisPerhitungan: "+jenisPerhitungan);
        System.out.println("totalInvestasi: "+totalInvestasi);
        System.out.println("koin: "+koin);
        System.out.println("waktu: "+waktu);
        System.out.println("perBulan: "+perBulan);
        System.out.println("investasiAwal: "+investasiAwal);

        // if(Constant.HITUNG_COIN_YANG_COCOK.equals(jenisPerhitungan)){
        //     retval = calcuService.calcCoin(totalInvestasi, waktu, perBulan);
        // }
        if(Constant.HITUNG_TOTAL_INVESTASI.equals(jenisPerhitungan)){
            retval = String.valueOf(calcuService.calcTotalInvestment(koin, waktu, perBulan,investasiAwal));
        }
        else if(Constant.HITUNG_COMPOUNDING_INTEREST.equals(jenisPerhitungan)){
            retval = String.valueOf(calcuService.calcCompounding(investasiAwal, waktu, koin));
        }
        System.out.println("==========HASIL PERHITUNGAN: "+retval);
        return retval;
    }

    @RequestMapping("/delete/{planningId}")
    private String page(Model model, Principal principal, @PathVariable int planningId){
        calcuService.deletePlanning(planningId);
        return "redirect:/calculator" ;
    }
}
