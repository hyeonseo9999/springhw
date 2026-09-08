package com.likelion.springhw.greeting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/my-name")
    public String myName() {
        return "권현서";
    }
}