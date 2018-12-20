package me.nickac.clientui.networking.packets;

import com.google.gson.JsonSyntaxException;
import io.netty.buffer.ByteBuf;
import me.nickac.clientui.ClientUIForge;
import me.nickac.clientui.framework.annotations.PacketDescriptor;
import me.nickac.clientui.framework.controls.containers.Window;
import me.nickac.clientui.networking.IPacket;
import me.nickac.clientui.utils.ByteBufUtils;
import me.nickac.clientui.utils.ModCoderPackUtils;

import static me.nickac.clientui.ClientUIForge.getGson;

@PacketDescriptor(2)
public class ShowWindowPacket implements IPacket {
    private Window window;

    @Override
    public void fromBytes(ByteBuf buf) {
        String json = ByteBufUtils.readString(buf);
        try {
            window = getGson().fromJson(json, Window.class);
        } catch (JsonSyntaxException e) {
            System.err.println("Unable to parse JSON sent by the server.");
            System.err.println("Input: " + json);
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeString(buf, getGson().toJson(window));
    }

    @Override
    public IPacket run() {
        if (window != null) {
            ModCoderPackUtils.addScheduledTask(() -> ClientUIForge.INSTANCE.getWindowManager().showWindow(window));
        }
        return null;
    }
}
