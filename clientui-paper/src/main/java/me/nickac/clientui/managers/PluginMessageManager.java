package me.nickac.clientui.managers;

import me.nickac.clientui.ClientUIPaper;
import me.nickac.clientui.framework.Constants;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.networking.pluginmessages.GeneralMessageHandler;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;
import java.util.HashMap;

public class PluginMessageManager {
    private HashMap<Byte, Class<? extends IPacket>> messageHandlers = new HashMap<>();
    private GeneralMessageHandler messageHandler;

    public GeneralMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void registerMessageHandler(Class<? extends IPacket> clazz) {
        byte number = getMessageNumberFromClass(clazz);

        if (number < 0)
            throw new RuntimeException("Packet with class " + clazz.getName() + " doesn't have a packet id!");

        messageHandlers.put(number, clazz);
    }

    @Nullable
    public IPacket getMessageHandlerForId(byte id) {
        Class<? extends IPacket> clazz = messageHandlers.getOrDefault(id, null);
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


    protected byte getMessageNumberFromClass(Class<? extends IPacket> clazz) {
        if (clazz.isAnnotationPresent(PacketDescriptor.class)) {
            return clazz.getAnnotation(PacketDescriptor.class).value();
        }
        return -1;
    }

    public void register() {
        messageHandler = new GeneralMessageHandler();
        Bukkit.getMessenger().registerIncomingPluginChannel(ClientUIPaper.getInstance(), Constants.PLUGIN_MESSAGE_NAME, messageHandler);
        Bukkit.getMessenger().registerOutgoingPluginChannel(ClientUIPaper.getInstance(), Constants.PLUGIN_MESSAGE_NAME);
    }

    public void unregister() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(ClientUIPaper.getInstance());
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(ClientUIPaper.getInstance());
    }
}
