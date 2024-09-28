package xyz.luakionmc.luakion.protocol.handshake.packets;

import xyz.luakionmc.luakion.protocol.EnumConnectionStatus;
import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.handshake.listener.IHandshakeListener;

public class C00HandshakePacket implements Packet<IHandshakeListener> {
    public int protocol, port;
    public String host;
    public EnumConnectionStatus status;

    @Override
    public void read(IMinecraftBuffer buffer) {
        protocol = buffer.readInt();
        host = buffer.readString();
        port = buffer.readUnsignedShort();
        status = buffer.readInt() == 1 ? EnumConnectionStatus.STATUS : EnumConnectionStatus.LOGIN;
    }

    @Override
    public void write(IMinecraftBuffer buffer) {
        buffer.writeInt(protocol);
        buffer.writeString(host);
        buffer.writeShort(port);
        buffer.writeInt(status == EnumConnectionStatus.STATUS ? 1 : 2);
    }

    @Override
    public void handle(IHandshakeListener listener) {
        listener.handlerHandshake(this);
    }
}
