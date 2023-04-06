package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.SymmetricCipher;
import com.danilbel.cryptosystem.ciphers.key.Key;
import com.danilbel.cryptosystem.ciphers.key.NumberKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ceasar")
public class CeasarController {

    @GetMapping("/")
    public String showCaesar(Model model) {
        return "ceasar";
    }

    @PostMapping("/encrypt-result")
    public String encrypt(@RequestParam("text") String text, @RequestParam("key-number") String numberKey, Model model) {
        String encrypted = crypt(text, numberKey, true);
        model.addAttribute("result", encrypted);
        return "ceasar";
    }

    @PostMapping("/decrypt-result")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key-number") String numberKey, Model model) {
        String encrypted = crypt(text, numberKey, false);
        model.addAttribute("result", encrypted);
        return "ceasar";
    }

    private String crypt(String text, String key, boolean isEncrypt) {
        SymmetricCipher sc = new SymmetricCipher();
        Key k = new NumberKey(Long.parseLong(key));
        return isEncrypt ? sc.encrypt(text, k) : sc.decrypt(text, k);
    }
}
