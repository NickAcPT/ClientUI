package me.nickac.clientui.managers;

import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.framework.events.registration.IEventRegistrationHandler;

import java.util.HashMap;
import java.util.UUID;

public class EventsRegistrationManager implements IEventRegistrationHandler {

    private HashMap<UUID, Event> registeredEvents = new HashMap<>();

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
    }

    public Event getEventById(UUID uuid) {
        return registeredEvents.getOrDefault(uuid, null);
    }

    public void raiseEvent(UUID event, Object object) {
        Event evnt = getEventById(event);
        if (evnt != null) {
            evnt.invoke(object);
        }
    }
}
