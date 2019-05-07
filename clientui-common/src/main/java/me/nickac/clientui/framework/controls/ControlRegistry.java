package me.nickac.clientui.framework.controls;

public class ControlRegistry {
    private static ControlRegistry instance;

    public static ControlRegistry getInstance() {
        if (instance == null) instance = new ControlRegistry();
        return instance;
    }
}
