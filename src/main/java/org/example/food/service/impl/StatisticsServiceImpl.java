package org.example.food.service.impl;

import org.example.food.dtos.statistics.StatisticsCreateDto;
import org.example.food.dtos.statistics.StatisticsDto;
import org.example.food.dtos.statistics.StatisticsHomeDto;
import org.example.food.dtos.statistics.StatisticsUpdateDto;
import org.example.food.mapper.StatisticsMapper;
import org.example.food.model.Statistics;
import org.example.food.repository.StatisticsRepository;
import org.example.food.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public void createStatistics(StatisticsCreateDto statisticsCreateDto) {
        Statistics statistics = statisticsMapper.toEntity(statisticsCreateDto);
        statisticsRepository.save(statistics);
    }

    @Override
    public List<StatisticsDto> getStatistics() {
        List<StatisticsDto> statistics = statisticsRepository.findAll().stream()
                .map(statisticsMapper::toDto)
                .collect(Collectors.toList());
        return statistics;
    }

    @Override
    public void updateStatistics(StatisticsUpdateDto statisticsUpdateDto) {
        Statistics findItem = statisticsRepository.findById(statisticsUpdateDto.getId()).orElseThrow();
        statisticsMapper.updateEntityFromDto(statisticsUpdateDto, findItem);
        statisticsRepository.save(findItem);
    }

    @Override
    public StatisticsUpdateDto findUpdatedStatistics(int id) {
        Statistics statistics = statisticsRepository.findById(id).orElseThrow();
        return statisticsMapper.toUpdateDto(statistics);
    }

    @Override
    public void removeStatistics(int id) {
        Statistics statistics = statisticsRepository.findById(id).orElseThrow();
        statisticsRepository.delete(statistics);
    }

    @Override
    public List<StatisticsHomeDto> getHomeStatistics() {
        List<StatisticsHomeDto> statistics = statisticsRepository.findAll().stream()
                .map(statisticsMapper::toHomeDto)
                .collect(Collectors.toList());
        return statistics;
    }
}
