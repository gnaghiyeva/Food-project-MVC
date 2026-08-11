package org.example.food.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorPageController {

    @GetMapping("/error/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDenied() {
        return "error/403";
    }
}