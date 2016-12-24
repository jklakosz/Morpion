package fr.ryukk.morpion;

import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;

/**
 *  @author ryukk
 *  @version 1.0
 *  @see https://github.com/ryukk01/Morpion
 *
 *  Under:
 *  ______________________________
 *  \ GNU GENERAL PUBLIC LICENSE /
 *   \ Version 3, 29 June 2007  /
 *    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
 */

public final class Morpion {

    private static Morpion instance;

    private static Window window;
    private static Game game;

    private void start() {
        Player[] players = new Player[2];

        players[0] = new HumanPlayer("George Sand", Tile.TileType.O);
        players[1] = new HumanPlayer("Thierry Micheaux", Tile.TileType.X);

        window = new Window();
        game = new Game(players);

        game.view();
        game.start();
    }

    public static void main(String[] args) {
        instance = new Morpion();
        instance.start();
    }

    public static Morpion instance() { return instance; }

    public static Window window() { return window; }
    public static Game game() { return game; }

}
