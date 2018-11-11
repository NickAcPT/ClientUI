package me.nickac.clientui.events;

import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.framework.events.registration.IEventRegistrationHandler;
import me.nickac.clientui.networking.packets.EventNotifyPacket;
import me.nickac.clientui.utils.ModCoderPackUtils;

import java.util.HashMap;
import java.util.UUID;

public class EventDealerHandler implements IEventRegistrationHandler {
    private HashMap<UUID, Event> registeredEvents = new HashMap<>();

    private Event getEventById(UUID uuid) {
        return registeredEvents.getOrDefault(uuid, null);
    }

    public void raiseEvent(UUID uuid, Object object) {
        Event event = getEventById(uuid);
        if (event != null) {
            event.invokeLocal(object);
        }
    }

    @Override
    public void register(Event event) {
        registeredEvents.put(event.getUniqueId(), event);
    }

    @Override
    public void unregister(Event event) {
        registeredEvents.remove(event.getUniqueId());
    }

    @Override
    public void raiseEvent(Event event, Object obj) {
        ModCoderPackUtils.sendPacketToServer(new EventNotifyPacket(event, obj));
    }
}
