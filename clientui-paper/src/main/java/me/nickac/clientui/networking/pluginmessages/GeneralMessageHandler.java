package me.nickac.clientui.networking.pluginmessages;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.nickac.clientui.ClientUIPaper;
import me.nickac.clientui.networking.IPacket;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static me.nickac.clientui.ClientUIPaper.getClientsManager;

public class GeneralMessageHandler implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (getClientsManager().isBlacklisted(player.getUniqueId()))
            return;

        ByteBuf byteBuf = Unpooled.wrappedBuffer(message);

        //Read message id.
        byte id = byteBuf.readByte();

        IPacket messageHandler = ClientUIPaper.getPluginMessageManager().getMessageHandlerForId(id);

        assert messageHandler != null;
        messageHandler.fromBytes(byteBuf);

        messageHandler.execute(player);

        System.out.printf("Received packet with id %s%n", id);
    }
}
