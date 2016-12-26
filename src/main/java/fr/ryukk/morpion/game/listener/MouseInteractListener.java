package fr.ryukk.morpion.game.listener;

import fr.ryukk.morpion.utils.Mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInteractListener implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        Mouse.singletone().setDown(true);
        System.out.println(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Mouse.singletone().setDown(false);
        System.out.println(false);
    }

    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }

}
