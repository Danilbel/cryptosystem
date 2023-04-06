package com.danilbel.cryptosystem.ciphers;

import com.danilbel.cryptosystem.ciphers.key.Key;

public class XorCipher extends SymmetricCipher {
    @Override
    protected String cipher(String message, Key key, boolean encrypt) {
        StringBuilder cripted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            cripted.append((char) (message.charAt(i) ^ key.getKey()));
        }
        return cripted.toString();
    }
}
