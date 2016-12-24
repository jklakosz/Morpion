package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.game.Tile;

public class RemoteHumanPlayer extends Player {

    public RemoteHumanPlayer(Tile.TileType type) {
        super(type);
        this.playerType = PlayerType.REMOTE_HUMAN;
    }

    @Override
    public void turn() {

    }

}
