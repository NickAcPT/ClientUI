package me.nickac.clientui.networking;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public interface IPacket extends IMessage {
    default IPacket run() {
        return null;
    }
}
