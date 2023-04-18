package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.unsymmetric.litfrag.LitFragCipher;
import com.danilbel.cryptosystem.ciphers.unsymmetric.litfrag.LitFragKey;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lit-frag")
public class LitFragController {

    @GetMapping("/")
    public String showLitFrag(Model model) {
        return "lit-frag";
    }

    @PostMapping("/encrypt-result")
    public String encrypt(@RequestParam("text") String text, @RequestParam("key-lit") String key, Model model) {
        return crypt(text, key, true, model);
    }

    @PostMapping("/decrypt-result")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key-lit") String key, Model model) {
        return crypt(text, key, false, model);
    }

    private String crypt(String text, String key, boolean isEncrypt, Model model) {
        model.addAttribute("key", key);
        LitFragCipher lfc = new LitFragCipher();
        LitFragKey k = new LitFragKey(key);
        try {
            String result = isEncrypt ? lfc.encrypt(text, k) : lfc.decrypt(text, k);
            model.addAttribute("result", result);
        } catch (IllegalArgumentException e) {
            model.addAttribute("result", e.getMessage());
        }
        return "lit-frag";
    }
}
