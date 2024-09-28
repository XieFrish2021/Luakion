package xyz.luakionmc.luakion.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.UUID;

@SuppressWarnings("all")
public interface IMinecraftBuffer {
    void writeInt(int value);
    int readInt();

    void writeString(String value);
    String readString();

    void writeLong(long value);
    long readLong();

    int readUnsignedShort();
    void writeShort(int value);

    void writeUUID(UUID uuid);

    ByteBuf writeBytes(ByteBuf buf);
    ByteBuf readBytes(ByteBuf buf);

    ByteBuf capacity(int i);
    int maxCapacity();
    int capacity();

    void skip(int i);
    ByteBufAllocator alloc();
    int readerIndex();
    ByteBuf readerIndex(int i);
    int writerIndex();
    ByteBuf writerIndex(int i);
    int readableBytes();
    void clear();
    ByteBuf copy();

    ByteBuf asByteBuf();
}
