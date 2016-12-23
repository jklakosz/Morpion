package fr.ryukk.morpion;

import fr.ryukk.morpion.utils.Constants;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class Window extends JFrame implements MouseListener {

    public Window() {
        super("Morpion");

        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseListener(this);

        setVisible(true);
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }

}
