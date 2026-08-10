package org.example.food.controller;

import org.example.food.dtos.statistics.StatisticsCreateDto;
import org.example.food.dtos.statistics.StatisticsDto;
import org.example.food.dtos.statistics.StatisticsUpdateDto;
import org.example.food.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/admin/statistics")
    public String statistic(Model model){
        List<StatisticsDto> statisticsDtoList = statisticsService.getStatistics();
        model.addAttribute("statistics",statisticsDtoList);
        return "dashboard/statistics/statistics";
    }

    @GetMapping("/admin/statistics/statistics-create")
    public String addStatistics(){
        return "dashboard/statistics/statistics-create";
    }

    @PostMapping("/admin/statistics/create")
    public String addStatistics(@ModelAttribute StatisticsCreateDto statisticsCreateDto){
        statisticsService.createStatistics(statisticsCreateDto);
        return "redirect:/admin/statistics";
    }

    @GetMapping("/admin/statistics/statistics-edit/{id}")
    public String updatedStatistics(@PathVariable int id, Model model){
        StatisticsUpdateDto statisticsUpdateDto = statisticsService.findUpdatedStatistics(id);
        model.addAttribute("statistics", statisticsUpdateDto);
        return "dashboard/statistics/statistics-edit";
    }

    @PostMapping("/admin/statistics/update")
    public String updateStatistics(@ModelAttribute StatisticsUpdateDto statisticsUpdateDto){
        statisticsService.updateStatistics(statisticsUpdateDto);
        return "redirect:/admin/statistics";
    }

    @GetMapping("/admin/statistics/remove/{id}")
    public String removeStatistics(@ModelAttribute @PathVariable int id){
        statisticsService.removeStatistics(id);
        return "redirect:/admin/statistics";
    }
}
