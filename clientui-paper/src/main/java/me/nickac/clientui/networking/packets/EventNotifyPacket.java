package me.nickac.clientui.networking.packets;

import io.netty.buffer.ByteBuf;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;

import java.util.UUID;

import static me.nickac.clientui.ClientUIPaper.getEventsRegistrationManager;
import static me.nickac.clientui.ClientUIPaper.getGson;

@PacketDescriptor(3)
public class EventNotifyPacket implements IPacket {
    @Override
    public void fromBytes(ByteBuf buf) {
        UUID eventId = uuidFromByteBuf(buf);
        boolean hasObject = buf.readBoolean();
        Object obj = null;
        if (hasObject) {
            String json = ByteBufUtils.readString(buf);
            obj = getGson().fromJson(json, Object.class);
        }
        getEventsRegistrationManager().raiseEvent(eventId, obj);
    }

    public UUID uuidFromByteBuf(ByteBuf buf) {
        long most = buf.readLong();
        long least = buf.readLong();
        return new UUID(most, least);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        //Nothing
    }
}
