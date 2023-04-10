package com.danilbel.cryptosystem.ciphers.symmetric.key;

public class QuadraticKey extends LinearKey {
    protected final long c;

    public QuadraticKey(long a, long b, long c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public char getKey() {
        return (char) (a * counter * counter + b * counter++ + c);
    }
}
