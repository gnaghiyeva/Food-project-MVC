package org.example.food.dtos.orderdtos;

import lombok.Data;
import org.example.food.model.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime orderTime;
    private Long people;
    private String message;

    private OrderStatus status;
}
