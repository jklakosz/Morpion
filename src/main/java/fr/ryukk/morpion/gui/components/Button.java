package fr.ryukk.morpion.gui.components;

import fr.ryukk.morpion.utils.Component;

import java.awt.*;

public class Button implements Component {

    private int x, y;

    private String text;

    private boolean hovered;
    private boolean clicked;

    private Runnable action;

    public Button(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @Override
    public void render(Graphics2D g2d) {

    }

    @Override
    public void update() {

    }

}
