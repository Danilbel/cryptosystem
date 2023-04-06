package com.danilbel.cryptosystem.ciphers.key;

public class LinearKey extends NumberKey {
    protected long counter = 0;
    protected final long b;

    public LinearKey(long a, long b) {
        super(a);
        this.b = b;
    }

    @Override
    public char getKey() {
        return (char) (a * counter++ + b);
    }
}
