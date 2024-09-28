package xyz.luakionmc.luakion.protocol.status.listener;

import xyz.luakionmc.luakion.network.NettyHandler;
import xyz.luakionmc.luakion.protocol.status.data.StatusResponseData;
import xyz.luakionmc.luakion.protocol.status.packets.C00StatusRequestPacket;
import xyz.luakionmc.luakion.protocol.status.packets.S00StatusResponsePacket;
import xyz.luakionmc.luakion.server.IServerStatus;
import xyz.luakionmc.luakion.server.MinecraftServer;

public class StatusRequestListenerImpl implements IStatusRequestListener {
    private final NettyHandler handler;

    public StatusRequestListenerImpl(NettyHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handlerStatusRequest(C00StatusRequestPacket packet) {
        IServerStatus status = MinecraftServer.getServer();
        handler.sendPacket(new S00StatusResponsePacket(new StatusResponseData(
                new StatusResponseData.Version(status.getVersionName(), status.getProtocolVersion()),
                new StatusResponseData.Players(status.getMaxPlayer(), status.getOnlinePlayers()),
                new StatusResponseData.Description(status.getDescription())
        )));

        handler.setListener(new StatusPingListenerImpl(this.handler));
    }
}
