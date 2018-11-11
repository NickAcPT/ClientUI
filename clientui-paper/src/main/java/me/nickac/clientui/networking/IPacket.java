package me.nickac.clientui.networking;

import io.netty.buffer.ByteBuf;
import org.bukkit.entity.Player;

public interface IPacket {
    void fromBytes(ByteBuf buf);

    void toBytes(ByteBuf buf);

    default void execute(Player p) {
    }
}