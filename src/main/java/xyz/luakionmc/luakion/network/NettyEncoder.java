package xyz.luakionmc.luakion.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.MinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;

public class NettyEncoder extends MessageToByteEncoder<Packet<?>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, ByteBuf byteBuf) throws Exception {
        IMinecraftBuffer buffer = new MinecraftBuffer(Unpooled.buffer(1024));
        packet.write(buffer);

        int length = buffer.writerIndex();
        new MinecraftBuffer(byteBuf).writeInt(length);
        byteBuf.writeBytes(buffer.asByteBuf());
    }
}
