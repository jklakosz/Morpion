package fr.ryukk.morpion;

import javax.swing.*;
import java.awt.*;

import static fr.ryukk.morpion.utils.Constants.*;

public final class Window extends JFrame {

    public Window() {
        super("Morpion");

        Dimension size = new Dimension(WINDOW_WIDTH - 10, WINDOW_HEIGHT - 10);

        getContentPane().setPreferredSize(size);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setVisible(true);
    }

}
