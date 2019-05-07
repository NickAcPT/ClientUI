package me.nickac.clientui.utils;

import java.nio.charset.StandardCharsets;

class StringByteUtils {
    static byte[] stringToBytes(String value) {
        return value.getBytes(StandardCharsets.UTF_16);
    }

    static String stringFromBytes(byte[] value) {
        return new String(value, StandardCharsets.UTF_16);
    }
}
