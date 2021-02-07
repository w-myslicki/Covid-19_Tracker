package edu.wmyslicki.covid19tracker.controllers;

import edu.wmyslicki.covid19tracker.models.LocationStats;
import edu.wmyslicki.covid19tracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
public class MainController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping()
    public String mainPage(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int sumTotalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCase()).sum();
        int sumTotalThisDayCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("allStats", allStats);
        model.addAttribute("sumStats", sumTotalCases);
        model.addAttribute("thisDay", sumTotalThisDayCases);
        return "index";
    }

}
