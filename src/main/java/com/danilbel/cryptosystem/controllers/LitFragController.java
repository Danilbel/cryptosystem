package com.danilbel.cryptosystem.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lit-frag")
public class LitFragController {

    @GetMapping("/")
    public String showCaesar(Model model) {
        return "lit-frag";
    }
}
