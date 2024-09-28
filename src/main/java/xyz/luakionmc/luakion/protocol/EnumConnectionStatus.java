package xyz.luakionmc.luakion.protocol;

import xyz.luakionmc.luakion.protocol.handshake.packets.C00HandshakePacket;
import xyz.luakionmc.luakion.protocol.login.packets.C00LoginStartPacket;
import xyz.luakionmc.luakion.protocol.status.packets.C01StatusPingPacket;
import xyz.luakionmc.luakion.protocol.status.packets.C00StatusRequestPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EnumConnectionStatus {
    HANDSHAKE(new ConcurrentHashMap<>() {
        {
            put(0x00, C00HandshakePacket.class);
        }
    }),
    STATUS(new ConcurrentHashMap<>() {
        {
            put(0x00, C00StatusRequestPacket.class);
            put(0x01, C01StatusPingPacket.class);
        }
    }),
    LOGIN(new ConcurrentHashMap<>() {
        {
            put(0x00, C00LoginStartPacket.class);
        }
    }),
    PLAY(new ConcurrentHashMap<>());

    final Map<Integer, Class<? extends Packet<?>>> PACKETS;

    EnumConnectionStatus(Map<Integer, Class<? extends Packet<?>>> PACKETS) {
        this.PACKETS = PACKETS;
    }

    private Map<Integer, Class<? extends Packet<?>>> getPACKETS() {
        return PACKETS;
    }

    public static Class<? extends Packet<?>> getPacketById(EnumConnectionStatus status, int id) {
        return status.getPACKETS().get(id);
    }
}
