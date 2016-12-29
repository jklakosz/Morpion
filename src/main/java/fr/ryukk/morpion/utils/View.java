package fr.ryukk.morpion.utils;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.ScreenHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static fr.ryukk.morpion.utils.Constants.*;
import static fr.ryukk.morpion.utils.Constants.DEBUG_Y_OFFSET;

public abstract class View extends JPanel implements Component {

    private List<Component> components;

    public View() {
        components = new ArrayList<>();
    }

    public final void updateAll() {
        update();
        components.forEach(Component::update);
    }

    @Override
    public final void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        render(g2d);
        components.forEach((c) -> c.render(g2d));
        renderDebug(g2d);

        g2d.dispose();
    }

    private void renderDebug(Graphics2D g2d) {
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

    public void add(Component component) {
        components.add(component);
    }
    public void add(Component... components) {
        for(Component component : components)
            add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }
    public void remove(Component... components) {
        for(Component component : components)
            remove(component);
    }

}
