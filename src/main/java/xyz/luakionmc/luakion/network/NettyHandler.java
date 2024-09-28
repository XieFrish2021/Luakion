package xyz.luakionmc.luakion.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import xyz.luakionmc.luakion.protocol.*;
import xyz.luakionmc.luakion.protocol.handshake.listener.HandshakeListenerImpl;
import xyz.luakionmc.luakion.protocol.login.data.LoginDisconnectData;
import xyz.luakionmc.luakion.protocol.login.packets.S00LoginDisconnectPacket;
import xyz.luakionmc.luakion.protocol.types.VarInt;

import java.util.UUID;

public class NettyHandler extends ChannelInboundHandlerAdapter {
    public static final AttributeKey<EnumConnectionStatus> status = AttributeKey.valueOf("status");
    public static final AttributeKey<PacketListener> listener = AttributeKey.valueOf("listener");
    public static final AttributeKey<Integer> protocol = AttributeKey.valueOf("protocol");
    public static final AttributeKey<String> username = AttributeKey.valueOf("username");
    public static final AttributeKey<UUID> uuid = AttributeKey.valueOf("uuid");

    public Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.channel = ctx.channel();
        channel.attr(NettyHandler.status).set(EnumConnectionStatus.HANDSHAKE);
        channel.attr(NettyHandler.listener).set(new HandshakeListenerImpl(this));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        IMinecraftBuffer buffer = new MinecraftBuffer((ByteBuf) msg);
        EnumConnectionStatus status = ctx.channel().attr(NettyHandler.status).get();
        PacketListener listener = ctx.channel().attr(NettyHandler.listener).get();

        buffer.readInt();
        int id = buffer.readInt();

        Class<? extends Packet<?>> packetClass = EnumConnectionStatus.getPacketById(status, id);
        if (packetClass != null) {
            Packet<?> packet = packetClass.getConstructor().newInstance();

            packet.read(buffer);
            handleListener(packet, listener);
        }

        buffer.readerIndex(0);
        ctx.pipeline().replace(this, "networkHandler", new NettyHandler());
        super.channelRead(ctx, msg);
    }

    @SuppressWarnings("unchecked")
    public <T extends PacketListener> void handleListener(Packet<T> packet, PacketListener listener) {
        try {
            packet.handle((T) listener);
        } catch (ClassCastException ignored) {}
    }

    public void disconnect(String reason) {
        EnumConnectionStatus status = this.channel.attr(NettyHandler.status).get();
        if (status == EnumConnectionStatus.LOGIN) {
            sendPacket(new S00LoginDisconnectPacket(new LoginDisconnectData(reason)));
        }

        this.channel.disconnect();
    }

    public void disconnect() {
        disconnect("Unknown reason.");
    }

    public void sendPacket(Packet<?> packet) {
        this.channel.writeAndFlush(packet);
    }

    public void setProtocol(int protocol) {
        channel.attr(NettyHandler.protocol).set(protocol);
    }

    public void setListener(PacketListener listener) {
        channel.attr(NettyHandler.listener).set(listener);
    }

    public void setStatus(EnumConnectionStatus status) {
        channel.attr(NettyHandler.status).set(status);
    }

    public void setUsername(String username) {
        channel.attr(NettyHandler.username).set(username);
    }

    public void setUuid(UUID uuid) {
        channel.attr(NettyHandler.uuid).set(uuid);
    }

    public int getProtocol() {
        return channel.attr(NettyHandler.protocol).get();
    }
}
