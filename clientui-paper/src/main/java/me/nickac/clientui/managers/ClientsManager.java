package me.nickac.clientui.managers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.nickac.clientui.ClientUIPaper;
import me.nickac.clientui.framework.Constants;
import me.nickac.clientui.networking.IPacket;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientsManager {
    private List<UUID> playersUsingMod = new ArrayList<>();
    private List<UUID> blacklistedPlayers = new ArrayList<>();

    public void markPlayerAsBlacklisted(UUID uuid) {
        ClientUIPaper.getInstance().getLogger().warning(String.format(Constants.MALFORMED_PACKET, uuid));
        blacklistedPlayers.add(uuid);
    }

    public boolean isBlacklisted(UUID uuid) {
        return blacklistedPlayers.contains(uuid);
    }

    public void addPlayer(UUID uuid) {
        playersUsingMod.add(uuid);
    }

    public void removePlayer(UUID uuid) {
        playersUsingMod.remove(uuid);
    }

    public boolean isPlayerUsingTheMod(UUID uuid) {
        return playersUsingMod.contains(uuid);
    }

    public void sendPacketToPlayer(Player p, IPacket packet) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(ClientUIPaper.getPluginMessageManager().getMessageNumberFromClass(packet.getClass()));
        packet.toBytes(buffer);
        p.sendPluginMessage(ClientUIPaper.getInstance(), Constants.PLUGIN_MESSAGE_NAME, buffer.array());
    }
}
