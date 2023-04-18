package com.danilbel.cryptosystem.ciphers.unsymmetric.des;

public enum DesMode {
    ECB, CBC, CFB, OFB, CTR;

    private static final String PKCS5_PADDING = "DES/%s/PKCS5Padding";
    private static final String NO_PADDING = "DES/%s/NoPadding";

    public String getInstance() {
        if (this == DesMode.CTR) {
            return String.format(NO_PADDING, this);
        }
        return String.format(PKCS5_PADDING, this);
    }
}
