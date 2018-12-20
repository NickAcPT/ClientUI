package me.nickac.clientui.renderers;

import me.nickac.clientui.framework.controls.ControlBase;
import net.minecraft.client.gui.GuiScreen;

public abstract class BaseRenderer<T extends ControlBase> {

    public BaseRenderer() {
    }

    public abstract void drawGuiElement(GuiScreen screen, T control);

}
