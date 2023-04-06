package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.SymmetricCipher;
import com.danilbel.cryptosystem.ciphers.key.Key;
import com.danilbel.cryptosystem.ciphers.key.LinearKey;
import com.danilbel.cryptosystem.ciphers.key.QuadraticKey;
import com.danilbel.cryptosystem.ciphers.key.SloganKey;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/tritemius")
public class TrithemiusController {
    @GetMapping("/")
    public String showTritemius(Model model) {
        return "tritemius";
    }

    @PostMapping("/encrypt-result-linear")
    public String encrypt(@RequestParam("text") String text, @RequestParam("key-a") String keyA, @RequestParam("key-b") String keyB, Model model) {
        return crypt(text, List.of(keyA, keyB), true, model);
    }

    @PostMapping("/decrypt-result-linear")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key-a") String keyA, @RequestParam("key-b") String keyB, Model model) {
        return crypt(text, List.of(keyA, keyB), false, model);
    }

    @PostMapping("/encrypt-result-quadratic")
    public String encrypt(@RequestParam("text") String text, @RequestParam("key-a") String keyA, @RequestParam("key-b") String keyB, @RequestParam("key-c") String keyC, Model model) {
        return crypt(text, List.of(keyA, keyB, keyC), true, model);
    }

    @PostMapping("/decrypt-result-quadratic")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key-a") String keyA, @RequestParam("key-b") String keyB, @RequestParam("key-c") String keyC, Model model) {
        return crypt(text, List.of(keyA, keyB, keyC), false, model);
    }

    @PostMapping("/encrypt-result-slogan")
    public String encrypt(@RequestParam("text") String text, @RequestParam("key-slogan") String slogan, Model model) {
        return crypt(text, List.of(slogan), true, model);
    }

    @PostMapping("/decrypt-result-slogan")
    public String decrypt(@RequestParam("text") String text, @RequestParam("key-slogan") String slogan, Model model) {
        return crypt(text, List.of(slogan), false, model);
    }

    private String crypt(String text, List<String> keys, boolean isEncrypt, Model model) {
        SymmetricCipher sc = new SymmetricCipher();
        Key key = switch (keys.size()) {
            case 2 -> new LinearKey(Long.parseLong(keys.get(0)), Long.parseLong(keys.get(1)));
            case 3 -> new QuadraticKey(Long.parseLong(keys.get(0)), Long.parseLong(keys.get(1)), Long.parseLong(keys.get(2)));
            case 1 -> new SloganKey(keys.get(0));
            default -> throw new IllegalStateException("Unexpected value: " + keys.size());
        };
        String result = isEncrypt ? sc.encrypt(text, key) : sc.decrypt(text, key);
        model.addAttribute("result", result);
        return "tritemius";
    }
}
