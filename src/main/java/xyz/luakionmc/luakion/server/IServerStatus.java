package xyz.luakionmc.luakion.server;

public interface IServerStatus {
    String getDescription();

    String getVersionName();

    int getMaxPlayer();

    int getOnlinePlayers();

    int getProtocolVersion();
}
