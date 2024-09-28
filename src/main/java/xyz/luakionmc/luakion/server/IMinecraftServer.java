package xyz.luakionmc.luakion.server;

public interface IMinecraftServer extends IServerStatus {
    default void startServer() {
        addShutdownHook(Thread.ofVirtual().name("shutdown").unstarted(this::stopServer));
        if (this.initServer()) {
            runServer();
        } else {
            throw new RuntimeException("The Server initialization failed.");
        }
    }

    default boolean initServer() {
        return true;
    }

    default void addShutdownHook(Thread thread) {
        Runtime.getRuntime().addShutdownHook(thread);
    }

    void runServer();

    void stopServer();
}
