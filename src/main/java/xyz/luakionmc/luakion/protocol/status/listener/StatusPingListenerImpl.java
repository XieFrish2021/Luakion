package xyz.luakionmc.luakion.protocol.status.listener;

import xyz.luakionmc.luakion.network.NettyHandler;
import xyz.luakionmc.luakion.protocol.status.packets.C01StatusPingPacket;
import xyz.luakionmc.luakion.protocol.status.packets.S01StatusPongPacket;

public class StatusPingListenerImpl implements IStatusPingListener {
    private final NettyHandler handler;

    public StatusPingListenerImpl(NettyHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleStatusPing(C01StatusPingPacket packet) {
        handler.sendPacket(new S01StatusPongPacket());
        handler.disconnect();
    }
}
