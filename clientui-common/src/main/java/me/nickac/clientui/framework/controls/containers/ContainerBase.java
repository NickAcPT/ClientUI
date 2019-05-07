package me.nickac.clientui.framework.controls.containers;

import me.nickac.clientui.framework.controls.ControlBase;
import me.nickac.clientui.framework.controls.IControl;
import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.types.PointF;
import me.nickac.clientui.types.SizeF;

import java.util.ArrayList;
import java.util.List;

public class ContainerBase extends ControlBase {
    private List<IControl> childControls = new ArrayList<>();
    private Event<IControl> childAddedEvent = Event.createEvent(this::addControlIfNotExists);
    private Event<IControl> childRemovedEvent = Event.createEvent(val -> childControls.remove(val));

    public ContainerBase(PointF location, SizeF size) {
        super(location, size);
    }

    public ContainerBase(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Event<IControl> getChildAddedEvent() {
        return childAddedEvent;
    }

    public Event<IControl> getChildRemovedEvent() {
        return childRemovedEvent;
    }

    public List<IControl> getChildControls() {
        return childControls;
    }

    public void addControl(IControl ctrl) {
        childAddedEvent.invoke(ctrl);
    }

    public void removeControl(IControl ctrl) {
        if (childControls.remove(ctrl)) {
            childRemovedEvent.invoke(ctrl);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        childAddedEvent.dispose();
        childRemovedEvent.dispose();
    }

    private void addControlIfNotExists(IControl val) {
        if (!childControls.contains(val))
            childControls.add(val);
    }
}