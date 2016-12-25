package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.utils.UtilDraw;

import java.awt.*;

import static fr.ryukk.morpion.utils.Constants.*;

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

    public void paintComponent(Graphics2D g2d) {
        int gridSize = WINDOW_HEIGHT - 40;

        int x = 20 + this.x * (gridSize / 3) + 30;
        int y = 20 + this.y * (gridSize / 3) + 30;

        int size = (gridSize / 3) - 60;

        Game game = Morpion.game();

        if(hovered && game.getPlayerTurn().getPlayerType().equals(Player.PlayerType.HUMAN) && game.isPlaying()) {
            if(tileType.equals(TileType.NONE))
                g2d.setColor(HOVER_NONE_COLOR);
            else if(tileType.equals(Morpion.game().getPlayerTurn().getTileType()))
                g2d.setColor(HOVER_FRIEND_COLOR);
            else
                g2d.setColor(HOVER_ENNEMY_COLOR);

            g2d.fillRect( 20 + this.x * (gridSize / 3), 20 + this.y * (gridSize / 3), gridSize / 3, gridSize / 3);
        }

        switch(tileType) {
            case NONE:
                break;
            case X:
                g2d.setColor(X_COLOR);
                UtilDraw.drawCross(g2d, x, y, size);
                break;
            case O:
                g2d.setColor(O_COLOR);
                UtilDraw.drawCircle(g2d, x, y, size);
                break;
        }

    }

    public void update() {
        int gridSize = WINDOW_HEIGHT - 40;

        int minX = 20 + x * (gridSize / 3);
        int maxX = 20 + (x + 1) * (gridSize / 3);

        int minY = 20 + y * (gridSize / 3);
        int maxY = 20 + (y + 1) * (gridSize / 3);

        if(minX < mX && mX <= maxX && minY < mY && mY <= maxY)
            game.getGrid()[x][y].setHovered(true);
        else
            game.getGrid()[x][y].setHovered(false);
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
