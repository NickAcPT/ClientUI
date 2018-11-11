package me.nickac.clientui.utils;

import me.nickac.clientui.framework.controls.IControl;
import me.nickac.clientui.framework.events.Event;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Collection;

import static me.nickac.clientui.ClientUIForge.INSTANCE;

public class EventRegistryHelper {

    public static void registerEventsRecursive(Object object) {
        Field[] fields = FieldUtils.getAllFields(object.getClass());

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null && !field.getType().isPrimitive()) {
                    if (Event.class.isAssignableFrom(field.getType())) {
                        Event event = (Event) value;
                        INSTANCE.getEventHandler().register(event);
                    } else if (Collection.class.isAssignableFrom(field.getType())) {
                        Collection collection = (Collection) value;
                        for (Object o : collection) {
                            registerEventsRecursive(o);
                        }
                    } else if (IControl.class.isAssignableFrom(field.getType())) {
                        registerEventsRecursive(value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
