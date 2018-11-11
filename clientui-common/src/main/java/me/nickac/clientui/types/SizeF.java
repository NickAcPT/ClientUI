package me.nickac.clientui.types;

import java.util.Objects;

public class SizeF {
    public static SizeF EMPTY = new SizeF(0, 0);
    private float width, height;

    public SizeF(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeF sizeF = (SizeF) o;
        return Float.compare(sizeF.width, width) == 0 &&
                Float.compare(sizeF.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    public boolean isEmpty() {
        return equals(EMPTY);
    }
}