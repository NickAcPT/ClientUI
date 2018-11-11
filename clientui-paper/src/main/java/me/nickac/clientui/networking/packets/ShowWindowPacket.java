package me.nickac.clientui.networking.packets;

import io.netty.buffer.ByteBuf;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.framework.controls.containers.Window;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;
import org.bukkit.entity.Player;

import static me.nickac.clientui.ClientUIPaper.getClientsManager;
import static me.nickac.clientui.ClientUIPaper.getGson;

@PacketDescriptor(2)
public class ShowWindowPacket implements IPacket {
    private Window window;

    public ShowWindowPacket(Window window) {
        this.window = window;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (window == null) return;
        ByteBufUtils.writeString(buf, getGson().toJson(window));
    }

    @Override
    public void execute(Player p) {
        getClientsManager().markPlayerAsBlacklisted(p.getUniqueId());
    }
}
