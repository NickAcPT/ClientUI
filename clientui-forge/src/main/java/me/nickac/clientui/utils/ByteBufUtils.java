package me.nickac.clientui.utils;

import io.netty.buffer.ByteBuf;

public class ByteBufUtils {

    public static void writeString(ByteBuf buf, String value) {
        byte[] bytes = StringByteUtils.stringToBytes(value);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    public static String readString(ByteBuf buf) {
        int size = buf.readInt();
        byte[] bytes = new byte[size];
        buf.readBytes(bytes);
        return StringByteUtils.stringFromBytes(bytes);
    }

}
