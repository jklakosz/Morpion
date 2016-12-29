package fr.ryukk.morpion;

import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.utils.View;

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
    private static View view;

    private static ScreenHandler screenHandler;

    private static boolean debug;

    private void start() {
        Player[] players = new Player[2];

        players[0] = new HumanPlayer("George Sand", Tile.TileType.O);
        players[1] = new HumanPlayer("Isaac Newton", Tile.TileType.X);

        window = new Window();
        view = new Game(players);

        screenHandler = new ScreenHandler();
        screenHandler.start();

        window.switchView(view);

        ((Game) view).start();
    }

    public static void main(String[] args) {
        instance = new Morpion();
        instance.start();
    }

    public static Morpion instance() { return instance; }

    public static Window window() { return window; }
    public static View view() { return view; }
    public static ScreenHandler screenHandler() { return screenHandler; }

    public static void toggleDebug() { debug = !debug; }
    public static boolean isDebugActive() { return debug; }

}
