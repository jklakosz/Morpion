package fr.ryukk.morpion.game.listener;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInteractListener implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        if(Morpion.view() instanceof Game) {
            Game game = (Game) Morpion.view();

            if(game.getPlayerTurn().getPlayerType().equals(Player.PlayerType.HUMAN))
                for(int x = 0; x < 3; x++)
                    for(int y = 0; y < 3; y++)
                        if(game.getGrid()[x][y].isHovered())
                            game.getGrid()[x][y].setClicked(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(Morpion.view() instanceof Game) {
            Game game = (Game) Morpion.view();

            if(game.getPlayerTurn().getPlayerType().equals(Player.PlayerType.HUMAN))
                for(int x = 0; x < 3; x++)
                    for(int y = 0; y < 3; y++) {
                        if(game.getGrid()[x][y].isHovered() && game.getGrid()[x][y].isClicked())
                            if(game.getGrid()[x][y].getTileType().equals(Tile.TileType.NONE))
                                ((HumanPlayer) game.getPlayerTurn()).playerClick(x, y);

                        game.getGrid()[x][y].setClicked(false);
                    }

        }

    }

    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }

}
