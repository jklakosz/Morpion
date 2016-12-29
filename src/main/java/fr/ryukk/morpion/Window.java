package fr.ryukk.morpion;

import fr.ryukk.morpion.utils.View;

import javax.swing.*;
import java.awt.*;

import static fr.ryukk.morpion.utils.Constants.*;

public final class Window extends JFrame {

    public Window() {
        super("Morpion");

        Dimension size = new Dimension(WINDOW_WIDTH - 10, WINDOW_HEIGHT - 10);

        getContentPane().setPreferredSize(size);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setVisible(true);
    }

    public void switchView(View view) {
        setContentPane(view);
        revalidate();
        repaint();
    }

    public void render(Graphics2D g2d) {
        // FPS & TPS
        if(Morpion.isDebugActive()) {
            g2d.setColor(DEBUG_FONT_COLOR);
            g2d.setFont(DEBUG_FONT);

            ScreenHandler thread = Morpion.screenHandler();

            String fps = "FPS: " + thread.getFPS();
            String tps = "TPS: " + thread.getTPS();

            int base = WINDOW_WIDTH - DEBUG_X_OFFSET;

            int fpsX = base - g2d.getFontMetrics().stringWidth(fps);
            int tpsX = base - g2d.getFontMetrics().stringWidth(tps);

            g2d.drawString(fps, fpsX, DEBUG_Y_OFFSET);
            g2d.drawString(tps, tpsX, DEBUG_Y_OFFSET + g2d.getFont().getSize());
        }

    }

}
