package com.danilbel.cryptosystem.ciphers.unsymmetric.litfrag;

import java.util.ArrayList;
import java.util.List;

public class LitFragKey {
    private final List<List<Character>> key;

    public LitFragKey(String keyStr) {
        key = parseKey(keyStr);
    }

    private List<List<Character>> parseKey(String keyStr) {
        List<List<Character>> key = new ArrayList<>();
        String[] rows = keyStr.toLowerCase().split("(?=\r\n)");
        for (String row : rows) {
            ArrayList<Character> rowList = new ArrayList<>();
            for (char c : row.toCharArray()) {
                rowList.add(c);
            }
            key.add(rowList);
        }
        return key;
    }

    public boolean contains(char c) {
        for (List<Character> row : key) {
            if (row.contains(c)) {
                return true;
            }
        }
        return false;
    }

    public List<int[]> getKeyList(char c) {
        List<int[]> keyList = new ArrayList<>();
        for (int i = 0; i < key.size(); i++) {
            List<Character> row = key.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == c) {
                    keyList.add(new int[]{i, j});
                }
            }
        }
        return keyList;
    }

    public char getChar(int[] key) {
        if (key[0] < 0 || key[1] < 0 || key[0] >= this.key.size() || key[1] >= this.key.get(key[0]).size()) {
            return '?';
        }
        return this.key.get(key[0]).get(key[1]);
    }
}
