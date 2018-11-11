package me.nickac.clientui.events;

import me.nickac.clientui.networking.packets.InfoPacket;
import me.nickac.clientui.utils.ModCoderPackUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler {

    static final EntityEventHandler INSTANCE = new EntityEventHandler();

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        ModCoderPackUtils.sendPacketToServer(new InfoPacket());
        //After Joining, we need to clear the stuff because we are nice!
        MinecraftForge.EVENT_BUS.unregister(this);
    }


}
