package com.danilbel.cryptosystem.ciphers.symmetric.key;

public class NumberKey implements SymmetricCipherKey {
    protected final long a;

    public NumberKey(long a) {
        this.a = a;
    }

    @Override
    public char getKey() {
        return (char) a;
    }
}
