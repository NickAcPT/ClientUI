package me.nickac.clientui.events;

import me.nickac.clientui.utils.ModCoderPackUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkEventHandler {

    public static final NetworkEventHandler INSTANCE = new NetworkEventHandler();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void clientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        ModCoderPackUtils.addScheduledTask(() -> MinecraftForge.EVENT_BUS.register(EntityEventHandler.INSTANCE));
    }

}
