package xyz.luakionmc.luakion.protocol.login.listener;

import xyz.luakionmc.luakion.protocol.PacketListener;
import xyz.luakionmc.luakion.protocol.login.packets.C00LoginStartPacket;

public interface ILoginStartListener extends PacketListener {
    void handleLoginStart(C00LoginStartPacket packet);
}
