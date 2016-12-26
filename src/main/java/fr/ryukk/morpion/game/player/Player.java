package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.game.Tile;

public abstract class Player {

    protected String name;

    protected PlayerType playerType;
    protected Tile.TileType tileType;

    private int victory;

    public Player(String name, Tile.TileType tileType) {
        this.name = name;
        this.tileType = tileType;
    }

    public abstract void turn();

    public String getName() { return name; }

    public PlayerType getPlayerType() { return playerType; }
    public Tile.TileType getTileType() { return tileType; }

    public int getVictoryCount() { return victory; }
    public void addVictory() { victory++; }

    public enum PlayerType {
        HUMAN("Humain"),
        AI("IA"),
        REMOTE_HUMAN("Humain distant");

        private String name;

        PlayerType(String name) {
            this.name = name;
        }

        public String getName() { return name; }

    }

}
