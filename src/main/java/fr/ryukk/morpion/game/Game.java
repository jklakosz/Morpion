package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.utils.Serie;

public final class Game {

    private Tile[][] grid;

    private Player[] players;
    private byte playerTurn;

    private int winner;

    public Game(Player[] players) {
        grid = new Tile[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                grid[x][y] = new Tile(x, y);

        this.players = players;

        playerTurn = 0;
        winner = -1;
    }

    public void start() {
        while(winner == -1) {
            players[playerTurn].turn();

            Morpion.window().repaint();
            checkWin();

            playerTurn = playerTurn == 0 ? (byte) 1 : 0;
        }

        endGame();
    }

    public void endGame() {
        System.out.println("Fin du jeu lol");

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ignored) { }

        reset();
    }

    public void reset() {
        grid = new Tile[3][3];
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                grid[x][y] = new Tile(x, y);

        winner = -1;

        start();
    }

    private void checkWin() {
        Serie p1 = new Serie(players[0].getTileType());
        Serie p2 = new Serie(players[1].getTileType());

        if(p1.calc(grid, 3) > 0)
            winner = 0;
        else if(p2.calc(grid, 3) > 0)
            winner = 1;
        else {
            boolean finished = true;

            for(int x = 0; x < 3; x++)
                for(int y = 0; y < 3; y++)
                    if(grid[x][y].getTileType().equals(Tile.TileType.NONE)) finished = false;

            if(finished) winner = 2;
        }

    }

    public Tile[][] getGrid() { return grid; }
    public Player[] getPlayers() { return players; }

    public Player getPlayerTurn() { return players[playerTurn]; }

    public Player getPlayerByTileType(Tile.TileType tileType) {
        if(players[0].getTileType().equals(tileType))
            return players[0];
        else
            return players[1];
    }

}
