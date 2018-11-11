package me.nickac.clientui.types;

import java.util.Objects;

public class PointF {
    public static PointF EMPTY = new PointF(0, 0);
    private float x, y;

    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointF pointF = (PointF) o;
        return Float.compare(pointF.x, x) == 0 &&
                Float.compare(pointF.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isEmpty() {
        return equals(EMPTY);
    }
}