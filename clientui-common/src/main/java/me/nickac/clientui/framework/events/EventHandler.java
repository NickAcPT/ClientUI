package me.nickac.clientui.framework.events;

public interface EventHandler<TK> {
    void handle(TK obj);
}
