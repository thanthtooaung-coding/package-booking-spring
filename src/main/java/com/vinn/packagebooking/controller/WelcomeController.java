package com.vinn.packagebooking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Package Booking System";
    }
}
