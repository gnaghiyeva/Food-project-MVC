package org.example.food.mapper;

import org.example.food.dtos.statistics.StatisticsCreateDto;
import org.example.food.dtos.statistics.StatisticsDto;
import org.example.food.dtos.statistics.StatisticsHomeDto;
import org.example.food.dtos.statistics.StatisticsUpdateDto;
import org.example.food.model.Statistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {
    @Mapping(target = "id", ignore = true)
    Statistics toEntity(StatisticsCreateDto statisticsCreateDto);
    StatisticsDto toDto(Statistics statistics);
    StatisticsHomeDto toHomeDto(Statistics statistics);
    StatisticsUpdateDto toUpdateDto(Statistics statistics);

    void updateEntityFromDto(StatisticsUpdateDto statisticsUpdateDto, @MappingTarget Statistics statistics);
}
