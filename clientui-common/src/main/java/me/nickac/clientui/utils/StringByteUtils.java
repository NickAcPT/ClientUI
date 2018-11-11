package me.nickac.clientui.utils;

import java.nio.charset.StandardCharsets;

public class StringByteUtils {
    public static byte[] stringToBytes(String value) {
        return value.getBytes(StandardCharsets.UTF_16);
    }

    public static String stringFromBytes(byte[] value) {
        return new String(value, StandardCharsets.UTF_16);
    }
}
