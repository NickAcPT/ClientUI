package me.nickac.clientui.utils;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.lang.reflect.Modifier;

public class MiscUtils {
    public static YaGson createGsonInstance() {
        return new YaGsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).disableHtmlEscaping().create();
    }

    public static void runSql(String sql) {

    }
}