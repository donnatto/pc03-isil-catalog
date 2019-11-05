package me.donnatto.isilcatalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppWeb {

    @GetMapping(value = {"/", "/login"})
    private String index(){
        return "index";
    }

    @GetMapping("/menu")
    private String menu(){
        return "menu";
    }
}
