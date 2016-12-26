package fr.ryukk.morpion.utils;

import fr.ryukk.morpion.Morpion;

import javax.swing.*;
import java.awt.*;

public class Mouse {

    private static Mouse singletone;

    private boolean down;

    private Mouse() {
        down = false;
    }

    public boolean isDown() { return down; }
    public void setDown(boolean down) { this.down = down; }

    private Point getPoint() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, Morpion.window().getContentPane());
        return p;
    }

    public double getX() {
        return getPoint().getX();
    }

    public double getY() {
        return getPoint().getY();
    }

    public static Mouse singletone() {
        if(singletone == null)
            singletone = new Mouse();

        return singletone;
    }

}
