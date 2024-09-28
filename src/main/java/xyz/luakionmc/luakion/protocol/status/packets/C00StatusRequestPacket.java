package xyz.luakionmc.luakion.protocol.status.packets;

import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.status.listener.IStatusRequestListener;

public class C00StatusRequestPacket implements Packet<IStatusRequestListener> {
    @Override
    public void read(IMinecraftBuffer buffer) {
    }

    @Override
    public void write(IMinecraftBuffer buffer) {
    }

    @Override
    public void handle(IStatusRequestListener listener) {
        listener.handlerStatusRequest(this);
    }
}
