package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.models.LocationStats1;
import io.javabrains.coronavirustracker.models.LocationStats2;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService1;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @Autowired
    CoronaVirusDataService1 coronaVirusDataService1;
    @Autowired
    CoronaVirusDataService2 coronaVirusDataService2;
    @GetMapping("/")
    public String home(Model model)
    {
       List<LocationStats> allStates=coronaVirusDataService.getAllStats();
       List<LocationStats1> allStates1=coronaVirusDataService1.getAllStats();
       List<LocationStats2> allStates2=coronaVirusDataService2.getAllStats();
       //................
       int totalReportedCases=allStates.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
       int totalNewCases=allStates.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
       model.addAttribute("locationStats",allStates);
       model.addAttribute("totalReportedCases",totalReportedCases);
       model.addAttribute("totalNewCases",totalNewCases);
       //.................
        int totalDeathCases=allStates1.stream().mapToInt(stat->stat.getLatestDeathCases()).sum();
        int totalNewDeathCases=allStates1.stream().mapToInt(stat->stat.getDeathDiffFromPrevDay()).sum();
        model.addAttribute("locationStats1",allStates1);
        model.addAttribute("totalDeathCases",totalDeathCases);
        model.addAttribute("totalNewDeathCases",totalNewDeathCases);
       //..................
        int totalRecoveredCases=allStates2.stream().mapToInt(stat->stat.getLatestRecoveredCases()).sum();
        int totalNewRecoveredCases=allStates2.stream().mapToInt(stat->stat.getRecoveredDiffFromPrevDay()).sum();
        model.addAttribute("locationStats2",allStates2);
        model.addAttribute("totalRecoveredCases",totalRecoveredCases);
        model.addAttribute("totalNewRecoveredCases",totalNewRecoveredCases);
        return "home";
    }
    @RequestMapping("/covidcases")
    public String case1(Model model)
    {
        List<LocationStats> allStates=coronaVirusDataService.getAllStats();
        int totalReportedCases=allStates.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases=allStates.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStates);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "case1";
    }
    @RequestMapping("/deathcases")
    public String case2(Model model)
    {
        List<LocationStats1> allStates1=coronaVirusDataService1.getAllStats();
        int totalDeathCases=allStates1.stream().mapToInt(stat->stat.getLatestDeathCases()).sum();
        int totalNewDeathCases=allStates1.stream().mapToInt(stat->stat.getDeathDiffFromPrevDay()).sum();
        model.addAttribute("locationStats1",allStates1);
        model.addAttribute("totalDeathCases",totalDeathCases);
        model.addAttribute("totalNewDeathCases",totalNewDeathCases);

        return "case2";
    }
    @RequestMapping("/recoveredcases")
    public String case3(Model model)
    {
        List<LocationStats2> allStates2=coronaVirusDataService2.getAllStats();
        int totalRecoveredCases=allStates2.stream().mapToInt(stat->stat.getLatestRecoveredCases()).sum();
        int totalNewRecoveredCases=allStates2.stream().mapToInt(stat->stat.getRecoveredDiffFromPrevDay()).sum();
        model.addAttribute("locationStats2",allStates2);
        model.addAttribute("totalRecoveredCases",totalRecoveredCases);
        model.addAttribute("totalNewRecoveredCases",totalNewRecoveredCases);
        return "case3";
    }
}

