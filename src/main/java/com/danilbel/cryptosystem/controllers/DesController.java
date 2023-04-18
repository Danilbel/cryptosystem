package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.unsymmetric.des.DesCipher;
import com.danilbel.cryptosystem.ciphers.unsymmetric.des.DesMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/des")
public class DesController {

    @GetMapping("/")
    public String showDes(Model model) {
        return "des";
    }

    @PostMapping("/encrypt-result")
    public String encrypt(@RequestParam("text") String text, @RequestParam("key") String key, @RequestParam("mode") String mode, Model model) {
        return crypt(text, key, mode, true, model);
    }

    @PostMapping("/decrypt-result")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key") String key, @RequestParam("mode") String mode, Model model) {
        return crypt(text, key, mode, false, model);
    }

    private static String crypt(String text, String key, String mode, boolean isEncrypt, Model model) {
        DesCipher dc = new DesCipher();
        try {
            String result = isEncrypt ? dc.encrypt(text, key, DesMode.valueOf(mode)) : dc.decrypt(text, key, DesMode.valueOf(mode));
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("result", "Error: " + e.getMessage());
        }
        return "des";
    }

}
