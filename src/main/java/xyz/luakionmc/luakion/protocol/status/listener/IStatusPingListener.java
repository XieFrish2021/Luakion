package xyz.luakionmc.luakion.protocol.status.listener;

import xyz.luakionmc.luakion.protocol.PacketListener;
import xyz.luakionmc.luakion.protocol.status.packets.C01StatusPingPacket;

public interface IStatusPingListener extends PacketListener {
    void handleStatusPing(C01StatusPingPacket packet);
}
