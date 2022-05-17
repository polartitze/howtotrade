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
    public String page(Model model, Principal principal){
        model.addAttribute("listCoin", calcuService.getAllCoin());
        model.addAttribute("calculatorForm", new Calculator());
        model.addAttribute("history", calcuService.checkInvestmentData(principal.getName()));
        model.addAttribute("listCalcType", calcuService.getAllCalculatorType());
        
        return "calculator/investplan" ;
    }

    @RequestMapping("/save-calculation")
    public String saveResult(Model model, Calculator calc, Principal principal){
        System.out.println("===========SAVE RESULT===============");
        System.out.println("calc.toString: "+calc.toString());
        

        calcuService.saveResult(calc, principal.getName());
        return "redirect:/calculator" ;
    }

    @ResponseBody
    @RequestMapping("/calculate-result")
    public String calculateResult(Model model, 
        @RequestParam(value =  "jenisPerhitungan", required = true) String calcTypeStr,
        @RequestParam(value =  "koin", required = false) String coinCode,
        @RequestParam(value =  "waktu", required = false) String duration,
        @RequestParam(value =  "perBulan", required = false) String investasiPerbulan,
        @RequestParam(value =  "investasiAwal", required = false) String investasiAwal){
            
        String retval = "";
        System.out.println("jenisPerhitungan: "+calcTypeStr);
        System.out.println("koin: "+coinCode);
        System.out.println("waktu: "+duration);
        System.out.println("perBulan: "+investasiPerbulan);
        System.out.println("investasiAwal: "+investasiAwal);

        int calculatorTypeId = Integer.parseInt(calcTypeStr);

        if(Constant.HITUNG_TOTAL_INVESTASI == calculatorTypeId){
            retval = String.valueOf(calcuService.calcTotalInvestment(coinCode, duration, investasiPerbulan, investasiAwal));
        }
        else if(Constant.HITUNG_COMPOUNDING_INTEREST == calculatorTypeId){
            retval = String.valueOf(calcuService.calcCompounding(investasiAwal, duration, coinCode));
        }
        System.out.println("==========HASIL PERHITUNGAN: "+retval);
        return retval;
    }

    @RequestMapping("/delete/{calculatorId}")
    public String deleteHistory(Model model, Principal principal, @PathVariable int calculatorId){
        calcuService.deletePlanning(calculatorId);
        return "redirect:/calculator" ;
    }
}
