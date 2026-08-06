package org.example.food.service;

import org.example.food.dtos.orderdtos.OrderCreateDto;
import org.example.food.dtos.orderdtos.OrderDto;
import org.example.food.model.OrderStatus;

import java.util.List;

public interface OrderService {
    void addOrder(OrderCreateDto orderCreateDto);
    List<OrderDto> getOrders();
    void removeOrder(Long id);
    void confirm(Long id);
    void updateStatus(Long id, OrderStatus status);

}
