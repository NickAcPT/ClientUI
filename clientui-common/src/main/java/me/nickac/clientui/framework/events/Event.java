package me.nickac.clientui.framework.events;

import me.nickac.clientui.framework.events.registration.IEventRegistrationHandler;
import me.nickac.clientui.framework.events.registration.NoOpRegistrationHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event<T> {
    private transient static IEventRegistrationHandler registrationHandler;
    private UUID uuid;
    private transient List<EventHandler<T>> handlers = new ArrayList<>();
    private transient EventHandler<T> setValueMethod;

    protected Event() {
        this(null);
    }

    protected Event(EventHandler<T> setValue) {
        setValueMethod = setValue;
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

    public static <T> Event<T> createEvent(EventHandler<T> setValue) {
        return new Event<>(setValue);
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public void addHandler(EventHandler<T> handler) {
        handlers.add(handler);
    }

    public void invoke(T obj) {
        Event.getRegistrationHandler().raiseEvent(this, obj);
        invokeLocal(obj);
    }

    public void invokeLocal(T obj) {
        handlers.forEach(h -> h.handle(obj));
        if (setValueMethod != null) setValueMethod.handle(obj);
    }

    public void invoke() {
        invoke(null);
    }

    public void dispose() {
        getRegistrationHandler().unregister(this);
    }
}