package me.nickac.clientui.wm;

import me.nickac.clientui.framework.controls.containers.Window;
import net.minecraft.client.gui.GuiScreen;

public class ServerUI extends GuiScreen {
    private Window window;

    public ServerUI(Window window) {
        this.window = window;
        window.getWindowOpenedEvent().invoke();
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public void onGuiClosed() {
        window.getWindowClosedEvent().invoke();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
    }
}
