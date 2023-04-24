package com.danilbel.cryptosystem.ciphers.unsymmetric.knapsack;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class KnapsackCipher {
    public String encrypt(String message, BigInteger[] openKey) {
        byte[] messageByteArray = message.getBytes(StandardCharsets.UTF_8);
        StringBuilder messageBinaryString = new StringBuilder();
        for (byte b : messageByteArray) {
            messageBinaryString.append(
                    String.format("%8s", Integer.toBinaryString(b & 0xFF))
                            .replace(' ', '0')
            );
        }
        char[] messageBinaryCharArray = messageBinaryString.toString().toCharArray();
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < messageBinaryCharArray.length; i += openKey.length) {
            BigInteger sum = BigInteger.ZERO;
            for (int j = 0; j < openKey.length; j++)
                sum = sum.add(
                        BigInteger.valueOf(
                                Character.getNumericValue(messageBinaryCharArray[i + j])
                        ).multiply(openKey[j])
                );
            if (i == 0) encryptedMessage.append(sum);
            else encryptedMessage.append(", ").append(sum);
        }
        return encryptedMessage.toString();
    }

    public String decrypt(String encryptedMessage, BigInteger[] supercoiledSequence, BigInteger m, BigInteger t) {
        BigInteger tInverse = t.modInverse(m);
        String[] encryptedMessageArray = encryptedMessage.split(", ");
        StringBuilder decryptedBinaryMessage = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();
        for (String s : encryptedMessageArray) {
            BigInteger sum = new BigInteger(s);
            sum = sum.multiply(tInverse).mod(m);
            for (int i = supercoiledSequence.length - 1; i >= 0; i--) {
                if (sum.compareTo(supercoiledSequence[i]) >= 0) {
                    tempBuilder.append(1);
                    sum = sum.subtract(supercoiledSequence[i]);
                } else tempBuilder.append(0);
            }
            decryptedBinaryMessage.append(tempBuilder.reverse());
            tempBuilder.setLength(0);
        }
        String[] decryptedBinaryMessageArray = decryptedBinaryMessage.toString().split("(?<=\\G.{8})");
        byte[] decryptedByteMessageArray = new byte[decryptedBinaryMessageArray.length];
        for (int i = 0; i < decryptedBinaryMessageArray.length; i++)
            decryptedByteMessageArray[i] = (byte) Integer.parseInt(decryptedBinaryMessageArray[i], 2);
        return new String(decryptedByteMessageArray, StandardCharsets.UTF_8);
    }
}
