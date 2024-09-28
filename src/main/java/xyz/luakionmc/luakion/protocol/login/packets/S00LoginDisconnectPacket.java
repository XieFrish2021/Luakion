package xyz.luakionmc.luakion.protocol.login.packets;

import xyz.luakionmc.luakion.protocol.IMinecraftBuffer;
import xyz.luakionmc.luakion.protocol.Packet;
import xyz.luakionmc.luakion.protocol.login.data.LoginDisconnectData;
import xyz.luakionmc.luakion.protocol.login.listener.ILoginStartListener;

public class S00LoginDisconnectPacket implements Packet<ILoginStartListener> {
    private final LoginDisconnectData json;

    public S00LoginDisconnectPacket(LoginDisconnectData json) {
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
    public void handle(ILoginStartListener listener) {
    }
}
