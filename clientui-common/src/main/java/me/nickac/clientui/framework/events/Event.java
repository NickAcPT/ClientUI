package me.nickac.clientui.framework.events;

import me.nickac.clientui.framework.events.registration.IEventRegistrationHandler;
import me.nickac.clientui.framework.events.registration.NoOpRegistrationHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event<T> {
    private static IEventRegistrationHandler registrationHandler;
    private UUID uuid;
    private transient List<EventHandler<T>> handlers = new ArrayList<>();

    protected Event() {
        uuid = UUID.randomUUID();
        getRegistrationHandler().register(this);
    }

    private static IEventRegistrationHandler getRegistrationHandler() {
        return registrationHandler == null ? registrationHandler = new NoOpRegistrationHandler() : registrationHandler;
    }

    public static void setRegistrationHandler(IEventRegistrationHandler registrationHandler) {
        Event.registrationHandler = registrationHandler;
    }

    public static <T> Event<T> createEvent() {
        return new Event<>();
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public void addHandler(EventHandler<T> handler) {
        handlers.add(handler);
    }

    public void invoke(T obj) {
        Event.getRegistrationHandler().raiseEvent(this, obj);
        handlers.forEach(h -> h.handle(obj));
    }

    public void invoke() {
        invoke(null);
    }

    public void dispose() {
        getRegistrationHandler().unregister(this);
    }
}