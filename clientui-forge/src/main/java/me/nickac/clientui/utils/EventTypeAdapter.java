package me.nickac.clientui.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.nickac.clientui.framework.events.Event;

import java.io.IOException;

public class EventTypeAdapter extends TypeAdapter<Event> {

    private Gson gson = new Gson();

    @Override
    public void write(JsonWriter out, Event value) throws IOException {
        gson.toJson(gson.toJsonTree(value), out);
    }

    @Override
    public Event read(JsonReader in) throws IOException {
        return gson.fromJson(in, Event.class);
    }
}
