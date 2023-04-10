package com.danilbel.cryptosystem.ciphers.symmetric.key;

public class SloganKey implements SymmetricCipherKey {
    private long counter = 0;
    private final String slogan;

    public SloganKey(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public char getKey() {
        if (counter >= slogan.length()) {
            counter = 0;
        }
        return slogan.charAt((int) counter++);
    }
}
