package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.Morpion;
import fr.ryukk.morpion.game.Tile;

public class HumanPlayer extends Player {

    private boolean waitingToPlay;

    public HumanPlayer(Tile.TileType type) {
        super(type);
        this.playerType = PlayerType.HUMAN;

        waitingToPlay = false;
    }

    private volatile int x = -1, y = -1;

    @Override
    public void turn() {
        waitingToPlay = true;

        while(waitingToPlay)
            if(x >= 0 && y >= 0) {
                Morpion.game().getGrid()[x][y].setTileType(tileType);
                waitingToPlay = false;
            }

        x = -1;
        y = -1;
    }

    public void playerClick(int x, int y) {
        if(waitingToPlay) {
            this.x = x;
            this.y = y;
        }

    }

}
