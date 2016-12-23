package fr.ryukk.morpion.game;

public final class Tile {

    private int x, y;
    private TileType type;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setType(TileType type) { this.type = type; }
    public TileType getType() { return type; }

    public enum TileType {
        NONE,
        X,
        O
    }

}
