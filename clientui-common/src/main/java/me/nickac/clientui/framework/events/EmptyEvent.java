package me.nickac.clientui.framework.events;

public class EmptyEvent extends Event<Object> {

    private EmptyEvent() {
    }

    public static EmptyEvent createEvent() {
        return new EmptyEvent();
    }

    public void addHandler(EmptyEventHandler handler) {
        super.addHandler(handler);
    }

}
