package xyz.luakionmc.luakion.protocol.status.listener;

import xyz.luakionmc.luakion.protocol.PacketListener;
import xyz.luakionmc.luakion.protocol.status.packets.C00StatusRequestPacket;

public interface IStatusRequestListener extends PacketListener {
    void handlerStatusRequest(C00StatusRequestPacket packet);
}
