package xyz.luakionmc.luakion.protocol.login.packets;

import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.login.listener.ILoginStartListener;

public class C00LoginStartPacket implements Packet<ILoginStartListener> {
    @Override
    public void read(IMinecraftBuffer buffer) {}

    @Override
    public void write(IMinecraftBuffer buffer) {
    }

    @Override
    public void handle(ILoginStartListener listener) {
        listener.handleLoginStart(this);
    }
}
