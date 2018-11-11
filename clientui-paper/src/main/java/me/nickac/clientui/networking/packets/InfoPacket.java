package me.nickac.clientui.networking.packets;

import io.netty.buffer.ByteBuf;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;
import org.bukkit.entity.Player;

import java.util.UUID;

import static me.nickac.clientui.ClientUIPaper.getClientsManager;

@PacketDescriptor(1)
public class InfoPacket implements IPacket {

    @Override
    public void fromBytes(ByteBuf buf) {
        String version = ByteBufUtils.readString(buf);
        System.out.printf("Client is using ClientUI version %s%n", version);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        //Server doesn't send info packet yet...
    }

    @Override
    public void execute(Player p) {

        UUID id = p.getUniqueId();
        if (!getClientsManager().isPlayerUsingTheMod(id)) {
            getClientsManager().addPlayer(id);
        } else {
            getClientsManager().markPlayerAsBlacklisted(id);
        }
    }
}
