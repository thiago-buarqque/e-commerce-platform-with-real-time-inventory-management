package com.nozama.app.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
public class ProductController {

    @GetMapping()
    public String hello() {
        return "Hello Worldddd";
    }

}
