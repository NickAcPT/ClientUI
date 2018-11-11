package me.nickac.clientui.networking.packets;

import io.netty.buffer.ByteBuf;
import me.nickac.clientui.ClientUIForge;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;

@PacketDescriptor(1)
public class InfoPacket implements IPacket {
    @Override
    public void fromBytes(ByteBuf buf) {
        //Not needed since it won't be used on a forge server
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeString(buf, ClientUIForge.VERSION);
    }
}
