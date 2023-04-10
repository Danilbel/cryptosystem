package com.danilbel.cryptosystem.ciphers.symmetric;

import com.danilbel.cryptosystem.ciphers.symmetric.key.SymmetricCipherKey;

public interface SymmetricCipher {
    String encrypt(String message, SymmetricCipherKey key);
    String decrypt(String message, SymmetricCipherKey key);
}
