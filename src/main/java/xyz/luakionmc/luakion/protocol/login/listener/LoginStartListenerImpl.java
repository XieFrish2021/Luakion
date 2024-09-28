package xyz.luakionmc.luakion.protocol.login.listener;

import xyz.luakionmc.luakion.network.NettyHandler;
import xyz.luakionmc.luakion.protocol.login.packets.C00LoginStartPacket;
import xyz.luakionmc.luakion.server.IServerStatus;
import xyz.luakionmc.luakion.server.MinecraftServer;

public class LoginStartListenerImpl implements ILoginStartListener {
    private final NettyHandler handler;

    public LoginStartListenerImpl(NettyHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleLoginStart(C00LoginStartPacket packet) {
        IServerStatus status = MinecraftServer.getServer();
        if (handler.getProtocol() != status.getProtocolVersion()) {
            handler.disconnect("Bad Protocol: " + handler.getProtocol());
        }

        handler.disconnect("你被骗了，这是假服务端:)");
    }
}
