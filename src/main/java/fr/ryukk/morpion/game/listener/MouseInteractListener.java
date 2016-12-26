package fr.ryukk.morpion.game.listener;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInteractListener implements MouseListener, MouseMotionListener {

    @Override
    public void mousePressed(MouseEvent e) {
        if(Morpion.game().getPlayerTurn().getPlayerType().equals(Player.PlayerType.HUMAN))
            for(int x = 0; x < 3; x++)
                for(int y = 0; y < 3; y++)
                    if(Morpion.game().getGrid()[x][y].isHovered())
                        if(Morpion.game().getGrid()[x][y].getTileType().equals(Tile.TileType.NONE)) {
                            ((HumanPlayer) Morpion.game().getPlayerTurn()).playerClick(x, y);
                            System.out.println("CLICK");
                        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Morpion.game().updateTiles();
    }

    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseReleased(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }
    @Override public void mouseDragged(MouseEvent e) { }

}
