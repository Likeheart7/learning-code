package com.chenx.collections;


import static com.chenx.collections.HTMLColor.*;

public class HTMLColorTest {
    static final int DISPLAY_SIZE = 20;

    public static void main(String[] args) {
        show(MAP, DISPLAY_SIZE);
        border();
        showInv(INVMAP, DISPLAY_SIZE);
        border();
        show(LIST, DISPLAY_SIZE);
        border();
        showrgb(RGBLIST, DISPLAY_SIZE);
    }
}
