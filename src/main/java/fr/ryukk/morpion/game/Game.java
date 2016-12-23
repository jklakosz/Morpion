package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;

import java.awt.*;

public final class Game {

    private Tile[][] grid;

    private boolean isPlayerTurn;

    public Game(int size) {
        grid = new Tile[size][size];

        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++)
                grid[x][y] = new Tile(x, y);

        isPlayerTurn = true;
    }

    public void start() {
        boolean win = false;

        while(!win) {
            paint(Morpion.window().getGraphics());

            if (!isPlayerTurn)
                iaTurn();

            checkWin();
        }

    }

    public void iaTurn() {



    }

    public void checkWin() {



    }

    public void paint(Graphics g) {
        g.drawLine(5, 5, 300, 300);
        g.dispose();
    }

    public Tile[][] getGrid() { return grid; }

}
