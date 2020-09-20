package com.geekbrain.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping
    @ResponseBody
    public String firstRequest(){
        return "Hello";
    }
}
