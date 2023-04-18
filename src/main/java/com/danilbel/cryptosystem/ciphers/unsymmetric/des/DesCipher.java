package com.danilbel.cryptosystem.ciphers.unsymmetric.des;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;

public class DesCipher {
    private static final String ENCRYPTION_ALGORITHM = "DES";
    private static final int IV_SIZE = 8;

    private final Base64.Encoder base64Encoder = Base64.getEncoder();
    private final Base64.Decoder base64Decoder = Base64.getDecoder();

    public String encrypt(String message, String key, DesMode mode) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKeyBytes(key), ENCRYPTION_ALGORITHM);
        Cipher cipher = Cipher.getInstance(mode.getInstance());
        IvParameterSpec ivParameterSpec = null;
        if (mode != DesMode.ECB) {
            byte[] iv = new byte[IV_SIZE];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(iv);
            ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        }
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(messageBytes);
        if (mode != DesMode.ECB) {
            return base64Encoder.encodeToString(encryptedBytes) + "::" + base64Encoder.encodeToString(ivParameterSpec.getIV());
        } else {
            return base64Encoder.encodeToString(encryptedBytes);
        }
    }

    public String decrypt(String message, String key, DesMode mode) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKeyBytes(key), ENCRYPTION_ALGORITHM);
        Cipher cipher = Cipher.getInstance(mode.getInstance());
        if (mode != DesMode.ECB) {
            String[] parts = message.split("::");
            byte[] iv = base64Decoder.decode(parts[1]);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            message = parts[0];
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }
        byte[] messageBytes = base64Decoder.decode(message);
        byte[] decryptedBytes = cipher.doFinal(messageBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private byte[] getKeyBytes(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length != 8) {
            throw new IllegalArgumentException("Key length must be 8 bytes");
        }
        return keyBytes;
    }
}
