package me.nickac.clientui.networking.packets;

import io.netty.buffer.ByteBuf;
import me.nickac.clientui.ClientUIForge;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;

import java.nio.ByteBuffer;
import java.util.UUID;

@PacketDescriptor(3)
public class EventNotifyPacket implements IPacket {
    private Event event;
    private Object obj;

    public EventNotifyPacket(Event event, Object obj) {
        this.event = event;
        this.obj = obj;
    }

    public static byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        //Server doesn't send events
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBytes(uuidToBytes(event.getUniqueId()));
        buf.writeBoolean(obj != null);
        if (obj != null)
            ByteBufUtils.writeString(buf, ClientUIForge.getGson().toJson(obj));
    }

}
