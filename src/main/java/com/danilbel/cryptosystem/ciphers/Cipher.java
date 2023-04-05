package com.danilbel.cryptosystem.ciphers;

import com.danilbel.cryptosystem.ciphers.key.Key;

public interface Cipher {
    String encrypt(String message, Key key);
    String decrypt(String message, Key key);
}
