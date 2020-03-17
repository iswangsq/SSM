package com.chuyue.controller;

import com.chuyue.config.ModelTagConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {

    @Autowired
    private ModelTagConfig modelTagConfig;

    @GetMapping("/hello")
    public String hello() {
        System.out.println(modelTagConfig.getTag());
        return "hello";
    }


}
