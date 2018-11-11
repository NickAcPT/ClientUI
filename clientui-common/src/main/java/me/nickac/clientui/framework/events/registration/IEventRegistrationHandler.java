package me.nickac.clientui.framework.events.registration;

import me.nickac.clientui.framework.events.Event;

public interface IEventRegistrationHandler {

    void register(Event event);

    void unregister(Event event);

    void raiseEvent(Event event, Object obj);

}
