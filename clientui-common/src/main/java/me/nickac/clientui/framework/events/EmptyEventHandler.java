package me.nickac.clientui.framework.events;

public interface EmptyEventHandler extends EventHandler<Object> {

    void handle();

    @Override
    default void handle(Object obj) {
        handle();
    }
}
