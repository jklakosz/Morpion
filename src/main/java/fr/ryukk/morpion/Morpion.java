package fr.ryukk.morpion;

import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.utils.Constants;

public final class Morpion {

    private static Morpion instance;

    private static Window window;
    private static Game game;

    private void start() {
        window = new Window();
        game = new Game(Constants.GRID_SIZE);
    }

    public static void main(String[] args) {
        instance = new Morpion();
        instance.start();
    }

    public static Morpion instance() { return instance; }

    public static Window window() { return window; }
    public static Game game() { return game; }

}
