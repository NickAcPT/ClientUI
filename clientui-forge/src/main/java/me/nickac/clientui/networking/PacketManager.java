package me.nickac.clientui.networking;

import me.nickac.clientui.ClientUIForge;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;
import java.util.HashMap;

public class PacketManager {
    private HashMap<Byte, Class<? extends IPacket>> packets = new HashMap<>();

    private static IPacket onMessage(IPacket message, MessageContext ctx) {
        return message.run();
    }

    public void registerPacket(Class<? extends IPacket> clazz) {
        byte number = getMessageNumberFromClass(clazz);

        if (number < 0)
            throw new RuntimeException("Packet with class " + clazz.getName() + " doesn't have a packet id!");

        packets.put(number, clazz);
        ClientUIForge.INSTANCE.getNetworkWrapper().registerMessage(
                PacketManager::onMessage, clazz, number, Side.CLIENT
        );
    }

    @Nullable
    public IPacket getMessageHandlerForId(byte id) {
        Class<? extends IPacket> clazz = packets.getOrDefault(id, null);
        if (clazz == null)
            return null;
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.printf("Unable to create a new instance of type for id %s:%n", id);
            e.printStackTrace();
        }
        return null;
    }

    private byte getMessageNumberFromClass(Class<? extends IPacket> clazz) {
        if (clazz.isAnnotationPresent(PacketDescriptor.class)) {
            return clazz.getAnnotation(PacketDescriptor.class).value();
        }
        return -1;
    }
}
