package com.danilbel.cryptosystem.ciphers.symmetric;

import com.danilbel.cryptosystem.ciphers.symmetric.key.SymmetricCipherKey;

public class XorCipher extends SymmetricCipherImpl {
    @Override
    protected String cipher(String message, SymmetricCipherKey key, boolean encrypt) {
        StringBuilder cripted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            cripted.append((char) (message.charAt(i) ^ key.getKey()));
        }
        return cripted.toString();
    }
}
