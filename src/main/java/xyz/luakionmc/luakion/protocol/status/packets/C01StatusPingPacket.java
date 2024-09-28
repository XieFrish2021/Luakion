package xyz.luakionmc.luakion.protocol.status.packets;

import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.status.listener.IStatusPingListener;

public class C01StatusPingPacket implements Packet<IStatusPingListener> {
    @Override
    public void read(IMinecraftBuffer buffer) {
    }

    @Override
    public void write(IMinecraftBuffer buffer) {
    }

    @Override
    public void handle(IStatusPingListener listener) {
        listener.handleStatusPing(this);
    }
}
