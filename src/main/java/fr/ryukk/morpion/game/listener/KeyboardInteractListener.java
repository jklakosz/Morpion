package fr.ryukk.morpion.game.listener;

import fr.ryukk.morpion.Morpion;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardInteractListener implements KeyEventDispatcher {

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_F3:
                if(e.getID() == KeyEvent.KEY_RELEASED)
                    Morpion.toggleDebug();
                break;
        }

        return false;
    }

}
