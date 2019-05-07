package me.nickac.clientui.renderers.impl;

import me.nickac.clientui.framework.controls.Label;
import me.nickac.clientui.renderers.BaseRenderer;
import me.nickac.clientui.utils.ModCoderPackUtils;
import net.minecraft.client.gui.GuiScreen;

public class LabelRenderer extends BaseRenderer<Label> {
    @Override
    public void drawGuiElement(GuiScreen screen, Label control) {
        screen.drawString(ModCoderPackUtils.getMinecraftInstance().fontRenderer, control.getText(),
                (int) control.getLocation().getX(), (int) control.getLocation().getY(),
                (int) control.getForegroundColor().getHexColor());
    }
}
