package me.nickac.clientui.events;

import me.nickac.clientui.ClientUIPaper;
import me.nickac.clientui.framework.controls.Label;
import me.nickac.clientui.framework.controls.containers.Window;
import me.nickac.clientui.wm.WindowManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventsHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }

    private void removePlayer(Player player) {
        ClientUIPaper.getClientsManager().removePlayer(player.getUniqueId());
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equals("/testui")) {
            event.setCancelled(true);
            Window wnd = new Window();
            Label label = new Label("Hello world!");

            wnd.addControl(label);

            wnd.getWindowOpenedEvent().addHandler(() -> event.getPlayer().sendMessage("Window has been opened!"));
            wnd.getWindowClosedEvent().addHandler(() -> event.getPlayer().sendMessage("Window has been closed!"));

            WindowManager.getInstance().showWindow(event.getPlayer(), wnd);
        }
    }
}
