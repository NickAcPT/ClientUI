package me.nickac.clientui.networking;

public class PacketHandler {

    void handle(IPacket packet) {
        packet.run();
    }

}
