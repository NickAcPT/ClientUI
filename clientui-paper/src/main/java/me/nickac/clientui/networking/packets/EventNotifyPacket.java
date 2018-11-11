package me.nickac.clientui.networking.packets;

import com.google.gson.JsonSyntaxException;
import io.netty.buffer.ByteBuf;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;

import java.nio.ByteBuffer;
import java.util.UUID;

import static me.nickac.clientui.ClientUIPaper.getEventsRegistrationManager;
import static me.nickac.clientui.ClientUIPaper.getGson;

@PacketDescriptor(3)
public class EventNotifyPacket implements IPacket {
    private Event event;
    private Object obj;

    public EventNotifyPacket(Event event, Object obj) {
        this.event = event;
        this.obj = obj;
    }

    public EventNotifyPacket() {
    }

    private static byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        UUID eventId = uuidFromByteBuf(buf);
        boolean hasObject = buf.readBoolean();
        Object object = null;
        if (hasObject) {
            String className = ByteBufUtils.readString(buf);
            String json = ByteBufUtils.readString(buf);
            try {
                object = getGson().fromJson(json, Class.forName(className));
            } catch (JsonSyntaxException | ClassNotFoundException e) {
                System.err.println("Unable to update value of control sent by event.");
                System.err.printf("JSON: %s%n", json);
                e.printStackTrace();
            }
        }
        getEventsRegistrationManager().raiseEvent(eventId, object);
    }

    private UUID uuidFromByteBuf(ByteBuf buf) {
        long most = buf.readLong();
        long least = buf.readLong();
        return new UUID(most, least);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBytes(uuidToBytes(event.getUniqueId()));
        buf.writeBoolean(obj != null);
        if (obj != null) {
            ByteBufUtils.writeString(buf, obj.getClass().getName());
            ByteBufUtils.writeString(buf, getGson().toJson(obj));
        }
    }
}
