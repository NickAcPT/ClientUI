package me.nickac.clientui.framework.events;

import java.io.Serializable;

public interface EventHandler<TK> extends Serializable {
    void handle(TK obj);
}
