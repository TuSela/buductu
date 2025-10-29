package com.example.demo.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpingController {
    @GetMapping("/hello")
    String sayHello(){
        return "Hello World";
    }
}
