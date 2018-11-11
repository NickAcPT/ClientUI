package me.nickac.clientui.framework.controls;

import me.nickac.clientui.types.PointF;
import me.nickac.clientui.types.SizeF;

import java.util.UUID;

public abstract class IControl {
    public abstract PointF getLocation();

    public abstract SizeF getSize();

    public abstract UUID getUniqueId();

    public abstract String getText();

    public abstract void setText(String text);

    public abstract Object getTag();

    public abstract void setTag(Object tag);

    public abstract void dispose();
}
