package fr.ryukk.morpion.game.panel;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.listener.MouseInteractListener;
import fr.ryukk.morpion.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public final class GamePanel extends JPanel {

    public GamePanel() {
        MouseInteractListener listener = new MouseInteractListener();

        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        renderGrid(g2d);
        renderConfig(g2d);

    }

    private void renderGrid(Graphics2D g2d) {
        int gridSize = Constants.WINDOW_HEIGHT - 40;

        // Background
        g2d.setColor(Constants.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Tiles
        g2d.setStroke(Constants.LINE_STROKE);

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                Morpion.game().getGrid()[x][y].paint(g2d);

        // Grid
        g2d.setColor(Constants.GRID_COLOR);
        g2d.drawRoundRect(20, 20, gridSize, gridSize, 5, 5);

        for(int i = 1; i < 3; i++) {
            int k = 20 + (gridSize / 3) * i;

            g2d.drawLine(k, 20, k, gridSize + 20);
            g2d.drawLine(20, k, gridSize + 20, k);
        }

    }

    private void renderConfig(Graphics2D g2d) {
        int baseX = Constants.WINDOW_WIDTH - Constants.WINDOW_HEIGHT;

        g2d.setStroke(Constants.BASIC_STROKE);

        // Title
        g2d.setColor(Constants.BASE_FONT_COLOR);
        g2d.setFont(Constants.BASE_FONT);

        String title = "Morpion";

        g2d.drawString(title, Constants.WINDOW_HEIGHT + (baseX / 2) -
                (g2d.getFontMetrics(Constants.BASE_FONT).stringWidth(title) / 2),
                70);

    }

}
