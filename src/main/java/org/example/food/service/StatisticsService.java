package org.example.food.service;

import org.example.food.dtos.statistics.StatisticsCreateDto;
import org.example.food.dtos.statistics.StatisticsDto;
import org.example.food.dtos.statistics.StatisticsHomeDto;
import org.example.food.dtos.statistics.StatisticsUpdateDto;

import java.util.List;

public interface StatisticsService {
    void createStatistics(StatisticsCreateDto statisticsCreateDto);
    List<StatisticsDto> getStatistics();
    void updateStatistics(StatisticsUpdateDto statisticsUpdateDto);
    StatisticsUpdateDto findUpdatedStatistics(int id);
    void removeStatistics(int id);
    List<StatisticsHomeDto> getHomeStatistics();
}
