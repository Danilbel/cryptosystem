package com.danilbel.cryptosystem.ciphers.key;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GammaKey implements Key {
    private final List<Character> gamma;
    private int counter = 0;

    public GammaKey(long seed, int length) {
        gamma = new ArrayList<>();
        Random random = new Random(seed);
        for (int i = 0; i < length; i++) {
            gamma.add((char) random.nextInt(0, Character.MAX_VALUE / 2));
        }
    }

    public char getKey() {
        if (counter >= gamma.size()) {
            counter = 0;
        }
        return gamma.get(counter++);
    }
}
