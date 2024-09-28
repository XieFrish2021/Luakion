package xyz.luakionmc.luakion.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import xyz.luakionmc.luakion.protocol.types.VarInt;
import xyz.luakionmc.luakion.protocol.types.VarString;

import java.util.UUID;

@SuppressWarnings("all")
public record MinecraftBuffer(ByteBuf buf) implements IMinecraftBuffer {
    @Override
    public void writeInt(int value) {
        VarInt.write(buf, value);
    }

    @Override
    public int readInt() {
        return VarInt.read(buf);
    }

    @Override
    public void writeString(String value) {
        VarString.writeString(buf, value);
    }

    @Override
    public String readString() {
        return VarString.readString(buf);
    }

    @Override
    public void writeLong(long value) {
        buf.writeLong(value);
    }

    @Override
    public long readLong() {
        return buf.readLong();
    }

    @Override
    public int readUnsignedShort() {
        return buf.readUnsignedShort();
    }

    @Override
    public void writeShort(int value) {
        buf.writeShort(value);
    }

    @Override
    public void writeUUID(UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    @Override
    public ByteBuf writeBytes(ByteBuf buf) {
        return this.buf.writeBytes(buf);
    }

    @Override
    public ByteBuf readBytes(ByteBuf buf) {
        return this.buf.readBytes(buf);
    }

    @Override
    public ByteBuf capacity(int i) {
        return buf.capacity(i);
    }

    @Override
    public int maxCapacity() {
        return buf.maxCapacity();
    }

    @Override
    public int capacity() {
        return buf.capacity();
    }

    @Override
    public void skip(int i) {
        buf.skipBytes(i);
    }

    @Override
    public ByteBufAllocator alloc() {
        return buf.alloc();
    }

    @Override
    public int readerIndex() {
        return buf.readerIndex();
    }

    @Override
    public ByteBuf readerIndex(int i) {
        return buf.readerIndex(i);
    }

    @Override
    public int writerIndex() {
        return buf.writerIndex();
    }

    @Override
    public ByteBuf writerIndex(int i) {
        return buf.readerIndex(i);
    }

    @Override
    public int readableBytes() {
        return buf.readableBytes();
    }

    @Override
    public void clear() {
        buf.clear();
    }

    @Override
    public ByteBuf copy() {
        return buf.copy();
    }

    @Override
    public ByteBuf asByteBuf() {
        return this.buf;
    }
}
