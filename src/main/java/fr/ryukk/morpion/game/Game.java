package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;

public final class Game {

    private Tile[][] grid;

    private volatile boolean playerTurn;
    private Tile.TileType playerType;

    public Game() {
        grid = new Tile[3][3];

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                grid[x][y] = new Tile(x, y);

        playerTurn = true;
        playerType = Tile.TileType.X;
    }

    public void start() {
        boolean win = false;

        while(!win) {
            if (!playerTurn) {
                checkWin();
                iaTurn();
                checkWin();
            }

        }

    }

    public void playerTurn(int x, int y) {
        grid[x][y].setType(playerType);
        Morpion.window().repaint();
        playerTurn = false;
    }

    public void iaTurn() {


        playerTurn = true;
    }

    public void checkWin() {



    }

    public Tile[][] getGrid() { return grid; }

    public boolean isPlayerTurn() { return playerTurn; }
    public Tile.TileType getPlayerType() { return playerType; }

}
