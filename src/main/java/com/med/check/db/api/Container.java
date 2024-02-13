package com.med.check.db.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Container {
    @GetMapping()
    public String first(){
        return "Hello  Oroz";
    }
    @PostMapping("/post")
    public String post(){
        return "this is post";
    }
}
