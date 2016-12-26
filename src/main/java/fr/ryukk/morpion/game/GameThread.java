package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.player.HumanPlayer;
import fr.ryukk.morpion.game.player.Player;
import fr.ryukk.morpion.utils.Mouse;

import java.awt.*;

import static fr.ryukk.morpion.utils.Constants.*;

public class GameThread extends Thread {

    private boolean running;

    private int fps;
    private int tps;

    public GameThread() {
        running = false;
    }

    @Override
    public void start() {
        running = true;

        super.start();
    }

    @Override
    public void run() {
        long lastTick = System.nanoTime();
        long lastFrame = System.nanoTime();

        int ticks = 0;
        int frames = 0;

        long timer = System.currentTimeMillis();

        while(running) {
            if(System.nanoTime() - lastTick > TICK_TIME) {
                lastTick += TICK_TIME;

                update();
                ticks++;
            }

            if(System.nanoTime() - lastFrame > FRAME_TIME) {
                lastFrame += FRAME_TIME;

                render();
                frames++;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;

                fps = frames;
                tps = ticks;

                Morpion.window().setTitle("Morpion - FPS: " + fps + " TPS: " + tps);

                ticks = frames = 0;
            }

        }

    }

    public void render() {
        if(Morpion.window() != null)
            Morpion.window().repaint();
    }

    public void update() {
        if(Morpion.game() == null) return;

        Morpion.game().updateGrid();

        if(Mouse.singletone().isDown())
            if(Morpion.game().getPlayerTurn().getPlayerType().equals(Player.PlayerType.HUMAN))
                for(int x = 0; x < 3; x++)
                    for(int y = 0; y < 3; y++)
                        if(Morpion.game().getGrid()[x][y].isHovered())
                            if(Morpion.game().getGrid()[x][y].getTileType().equals(Tile.TileType.NONE))
                                ((HumanPlayer) Morpion.game().getPlayerTurn()).playerClick(x, y);
    }

}
