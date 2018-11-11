package me.nickac.clientui.wm;

import me.nickac.clientui.framework.controls.containers.Window;
import me.nickac.clientui.framework.events.EmptyEventHandler;
import me.nickac.clientui.networking.packets.ShowWindowPacket;
import org.bukkit.entity.Player;

import static me.nickac.clientui.ClientUIPaper.getClientsManager;

public class WindowManager {
    private static WindowManager instance;

    public static WindowManager getInstance() {
        return instance == null ? instance = new WindowManager() : instance;
    }

    public void showWindow(Player p, Window wnd) {
        wnd.getWindowClosedEvent().addHandler(windowClosedHandler(wnd));
        getClientsManager().sendPacketToPlayer(p, new ShowWindowPacket(wnd));
    }

    private EmptyEventHandler windowClosedHandler(Window wnd) {
        return () -> {

        };
    }
}
