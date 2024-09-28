package xyz.luakionmc.luakion.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.luakionmc.luakion.network.INettyNetwork;
import xyz.luakionmc.luakion.network.NettyNetwork;

public class MinecraftServer implements IMinecraftServer {
    private static final Logger logger = LogManager.getLogger();
    private static IMinecraftServer server;

    private INettyNetwork nettyNetwork;

    public MinecraftServer() {
        server = this;
    }

    @Override
    public boolean initServer() {
        this.nettyNetwork = new NettyNetwork("127.0.0.1", 25565);

        return true;
    }

    @Override
    public void runServer() {
        logger.info("Running Server...");
        this.nettyNetwork.runServer();
    }

    @Override
    public void stopServer() {
        logger.info("Closing Server...");
        this.nettyNetwork.stopServer();
    }

    @Override
    public String getDescription() {
        return "A Minecraft Server.";
    }

    @Override
    public String getVersionName() {
        return "1.21";
    }

    @Override
    public int getMaxPlayer() {
        return 100;
    }

    @Override
    public int getOnlinePlayers() {
        return 0;
    }

    @Override
    public int getProtocolVersion() {
        return 767;
    }

    public static IMinecraftServer getServer() {
        return server;
    }
}
