package me.nickac.clientui.framework.controls;

import me.nickac.clientui.framework.events.Event;
import me.nickac.clientui.types.Color;
import me.nickac.clientui.types.DefaultColors;
import me.nickac.clientui.types.PointF;
import me.nickac.clientui.types.SizeF;

public class Label extends ControlBase {
    private Color foregroundColor = DefaultColors.WHITE;
    private Event<Color> foregroundColorChangedEvent = Event.createEvent(val -> foregroundColor = val);

    public Label(String text) {
        setText(text);
    }

    public Label(String text, PointF location, SizeF size) {
        super(location, size);
        setText(text);
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        foregroundColorChangedEvent.invoke(foregroundColor);
    }

    @Override
    public void dispose() {
        super.dispose();
        foregroundColorChangedEvent.dispose();
    }
}
