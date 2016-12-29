package fr.ryukk.morpion.utils;

import fr.ryukk.morpion.Morpion;

import javax.swing.*;
import java.awt.*;

public abstract class View extends JPanel {

    public abstract void update();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        render(g2d);
        Morpion.window().render(g2d);

        g2d.dispose();
    }

    public abstract void render(Graphics2D g2d);

}
