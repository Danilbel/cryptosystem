package com.danilbel.cryptosystem.ciphers.key;

public class NumberKey implements Key {
    protected final long a;

    public NumberKey(long a) {
        this.a = a;
    }

    @Override
    public char getKey() {
        return (char) a;
    }
}
