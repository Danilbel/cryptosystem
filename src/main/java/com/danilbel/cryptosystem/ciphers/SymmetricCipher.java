package com.danilbel.cryptosystem.ciphers;

import com.danilbel.cryptosystem.ciphers.key.Key;

public class SymmetricCipher implements Cipher {
    @Override
    public String encrypt(String message, Key key) {
        return cipher(message, key, true);
    }

    @Override
    public String decrypt(String message, Key key){
        return cipher(message, key, false);
    }

    protected String cipher(String message, Key key, boolean encrypt) {
        StringBuilder cripted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            cripted.append((char) (message.charAt(i) + (encrypt ? key.getKey() : -key.getKey())));
        }
        return cripted.toString();
    }
}
