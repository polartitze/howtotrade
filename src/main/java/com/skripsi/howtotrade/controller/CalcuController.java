package com.skripsi.howtotrade.controller;

import java.security.Principal;

import com.skripsi.howtotrade.model.Calculator;
import com.skripsi.howtotrade.service.CalcuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private String saveResult(Model model, Calculator calc){
        
        System.out.println("calc.jenisPerhitungan: "+calc.getJenisPerhitungan());
        System.out.println("calc.totalInvestasi: "+calc.getTotalInvestasi());
        System.out.println("calc.koin: "+calc.getKoin());
        System.out.println("calc.waktu: "+calc.getWaktu());
        System.out.println("calc.perBulan: "+calc.getPerBulan());

        calcuService.saveResult(calc);

        return "redirect:/calculator" ;
    }

    @ResponseBody
    @RequestMapping("/calculate-result")
    private Double calculateResult(Model model, 
        @RequestParam("jenisPerhitungan") String jenisPerhitungan,
        @RequestParam("totalInvestasi") String totalInvestasi,
        @RequestParam("koin") String koin,
        @RequestParam("waktu") int waktu,
        @RequestParam("perBulan") int perBulan){

        System.out.println("jenisPerhitungan: "+jenisPerhitungan);
        System.out.println("totalInvestasi: "+totalInvestasi);
        System.out.println("koin: "+koin);
        System.out.println("waktu: "+waktu);
        System.out.println("perBulan: "+perBulan);

        Double retval;
        retval = calcuService.calculateResult(jenisPerhitungan, totalInvestasi, koin, waktu, perBulan);
        System.out.println("==========retval: "+retval);
        return retval;
    }

    // @ResponseBody
    // @RequestMapping("/get-history")
    // private Double getHistory(Model model, 
    //     @RequestParam("jenisPerhitungan") String jenisPerhitungan,
    //     @RequestParam("totalInvestasi") String totalInvestasi,
    //     @RequestParam("koin") String koin,
    //     @RequestParam("waktu") int waktu,
    //     @RequestParam("perBulan") int perBulan){

    //     System.out.println("jenisPerhitungan: "+jenisPerhitungan);
    //     System.out.println("totalInvestasi: "+totalInvestasi);
    //     System.out.println("koin: "+koin);
    //     System.out.println("waktu: "+waktu);
    //     System.out.println("perBulan: "+perBulan);

    //     Double retval;
    //     retval = calcuService.calculateResult(jenisPerhitungan, totalInvestasi, koin, waktu, perBulan);
    //     System.out.println("==========retval: "+retval);
    //     return retval;
    // }
}
