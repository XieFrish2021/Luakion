package xyz.luakionmc.luakion.protocol.handshake.listener;

import xyz.luakionmc.luakion.protocol.PacketListener;
import xyz.luakionmc.luakion.protocol.handshake.packets.C00HandshakePacket;

public interface IHandshakeListener extends PacketListener {
    void handlerHandshake(C00HandshakePacket packet);
}
