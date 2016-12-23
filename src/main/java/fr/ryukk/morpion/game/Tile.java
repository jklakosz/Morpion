package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.utils.Constants;

import java.awt.*;

public final class Tile {

    private int x, y;
    private TileType type;

    private boolean hovered;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;

        this.type = TileType.NONE;

        this.hovered = false;
    }

    public void paint(Graphics2D g2d) {
        int gridSize = Constants.WINDOW_HEIGHT - 40;

        int x = 20 + this.x * (gridSize / 3) + 30;
        int y = 20 + this.y * (gridSize / 3) + 30;

        int size = (gridSize / 3) - 60;

        if(hovered && Morpion.game().isPlayerTurn()) {
            if(type.equals(TileType.NONE))
                g2d.setColor(Constants.HOVER_NONE_COLOR);
            else if(type.equals(Morpion.game().getPlayerType()))
                g2d.setColor(Constants.HOVER_FRIEND_COLOR);
            else
                g2d.setColor(Constants.HOVER_ENNEMY_COLOR);

            g2d.fillRect(
                    20 + this.x * (gridSize / 3),
                    20 + this.y * (gridSize / 3),
                    gridSize / 3,
                    gridSize / 3);

            /*if(type.equals(TileType.NONE)) {
                g2d.setColor(Constants.HOVER_COLOR);
                g2d.drawLine(x, y, x + size, y + size);
                g2d.drawLine(x + size, y, x, y + size);
            }*/

        }

        switch(type) {
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

    public void setType(TileType type) { this.type = type; }
    public TileType getType() { return type; }

    public void setHovered(boolean hovered) { this.hovered = hovered; }
    public boolean isHovered() { return hovered; }

    public enum TileType {
        NONE,
        X,
        O
    }

}
