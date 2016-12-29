package fr.ryukk.morpion.game;

import fr.ryukk.morpion.Morpion;

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

                ticks = frames = 0;
            }

        }

    }

    public void render() {
        if(Morpion.window() != null)
            Morpion.window().repaint();
    }

    public void update() {
        if(Morpion.game() != null)
            Morpion.game().updateGrid();
    }

    public int getFPS() { return fps; }
    public int getTPS() { return tps; }

}
