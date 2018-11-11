package me.nickac.clientui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.nickac.clientui.events.EventDealerHandler;
import me.nickac.clientui.events.NetworkEventHandler;
import me.nickac.clientui.framework.Constants;
import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.networking.PacketHandler;
import me.nickac.clientui.networking.PacketManager;
import me.nickac.clientui.networking.packets.EventNotifyPacket;
import me.nickac.clientui.networking.packets.InfoPacket;
import me.nickac.clientui.networking.packets.ShowWindowPacket;
import me.nickac.clientui.utils.RuntimeClassNameTypeAdapterFactory;
import me.nickac.clientui.wm.WindowManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(
        modid = ClientUIForge.MOD_ID,
        name = ClientUIForge.MOD_NAME,
        version = ClientUIForge.VERSION
)
public class ClientUIForge {

    //region Fields
    public static final String VERSION = "1.0-SNAPSHOT";
    static final String MOD_ID = "clientui";
    static final String MOD_NAME = "ClientUI";
    @Mod.Instance(MOD_ID)
    public static ClientUIForge INSTANCE;
    private SimpleNetworkWrapper networkWrapper;
    private PacketHandler packetHandler;
    private PacketManager packetManager;
    private Gson gson;
    private WindowManager windowManager;
    private EventDealerHandler eventHandler;
    //endregion

    public static Gson getGson() {
        return INSTANCE.gson;
    }

    //region Network stuff
    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    public SimpleNetworkWrapper getNetworkWrapper() {
        return networkWrapper;
    }

    public PacketManager getPacketManager() {
        return packetManager;
    }

    //endregion

    //region Helper methods
    public WindowManager getWindowManager() {
        return windowManager;
    }

    public EventDealerHandler getEventHandler() {
        return eventHandler;
    }

    //endregion

    //region Forge Stuff
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        gson = new GsonBuilder()/*.registerTypeHierarchyAdapter(Event.class, new EventTypeAdapter())*/.registerTypeAdapterFactory(RuntimeClassNameTypeAdapterFactory.of(Object.class)).create();
        packetHandler = new PacketHandler();
        packetManager = new PacketManager();
        eventHandler = new EventDealerHandler();
        Event.setRegistrationHandler(eventHandler);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.PLUGIN_MESSAGE_NAME);
        MinecraftForge.EVENT_BUS.register(NetworkEventHandler.INSTANCE);
        packetManager.registerPacket(InfoPacket.class);
        packetManager.registerPacket(ShowWindowPacket.class);
        packetManager.registerPacket(EventNotifyPacket.class);
        windowManager = new WindowManager();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
    //endregion
}
