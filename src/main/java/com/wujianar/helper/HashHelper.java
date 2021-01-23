package com.wujianar.helper;

import java.security.MessageDigest;

public class HashHelper {
    private HashHelper() {
    }

    public static String sha256(String str) {
        try {
            MessageDigest h = MessageDigest.getInstance("sha-256");
            return byteToHexString(h.digest(str.getBytes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public static String byteToHexString(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            String hex = Integer.toHexString(b & 0XFF);
            builder.append(hex.length() == 1 ? "0" + hex : hex);
        }
        return builder.toString();
    }
}
