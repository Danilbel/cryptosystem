package com.danilbel.cryptosystem.ciphers.key;

public class SloganKey implements Key {
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
