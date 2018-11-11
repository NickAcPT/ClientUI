package me.nickac.clientui.framework.controls.containers;

import me.nickac.clientui.framework.events.EmptyEvent;
import me.nickac.clientui.types.PointF;
import me.nickac.clientui.types.SizeF;

public class Window extends ContainerBase {
    private EmptyEvent windowOpenedEvent = EmptyEvent.createEvent();
    private EmptyEvent windowClosedEvent = EmptyEvent.createEvent();

    public Window() {
        super(PointF.EMPTY, SizeF.EMPTY);
    }

    public EmptyEvent getWindowClosedEvent() {
        return windowClosedEvent;
    }

    public EmptyEvent getWindowOpenedEvent() {
        return windowOpenedEvent;
    }

    @Override
    public void dispose() {
        super.dispose();
        windowOpenedEvent.dispose();
        windowClosedEvent.dispose();
    }
}