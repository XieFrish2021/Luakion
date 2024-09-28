package xyz.luakionmc.luakion.protocol.status.packets;

import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.status.data.StatusResponseData;
import xyz.luakionmc.luakion.protocol.status.listener.IStatusRequestListener;

public class S00StatusResponsePacket implements Packet<IStatusRequestListener> {
    private final StatusResponseData json;

    public S00StatusResponsePacket(StatusResponseData json) {
        this.json = json;
    }

    @Override
    public void read(IMinecraftBuffer buffer) {
    }

    @Override
    public void write(IMinecraftBuffer buffer) {
        buffer.writeInt(0x00);
        buffer.writeString(json.toString());
    }

    @Override
    public void handle(IStatusRequestListener listener) {
    }
}
