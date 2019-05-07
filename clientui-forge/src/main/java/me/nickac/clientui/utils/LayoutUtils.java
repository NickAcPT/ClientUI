package me.nickac.clientui.utils;

import me.nickac.clientui.framework.controls.ControlBase;
import me.nickac.clientui.framework.controls.IControl;
import me.nickac.clientui.types.PointF;

public class LayoutUtils {
    private static LayoutUtils instance;

    public static LayoutUtils getInstance() {
        if (instance == null) instance = new LayoutUtils();
        return instance;
    }


    public PointF getPositionWithParent(ControlBase control) {
        int x = 0, y = 0;
        IControl parent = control.getParent();

        return PointF.EMPTY;
    }


}
