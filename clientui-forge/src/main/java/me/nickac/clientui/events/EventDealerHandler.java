package me.nickac.clientui.events;

import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.framework.events.registration.IEventRegistrationHandler;
import me.nickac.clientui.networking.packets.EventNotifyPacket;
import me.nickac.clientui.utils.ModCoderPackUtils;

public class EventDealerHandler implements IEventRegistrationHandler {
    @Override
    public void register(Event event) {
    }

    @Override
    public void unregister(Event event) {
    }

    @Override
    public void raiseEvent(Event event, Object obj) {
        ModCoderPackUtils.sendPacketToServer(new EventNotifyPacket(event, obj));
    }
}
