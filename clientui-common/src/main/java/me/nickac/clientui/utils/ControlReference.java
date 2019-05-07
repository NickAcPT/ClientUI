package me.nickac.clientui.utils;

import com.gilecode.yagson.ReadContext;
import com.gilecode.yagson.WriteContext;
import com.gilecode.yagson.com.google.gson.TypeAdapter;
import com.gilecode.yagson.com.google.gson.stream.JsonReader;
import com.gilecode.yagson.com.google.gson.stream.JsonWriter;
import me.nickac.clientui.framework.controls.ControlBase;

import java.io.IOException;

public class ControlReference extends TypeAdapter<ControlBase> {

    @Override
    public void write(JsonWriter jsonWriter, ControlBase iControl, WriteContext writeContext) throws IOException {
        jsonWriter.value(iControl.getUniqueId().toString());
    }

    @Override
    public ControlBase read(JsonReader jsonReader, ReadContext readContext) throws IOException {
        return null;
    }
}