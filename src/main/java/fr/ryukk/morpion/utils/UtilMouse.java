package fr.ryukk.morpion.utils;

import fr.ryukk.morpion.Morpion;

import javax.swing.*;
import java.awt.*;

public class UtilMouse {

    public static Point getMousePoint() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(p, Morpion.window().getContentPane());
        return p;
    }

}
