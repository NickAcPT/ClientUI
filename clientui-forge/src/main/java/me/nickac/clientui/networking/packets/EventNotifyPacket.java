package me.nickac.clientui.networking.packets;

import com.google.gson.JsonSyntaxException;
import io.netty.buffer.ByteBuf;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;

import java.nio.ByteBuffer;
import java.util.UUID;

import static me.nickac.clientui.ClientUIForge.INSTANCE;
import static me.nickac.clientui.ClientUIForge.getGson;

@PacketDescriptor(3)
public class EventNotifyPacket implements IPacket {
    private Event event;
    private Object obj;

    public EventNotifyPacket() {
    }

    public EventNotifyPacket(Event event, Object obj) {
        this.event = event;
        this.obj = obj;
    }

    private static byte[] uuidToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    private static UUID bytesToUUID(ByteBuf buf) {
        long most = buf.readLong();
        long least = buf.readLong();
        return new UUID(most, least);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        UUID uuid = bytesToUUID(buf);
        boolean hasValue = buf.readBoolean();
        Object objVal = null;
        if (hasValue) {
            String className = ByteBufUtils.readString(buf);
            String json = ByteBufUtils.readString(buf);
            try {
                objVal = getGson().fromJson(json, Class.forName(className));
            } catch (JsonSyntaxException | ClassNotFoundException e) {
                System.err.println("Unable to update value of control sent by event.");
                System.err.printf("JSON: %s%n", json);
                e.printStackTrace();
            }
            System.out.println(className);
            System.out.println(json);
        }
        INSTANCE.getEventHandler().raiseEvent(uuid, objVal);
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
