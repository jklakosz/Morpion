package fr.ryukk.morpion;

import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.player.AIPlayer;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.gui.MainMenu;
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

    public Morpion() {
        window = new Window();
        screenHandler = new ScreenHandler();
    }

    private void start() {
        Player[] players = new Player[2];
        players[0] = new HumanPlayer("Tamer", Tile.TileType.O);
        players[1] = new AIPlayer(Tile.TileType.X);

        screenHandler.start();

        Game last = null;

        while(true) {
            if(last == null || (last != null && last.getWinner() == null))
                view = new Game(players);
            else
                view = new Game(players, (byte) (players[0] == last.getWinner() ? 1 : 0));

            last = (Game) view;

            window.switchView(view);

            ((Game) view).start();

            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

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
