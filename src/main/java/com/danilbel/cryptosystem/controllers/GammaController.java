package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.XorCipher;
import com.danilbel.cryptosystem.ciphers.key.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gamma")
public class GammaController {
    @GetMapping("/")
    public String showGamma(Model model) {
        return "gamma";
    }

    @PostMapping("/encrypt-result")
    public String encrypt(@RequestParam("text") String text, @RequestParam("seed") String seed, Model model) {
        return crypt(text, seed, model);
    }

    @PostMapping("/decrypt-result")
    public String decrypt(@RequestParam("text") String text, @RequestParam("seed") String seed, Model model) {
        return crypt(text, seed, model);
    }

    private String crypt(String text, String seed, Model model) {
        XorCipher xc = new XorCipher();
        Key key = new GammaKey(Long.parseLong(seed), text.length());
        String result = xc.encrypt(text, key);
        model.addAttribute("result", result);
        return "gamma";
    }
}