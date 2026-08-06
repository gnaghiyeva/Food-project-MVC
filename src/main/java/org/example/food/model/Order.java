package org.example.food.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "reservation")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    @DateTimeFormat(pattern = "hh:mm a")
    private LocalTime orderTime;
    private int people;
    private String message;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
