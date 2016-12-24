package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.game.Tile;

public class RemoteHumanPlayer extends Player {

    public RemoteHumanPlayer(String name, Tile.TileType type) {
        super(name, type);
        this.playerType = PlayerType.REMOTE_HUMAN;
    }

    @Override
    public void turn() {

    }

}
