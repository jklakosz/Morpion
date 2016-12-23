package fr.ryukk.morpion;

import fr.ryukk.morpion.game.GamePanel;
import fr.ryukk.morpion.utils.Constants;

import javax.swing.*;
import java.awt.*;

public final class Window extends JFrame {

    public Window() {
        super("Morpion");

        Dimension size = new Dimension(Constants.WINDOW_WIDTH - 10, Constants.WINDOW_HEIGHT - 10);

        setContentPane(new GamePanel());
        getContentPane().setPreferredSize(size);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setVisible(true);
    }

}
