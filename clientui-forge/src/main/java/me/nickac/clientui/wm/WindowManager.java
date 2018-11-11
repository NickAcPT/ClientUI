package me.nickac.clientui.wm;

import me.nickac.clientui.framework.controls.containers.Window;
import me.nickac.clientui.utils.ModCoderPackUtils;

public class WindowManager {

    public void showWindow(Window win) {
        ServerUI screen = new ServerUI(win);
        ModCoderPackUtils.displayGuiScreen(screen);
    }

}
