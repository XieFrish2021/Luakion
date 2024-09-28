package xyz.luakionmc.luakion;

import xyz.luakionmc.luakion.server.IMinecraftServer;
import xyz.luakionmc.luakion.server.MinecraftServer;

public class Main {
    public static void main(String[] args) {
        IMinecraftServer server = new MinecraftServer();

        // Start Server
        server.startServer();
    }
}
