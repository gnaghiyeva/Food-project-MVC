package org.example.food.service.impl;

import org.example.food.dtos.categorydtos.CategoryDto;
import org.example.food.dtos.orderdtos.OrderCreateDto;
import org.example.food.dtos.orderdtos.OrderDto;
import org.example.food.mapper.OrderMapper;
import org.example.food.model.Category;
import org.example.food.model.Order;
import org.example.food.model.OrderStatus;
import org.example.food.repository.OrderRepository;
import org.example.food.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orders = orderRepository.findAll().stream()
                .map(order -> {
                    OrderDto dto = orderMapper.toDto(order);
                    dto.setStatus(order.getStatus());   // ehtiyat üçün əl ilə təyin et
                    return dto;
                })
                .collect(Collectors.toList());
        return orders;
    }

    @Override
    public void removeOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
    }

    @Override
    public void confirm(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(order);
    }

    @Override
    public void updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

}
