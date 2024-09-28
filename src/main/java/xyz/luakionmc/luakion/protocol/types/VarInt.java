package xyz.luakionmc.luakion.protocol.types;

import io.netty.buffer.ByteBuf;

@SuppressWarnings("all")
public class VarInt {
    private static final int[] VARINT_EXACT_BYTE_LENGTHS = new int[33];

    static {
        for (int i = 0; i <= 32; ++i) {
            VARINT_EXACT_BYTE_LENGTHS[i] = (int) Math.ceil((31d - (i - 1)) / 7d);
        }
        VARINT_EXACT_BYTE_LENGTHS[32] = 1;
    }

    public static int getByteSize(int i) {
        return VARINT_EXACT_BYTE_LENGTHS[Integer.numberOfLeadingZeros(i)];
    }

    public static int read(ByteBuf buf) {
        int i = 0;
        int j = 0;

        byte b;
        do {
            b = buf.readByte();
            i |= (b & 127) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((b & 128) == 128);

        return i;
    }

    public static void write(ByteBuf buf, int i) {
        if ((i & (0xFFFFFFFF << 7)) == 0) {
            buf.writeByte(i);
        } else if ((i & (0xFFFFFFFF << 14)) == 0) {
            int w = (i & 0x7F | 0x80) << 8 | (i >>> 7);
            buf.writeShort(w);
        } else {
            writes(buf, i);
        }
    }

    private static void writes(ByteBuf buf, int i) {
        while ((i & -128) != 0) {
            buf.writeByte(i & 127 | 128);
            i >>>= 7;
        }

        buf.writeByte(i);
    }
}
