package fr.ryukk.morpion.game;

import fr.ryukk.morpion.utils.Component;

import java.awt.*;

import static fr.ryukk.morpion.utils.Constants.GRID_COLOR;
import static fr.ryukk.morpion.utils.Constants.WINDOW_HEIGHT;

public class Grid implements Component {

    private Tile[][] grid;

    public Grid(int width, int height) {
        grid = new Tile[width][height];

        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
                grid[x][y] = new Tile(x, y);
    }

    public Tile get(int x, int y) { return grid[x][y]; }

    @Override
    public void render(Graphics2D g2d) {
        int gridSize = WINDOW_HEIGHT - 40;

        // Grid
        g2d.setColor(GRID_COLOR);
        g2d.drawRoundRect(20, 20, gridSize, gridSize, 5, 5);

        for(int i = 1; i < 3; i++) {
            int k = 20 + (gridSize / 3) * i;

            g2d.drawLine(k, 20, k, gridSize + 20);
            g2d.drawLine(20, k, gridSize + 20, k);
        }

    }

    @Override
    public void update() { }

}
