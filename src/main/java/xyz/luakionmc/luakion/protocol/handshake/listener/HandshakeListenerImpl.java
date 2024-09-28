package xyz.luakionmc.luakion.protocol.handshake.listener;

import xyz.luakionmc.luakion.network.NettyHandler;
import xyz.luakionmc.luakion.protocol.EnumConnectionStatus;
import xyz.luakionmc.luakion.protocol.handshake.packets.C00HandshakePacket;
import xyz.luakionmc.luakion.protocol.login.listener.LoginStartListenerImpl;
import xyz.luakionmc.luakion.protocol.status.listener.StatusRequestListenerImpl;

public class HandshakeListenerImpl implements IHandshakeListener {
    private final NettyHandler handler;

    public HandshakeListenerImpl(NettyHandler handler) {
        this.handler = handler;
    }

    public void handlerHandshake(C00HandshakePacket packet) {
        switch (packet.status) {
            case STATUS -> {
                handler.setStatus(EnumConnectionStatus.STATUS);
                handler.setListener(new StatusRequestListenerImpl(this.handler));
            }

            case LOGIN -> {
                handler.setStatus(EnumConnectionStatus.LOGIN);
                handler.setListener(new LoginStartListenerImpl(this.handler));
            }

            default -> throw new RuntimeException("Bad status: " + packet.status);
        }
        handler.setProtocol(packet.protocol);
    }
}
