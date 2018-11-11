package me.nickac.clientui.framework.controls;

import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.types.PointF;
import me.nickac.clientui.types.SizeF;

import java.util.UUID;

public class ControlBase extends IControl {
    private UUID uuid;
    private PointF location;
    private SizeF size;
    private String text;
    private Object tag;

    //region Events
    private Event<PointF> locationChangedEvent = Event.createEvent();
    private Event<SizeF> sizeChangedEvent = Event.createEvent();
    //endregion

    protected ControlBase() {
        uuid = UUID.randomUUID();
    }

    public ControlBase(PointF location, SizeF size) {
        this();
        this.location = location;
        this.size = size;
    }

    public ControlBase(float x, float y, float width, float height) {
        this(new PointF(x, y), new SizeF(width, height));
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Object getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public void dispose() {
        locationChangedEvent.dispose();
        sizeChangedEvent.dispose();
    }

    @Override
    public PointF getLocation() {
        return location;
    }

    public void setLocation(PointF location) {
        this.location = location;
        locationChangedEvent.invoke(location);
    }

    @Override
    public SizeF getSize() {
        return size;
    }

    public void setSize(SizeF size) {
        this.size = size;
        sizeChangedEvent.invoke(size);
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    public Event<PointF> getLocationChangedEvent() {
        return locationChangedEvent;
    }

    public Event<SizeF> getSizeChangedEvent() {
        return sizeChangedEvent;
    }
}