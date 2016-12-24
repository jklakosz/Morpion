package fr.ryukk.morpion;

import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;


public final class Morpion {

    private static Morpion instance;

    private static Window window;
    private static Game game;

    private void start() {
        Player[] players = new Player[2];
        players[0] = new HumanPlayer(Tile.TileType.X);
        players[1] = new HumanPlayer(Tile.TileType.O);

        game = new Game(players);
        window = new Window();

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
