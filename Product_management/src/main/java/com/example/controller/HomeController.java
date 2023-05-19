package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/products")
    private String showProduct() {
        return "product/productList";
    }



    @GetMapping("/users")
    private String showUser() {
        return "user/userView";
    }
}
