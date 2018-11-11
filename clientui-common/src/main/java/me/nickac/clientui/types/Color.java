package me.nickac.clientui.types;

public class Color {
    private int alpha = 255;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    private Color(int red, int green, int blue) {
        this(255, red, green, blue);
    }

    private Color(int alpha, int red, int green, int blue) {
        this.alpha = Math.min(alpha, 255);
        this.red = Math.min(red, 255);
        this.green = Math.min(green, 255);
        this.blue = Math.min(blue, 255);
    }

    static Color fromArgb(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    private static Color fromArgb(int alpha, int red, int green, int blue) {
        return new Color(alpha, red, green, blue);
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = Math.min(alpha, 255);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = Math.min(red, 255);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = Math.min(green, 255);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = Math.min(blue, 255);
    }

    public long getHexColor() {
        String r = Integer.toHexString(getRed());
        r = r.length() < 2 ? "0" + r : r;
        String g = Integer.toHexString(getGreen());
        g = g.length() < 2 ? "0" + g : g;
        String b = Integer.toHexString(getBlue());
        b = b.length() < 2 ? "0" + b : b;
        String a = Integer.toHexString(getAlpha());
        a = a.length() < 2 ? "0" + a : a;

        return Long.parseLong(a + r + g + b, 16);
    }


}
