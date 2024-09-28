package xyz.luakionmc.luakion.protocol.types;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class VarString {
    public static String readString(ByteBuf is) {
        byte[] data = new byte[VarInt.read(is)];
        is.readBytes(data);
        return new String(data, StandardCharsets.UTF_8);
    }

    public static void writeString(ByteBuf os, String value) {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        VarInt.write(os, data.length);
        os.writeBytes(data);
    }
}
