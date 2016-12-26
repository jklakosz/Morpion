package fr.ryukk.morpion.game.panel;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.Game;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.game.listener.MouseInteractListener;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.utils.UtilDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

import static fr.ryukk.morpion.utils.Constants.*;

public final class GamePanel extends JPanel {

    public GamePanel() {
        MouseInteractListener listener = new MouseInteractListener();

        addMouseListener(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        renderBackground(g2d);
        renderConfig(g2d);
        renderGrid(g2d);

        g2d.dispose();
    }

    private void renderBackground(Graphics2D g2d) {
        // Background
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void renderGrid(Graphics2D g2d) {
        Game game = Morpion.game();

        int gridSize = WINDOW_HEIGHT - 40;

        // Tiles
        g2d.setStroke(LINE_STROKE);

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                Morpion.game().getGrid()[x][y].paintComponent(g2d);

        // Grid
        g2d.setColor(GRID_COLOR);
        g2d.drawRoundRect(20, 20, gridSize, gridSize, 5, 5);

        for(int i = 1; i < 3; i++) {
            int k = 20 + (gridSize / 3) * i;

            g2d.drawLine(k, 20, k, gridSize + 20);
            g2d.drawLine(20, k, gridSize + 20, k);
        }

    }

    private void renderConfig(Graphics2D g2d) {
        Game game = Morpion.game();

        int baseX = WINDOW_WIDTH - WINDOW_HEIGHT;
        Player[] players = game.getPlayers();

        // Title
        g2d.setColor(TITLE_FONT_COLOR);
        g2d.setFont(TITLE_FONT);

        String title = "Morpion";

        g2d.drawString(title, WINDOW_HEIGHT + (baseX / 2) - (g2d.getFontMetrics(TITLE_FONT).stringWidth(title) / 2),
                70);

        // Players shapes, names & types
        for (int i = 0; i < 2; i++) {
            // Vars
            Player p = players[i];

            String name = p.getName();
            String type = p.getPlayerType().getName();

            g2d.setFont(NAME_FONT);
            int nameLength = g2d.getFontMetrics().stringWidth(name);

            g2d.setFont(TYPE_FONT);
            int typeLength = g2d.getFontMetrics().stringWidth(type);

            int playerTotalLength = PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET + nameLength + PLAYERS_NAME_TYPE_X_OFFSET
                    + typeLength;
            int posX = WINDOW_HEIGHT + (baseX / 2) - (playerTotalLength / 2);
            int relativeYOffset = i * NAME_FONT.getSize() * 2;

            // Names
            g2d.setFont(NAME_FONT);
            g2d.setColor(p.getTileType().equals(Tile.TileType.X) ? X_COLOR : O_COLOR);
            g2d.drawString(name, posX + PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET, PLAYERS_Y_OFFSET
                    + (NAME_FONT.getSize() / 2) - 2 + relativeYOffset);

            // Types
            g2d.setFont(TYPE_FONT);

            int padding = 2;

            g2d.setColor(p.getTileType().equals(Tile.TileType.X) ? X_TYPE_BACKGROUND_COLOR : O_TYPE_BACKGROUND_COLOR);
            g2d.fillRoundRect(posX + PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET + nameLength
                    + PLAYERS_NAME_TYPE_X_OFFSET - padding, PLAYERS_Y_OFFSET + (NAME_FONT.getSize() / 2) - 2
                    + relativeYOffset - ((NAME_FONT.getSize() - TYPE_FONT.getSize()) / 3) - TYPE_FONT.getSize()
                    - padding, typeLength + 1 + (2 * padding), TYPE_FONT.getSize() + 3 + (2 * padding), 5, 5);

            g2d.setColor(TYPE_FONT_COLOR);
            g2d.drawString(type, posX + PLAYERS_SHAPE_SIZE + PLAYERS_SHAPE_NAME_X_OFFSET + nameLength
                    + PLAYERS_NAME_TYPE_X_OFFSET, PLAYERS_Y_OFFSET + (NAME_FONT.getSize() / 2) - 2 + relativeYOffset
                    - ((NAME_FONT.getSize() - TYPE_FONT.getSize()) / 3));

            // Shapes
            g2d.setStroke(MINI_LINE_STROKE);

            if (p.getTileType().equals(Tile.TileType.X)) {
                g2d.setColor(X_COLOR);
                UtilDraw.drawCross(g2d, posX, PLAYERS_Y_OFFSET - (PLAYERS_SHAPE_SIZE / 2) + relativeYOffset,
                        PLAYERS_SHAPE_SIZE);
            } else {
                g2d.setColor(O_COLOR);
                UtilDraw.drawCircle(g2d, posX, PLAYERS_Y_OFFSET - (PLAYERS_SHAPE_SIZE / 2) + relativeYOffset,
                        PLAYERS_SHAPE_SIZE);
            }

        }

        // Players scores
        String[] scores = new String[]{String.valueOf(game.getPlayers()[0].getVictoryCount()),
                String.valueOf(game.getPlayers()[1].getVictoryCount())};

        String separator = "-";

        g2d.setFont(SCORE_FONT);
        FontMetrics fm = g2d.getFontMetrics();

        int separatorLength = fm.stringWidth(separator);
        int scoreTotalLength = fm.stringWidth(scores[0]) + fm.stringWidth(scores[1]) + separatorLength
                + (2 * SCORES_SEPARATOR_OFFSET);

        g2d.setColor(SEPARATOR_FONT_COLOR);
        g2d.drawString(separator, WINDOW_HEIGHT + (baseX / 2) - (separatorLength / 2), SCORES_Y_OFFSET);

        for (int i = 0; i < 2; i++) {
            g2d.setColor(players[i].getTileType().equals(Tile.TileType.X) ? X_COLOR : O_COLOR);

            int temp = 0;
            if (i - 1 >= 0)
                for (int j = i - 1; j >= 0; j--)
                    temp += fm.stringWidth(scores[j]);

            int relativeXOffset = i * (separatorLength + (SCORES_SEPARATOR_OFFSET * 2)) + temp;

            g2d.drawString(scores[i], WINDOW_HEIGHT + (baseX / 2) - (scoreTotalLength  / 2) + relativeXOffset,
                    SCORES_Y_OFFSET);
        }

        if (game.getWinner() != null) {

        } else {
            g2d.setStroke(LINE_STROKE);

            if (game.getPlayerTurn().getTileType().equals(Tile.TileType.X)) {
                g2d.setColor(X_COLOR);
                UtilDraw.drawCross(g2d, WINDOW_HEIGHT + (baseX / 2) - (TURN_SHAPE_SIZE / 2), TURN_Y_OFFSET, TURN_SHAPE_SIZE);
            }
            else{
                g2d.setColor(O_COLOR);
                UtilDraw.drawCircle(g2d, WINDOW_HEIGHT + (baseX / 2) - (TURN_SHAPE_SIZE / 2), TURN_Y_OFFSET, TURN_SHAPE_SIZE);
            }

        }

    }

}
