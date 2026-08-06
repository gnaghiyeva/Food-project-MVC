package org.example.food.controller;
import org.example.food.dtos.orderdtos.OrderCreateDto;
import org.example.food.dtos.orderdtos.OrderDto;
import org.example.food.model.OrderStatus;
import org.example.food.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/order")
    public String order(Model model) {
        List<OrderDto> orders = orderService.getOrders();
        orders.forEach(o ->
                System.out.println(o.getId() + " -> " + o.getStatus())
        );
        model.addAttribute("orders", orders);
        return "dashboard/order/order";
    }

    @GetMapping("/admin/order/confirm/{id}")
    public String confirm(@PathVariable Long id) {
        orderService.confirm(id);
        return "redirect:/admin/order";
    }

    @PostMapping("/order/create")
    public String addOrder(@ModelAttribute OrderCreateDto orderCreateDto){
        orderService.addOrder(orderCreateDto);
        return "redirect:/";
    }

    @GetMapping("/admin/order/remove/{id}")
    public String removeOrder(@ModelAttribute @PathVariable Long id){
        orderService.removeOrder(id);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        orderService.updateStatus(id, status);
        return "redirect:/admin/order";
    }


}
