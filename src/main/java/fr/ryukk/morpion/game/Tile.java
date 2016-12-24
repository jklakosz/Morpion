package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.utils.Constants;

import java.awt.*;

public final class Tile {

    private int x, y;
    private TileType tileType;

    private boolean hovered;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        this.tileType = TileType.NONE;

        this.hovered = false;
    }

    public void paint(Graphics2D g2d) {
        int gridSize = Constants.WINDOW_HEIGHT - 40;

        int x = 20 + this.x * (gridSize / 3) + 30;
        int y = 20 + this.y * (gridSize / 3) + 30;

        int size = (gridSize / 3) - 60;

        if(hovered && Morpion.game().getPlayerTurn() instanceof HumanPlayer) {
            if(tileType.equals(TileType.NONE))
                g2d.setColor(Constants.HOVER_NONE_COLOR);
            else if(tileType.equals(Morpion.game().getPlayerTurn().getTileType()))
                g2d.setColor(Constants.HOVER_FRIEND_COLOR);
            else
                g2d.setColor(Constants.HOVER_ENNEMY_COLOR);

            g2d.fillRect( 20 + this.x * (gridSize / 3), 20 + this.y * (gridSize / 3), gridSize / 3, gridSize / 3);
        }

        switch(tileType) {
            case NONE:
                break;
            case X:
                g2d.setColor(Constants.X_COLOR);
                g2d.drawLine(x, y, x + size, y + size);
                g2d.drawLine(x + size, y, x, y + size);
                break;
            case O:
                g2d.setColor(Constants.O_COLOR);
                g2d.drawOval(x, y, size, size);
                break;
        }

    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setTileType(TileType type) { this.tileType = type; }
    public TileType getTileType() { return tileType; }

    public void setHovered(boolean hovered) { this.hovered = hovered; }
    public boolean isHovered() { return hovered; }

    public enum TileType {
        NONE,
        X,
        O
    }

}
