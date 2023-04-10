package com.danilbel.cryptosystem.ciphers.unsymmetric.litfrag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LitFragCipher {

    public String encrypt(String message, LitFragKey key) {
        char[] messageCharArr = message.toLowerCase().toCharArray();
        validateMessageAndKey(messageCharArr, key);
        StringBuilder encrypted = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < messageCharArr.length; i++) {
            List<int[]> keyList = key.getKeyList(messageCharArr[i]);
            int[] keyArr = keyList.get(random.nextInt(keyList.size()));
            if (i == 0)
                encrypted.append(keyArr[0]).append('/').append(keyArr[1]);
            else
                encrypted.append(',').append(keyArr[0]).append('/').append(keyArr[1]);
        }
        return encrypted.toString();
    }

    public String decrypt(String message, LitFragKey key) {
        try {
            String[] keyArr = message.split(",");
            List<int[]> keyList = new ArrayList<>();
            for (String s : keyArr) {
                String[] keyArr2 = s.split("/");
                keyList.add(new int[]{Integer.parseInt(keyArr2[0]), Integer.parseInt(keyArr2[1])});
            }
            StringBuilder decrypted = new StringBuilder();
            for (int[] keyArr2 : keyList) {
                decrypted.append(key.getChar(keyArr2));
            }
            return decrypted.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("Message is not valid");
        }
    }

    private void validateMessageAndKey(char[] message, LitFragKey key) {
        List<Character> charactersNonValidates = new ArrayList<>();
        for (char c : message) {
            if (!key.contains(c)) {
                if (!charactersNonValidates.contains(c)) {
                    charactersNonValidates.add(c);
                }
            }
        }
        if (!charactersNonValidates.isEmpty()) {
            throw new IllegalArgumentException("Message contains characters that are not in the key: " + charactersNonValidates);
        }
    }
}
