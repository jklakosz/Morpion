package fr.ryukk.morpion;

import fr.ryukk.morpion.game.Game;


public final class Morpion {

    private static Morpion instance;

    private static Window window;
    private static Game game;

    private void start() {
        game = new Game();
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
