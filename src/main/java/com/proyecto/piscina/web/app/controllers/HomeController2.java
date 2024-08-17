package com.proyecto.piscina.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController2 {
    
    @GetMapping("/home")
    public String goHome() {
        return "Inicio2";
    }
}
