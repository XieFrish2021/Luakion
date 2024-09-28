package xyz.luakionmc.luakion.protocol;

public interface Packet<L extends PacketListener> {
    void read(IMinecraftBuffer buffer);

    void write(IMinecraftBuffer buffer);

    void handle(L listener);
}
