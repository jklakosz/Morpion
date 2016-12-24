package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.game.Tile;

public abstract class Player {

    protected PlayerType playerType;
    protected Tile.TileType tileType;

    public Player(Tile.TileType tileType) {
        this.tileType = tileType;
    }

    public abstract void turn();

    public PlayerType getPlayerType() { return playerType; }
    public Tile.TileType getTileType() { return tileType; }

    public enum PlayerType {
        HUMAN,
        IA,
        REMOTE_HUMAN
    }

}
