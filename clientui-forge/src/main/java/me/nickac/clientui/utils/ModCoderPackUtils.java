package me.nickac.clientui.utils;

import me.nickac.clientui.ClientUIForge;
import me.nickac.clientui.networking.IPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ModCoderPackUtils {

    public static Minecraft getMinecraftInstance() {
        return Minecraft.getMinecraft();
    }

    public static void sendPacketToServer(IPacket packet) {
        ClientUIForge.INSTANCE.getNetworkWrapper().sendToServer(packet);
    }

    public static void addScheduledTask(Runnable runnableToSchedule) {
        getMinecraftInstance().addScheduledTask(runnableToSchedule);
    }

    public static void displayGuiScreen(GuiScreen screen) {
        addScheduledTask(() -> getMinecraftInstance().displayGuiScreen(screen));
    }
}
