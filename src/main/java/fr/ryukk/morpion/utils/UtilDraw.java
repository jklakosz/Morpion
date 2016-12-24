package fr.ryukk.morpion.utils;

import java.awt.*;

public class UtilDraw {

    public static void drawCross(Graphics2D g2d, int x, int y, int size) {
        g2d.drawLine(x, y, x + size, y + size);
        g2d.drawLine(x, y + size, x + size, y);
    }

    public static void drawCircle(Graphics2D g2d, int x, int y, int size) {
        g2d.drawOval(x, y, size, size);
    }

}
