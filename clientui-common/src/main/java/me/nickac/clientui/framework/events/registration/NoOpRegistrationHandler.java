package me.nickac.clientui.framework.events.registration;

import me.nickac.clientui.framework.events.Event;

public class NoOpRegistrationHandler implements IEventRegistrationHandler {
    @Override
    public void register(Event event) {

    }

    @Override
    public void unregister(Event event) {

    }

    @Override
    public void raiseEvent(Event event, Object obj) {

    }
}
