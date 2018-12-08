package me.nickac.clientui;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import com.gilecode.yagson.com.google.gson.ExclusionStrategy;
import com.gilecode.yagson.com.google.gson.FieldAttributes;
import me.nickac.clientui.events.PlayerEventsHandler;
import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.managers.ClientsManager;
import me.nickac.clientui.managers.EventsRegistrationManager;
import me.nickac.clientui.managers.PluginMessageManager;
import me.nickac.clientui.networking.packets.EventNotifyPacket;
import me.nickac.clientui.networking.packets.InfoPacket;
import me.nickac.clientui.networking.packets.ShowWindowPacket;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;

public final class ClientUIPaper extends JavaPlugin {
    private static ClientUIPaper instance;
    private PluginMessageManager pluginMessageManager;
    private ClientsManager clientsManager;
    private YaGson gson;
    private EventsRegistrationManager eventsRegistrationManager;

    public static ClientUIPaper getInstance() {
        return instance;
    }

    public static PluginMessageManager getPluginMessageManager() {
        return instance.pluginMessageManager;
    }

    public static ClientsManager getClientsManager() {
        return instance.clientsManager;
    }

    public static EventsRegistrationManager getEventsRegistrationManager() {
        return instance.eventsRegistrationManager;
    }

    public static YaGson getGson() {
        return instance.gson;
    }

    @Override
    public void onEnable() {
        instance = this;
        gson = new YaGsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.hasModifier(Modifier.TRANSIENT);
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        }).addDeserializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.hasModifier(Modifier.TRANSIENT);
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        }).create();
        //gson = new YaGsonBuilder()/*.registerTypeAdapterFactory(RuntimeClassNameTypeAdapterFactory.of(Object.class)
        // )*/.create();

        pluginMessageManager = new PluginMessageManager();
        pluginMessageManager.register();
        clientsManager = new ClientsManager();
        eventsRegistrationManager = new EventsRegistrationManager();
        Event.setRegistrationHandler(eventsRegistrationManager);

        //Register packets
        pluginMessageManager.registerMessageHandler(InfoPacket.class);
        pluginMessageManager.registerMessageHandler(ShowWindowPacket.class);
        pluginMessageManager.registerMessageHandler(EventNotifyPacket.class);

        //Register events
        Bukkit.getPluginManager().registerEvents(new PlayerEventsHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        pluginMessageManager.unregister();
        instance = null;
    }
}
