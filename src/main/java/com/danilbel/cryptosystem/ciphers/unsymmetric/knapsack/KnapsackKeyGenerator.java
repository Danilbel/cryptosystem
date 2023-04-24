package com.danilbel.cryptosystem.ciphers.unsymmetric.knapsack;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class KnapsackKeyGenerator {
    private final int INTEGER_SIZE = 32;
    private final Random random = new Random();

    private final int N;
    private BigInteger[] supercoiledSequence;
    private BigInteger m;
    private BigInteger t;
    private BigInteger[] openKey;

    public int getN() {
        return N;
    }

    public BigInteger[] getSupercoiledSequence() {
        return supercoiledSequence;
    }

    public BigInteger getM() {
        return m;
    }

    public BigInteger getT() {
        return t;
    }

    public BigInteger[] getOpenKey() {
        return openKey;
    }

    public KnapsackKeyGenerator(int n) {
        N = n;
        generateSupercoiledSequence();
        generateM();
        generateMutuallySimple();
        generateOpenKey();
    }

    private void generateSupercoiledSequence() {
        supercoiledSequence = new BigInteger[N];
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < supercoiledSequence.length; i++) {
            supercoiledSequence[i] = new BigInteger(INTEGER_SIZE, random).add(sum);
            sum = sum.add(supercoiledSequence[i]);
        }
    }

    private void generateM() {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger value : supercoiledSequence) {
            sum = sum.add(value);
        }
        m = new BigInteger(INTEGER_SIZE, random).add(sum);
    }

    public void generateMutuallySimple() {
        BigInteger coprime = new BigInteger(INTEGER_SIZE, random).mod(m);
        while (!coprime.gcd(m).equals(BigInteger.ONE)) {
            coprime = coprime.add(BigInteger.ONE);
        }
        t = coprime;
    }

    public void generateOpenKey() {
        openKey = new BigInteger[N];
        for (int i = 0; i < openKey.length; i++) {
            openKey[i] = supercoiledSequence[i].multiply(t).mod(m);
        }
    }

    @Override
    public String toString() {
        return "Open key:\n" +
                Arrays.toString(openKey) +
                "\nClosed key:\n" +
                Arrays.toString(supercoiledSequence) +
                "\nm = " +
                m +
                "\nt = " +
                t;
    }
}
