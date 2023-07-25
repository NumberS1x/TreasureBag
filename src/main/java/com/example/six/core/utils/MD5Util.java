package com.example.six.core.utils;

import java.security.MessageDigest;

public class MD5Util {
    /**
     * 简单MD5
     * @param str
     * @return
     */
    public static String MD5(String str) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        }catch(Exception e) {
            return null;
        }
    }
}
