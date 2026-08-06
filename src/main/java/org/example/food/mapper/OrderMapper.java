package org.example.food.mapper;

import org.example.food.dtos.orderdtos.OrderCreateDto;
import org.example.food.dtos.orderdtos.OrderDto;
import org.example.food.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderCreateDto orderCreateDto);
    OrderDto toDto(Order orders);
}
