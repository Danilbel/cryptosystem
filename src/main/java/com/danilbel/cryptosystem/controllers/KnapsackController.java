package com.danilbel.cryptosystem.controllers;

import com.danilbel.cryptosystem.ciphers.unsymmetric.knapsack.KnapsackCipher;
import com.danilbel.cryptosystem.ciphers.unsymmetric.knapsack.KnapsackKeyGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@RequestMapping("/knapsack")
public class KnapsackController {

    @GetMapping("/generate/")
    public String showKnapsack(Model model) {
        model.addAttribute("mode", "generate");
        return "knapsack";
    }

    @GetMapping("/encrypt/")
    public String showEncrypt(Model model) {
        model.addAttribute("mode", "encrypt");
        return "knapsack";
    }

    @GetMapping("/decrypt/")
    public String showDecrypt(Model model) {
        model.addAttribute("mode", "decrypt");
        return "knapsack";
    }

    @PostMapping("/generate-result")
    public String generateKnapsackKey(String length, Model model) {
        try {
            KnapsackKeyGenerator knapsackKeyGenerator = new KnapsackKeyGenerator(Integer.parseInt(length));
            model.addAttribute("result", knapsackKeyGenerator);
        } catch (Exception e) {
            model.addAttribute("result", "Error: Invalid input");
        }
        return "knapsack";
    }

    @PostMapping("/encrypt-result")
    public String encrypt(@RequestParam("text") String text,
                          @RequestParam("key") String key,
                          Model model) {

        String[] keyArray = key.split(", ");
        BigInteger[] openKey = new BigInteger[keyArray.length];
        for (int i = 0; i < keyArray.length; i++) {
            openKey[i] = new BigInteger(keyArray[i]);
        }
        try {
            String result = new KnapsackCipher().encrypt(text, openKey);
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("result", "Error: Invalid input");
        }
        model.addAttribute("mode", "encrypt");
        return "knapsack";
    }

    @PostMapping("/decrypt-result")
    public String decrypt(@RequestParam("text") String text,
                          @RequestParam("key") String key,
                          @RequestParam("key-m") String m,
                          @RequestParam("key-t") String t,
                          Model model) {

        String[] keyArray = key.split(", ");
        BigInteger[] closedKey = new BigInteger[keyArray.length];
        for (int i = 0; i < keyArray.length; i++) {
            closedKey[i] = new BigInteger(keyArray[i]);
        }
        try {
            String result = new KnapsackCipher().decrypt(text, closedKey, new BigInteger(m), new BigInteger(t));
            model.addAttribute("result", result);
        } catch (Exception e) {
            model.addAttribute("result", "Error: Invalid input");
        }
        model.addAttribute("mode", "decrypt");
        return "knapsack";
    }
}
