package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.symmetric.SymmetricCipher;
import com.danilbel.cryptosystem.ciphers.symmetric.SymmetricCipherImpl;
import com.danilbel.cryptosystem.ciphers.symmetric.key.SymmetricCipherKey;
import com.danilbel.cryptosystem.ciphers.symmetric.key.NumberKey;
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
        return crypt(text, numberKey, true, model);
    }

    @PostMapping("/decrypt-result")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key-number") String numberKey, Model model) {
        return crypt(text, numberKey, false, model);
    }

    private String crypt(String text, String key, boolean isEncrypt, Model model) {
        SymmetricCipher sc = new SymmetricCipherImpl();
        SymmetricCipherKey k = new NumberKey(Long.parseLong(key));
        String result = isEncrypt ? sc.encrypt(text, k) : sc.decrypt(text, k);
        model.addAttribute("result", result);
        return "ceasar";
    }
}
