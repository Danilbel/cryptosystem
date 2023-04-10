package com.danilbel.cryptosystem.ciphers.symmetric;

import com.danilbel.cryptosystem.ciphers.symmetric.key.SymmetricCipherKey;

public class SymmetricCipherImpl implements SymmetricCipher {
    @Override
    public String encrypt(String message, SymmetricCipherKey key) {
        return cipher(message, key, true);
    }

    @Override
    public String decrypt(String message, SymmetricCipherKey key){
        return cipher(message, key, false);
    }

    protected String cipher(String message, SymmetricCipherKey key, boolean encrypt) {
        StringBuilder cripted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            cripted.append((char) (message.charAt(i) + (encrypt ? key.getKey() : -key.getKey())));
        }
        return cripted.toString();
    }
}
