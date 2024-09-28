package xyz.luakionmc.luakion.protocol.status.packets;

import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.status.listener.IStatusPingListener;

public class S01StatusPongPacket implements Packet<IStatusPingListener> {
    @Override
    public void read(IMinecraftBuffer buffer) {
    }

    @Override
    public void write(IMinecraftBuffer buffer) {
        buffer.writeInt(0x01);
        buffer.writeLong(System.currentTimeMillis());
    }

    @Override
    public void handle(IStatusPingListener listener) {
    }
}
