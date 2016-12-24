package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.game.Tile;

public class IAPlayer extends Player {

    public IAPlayer(Tile.TileType type) {
        super(type);
        this.playerType = Player.PlayerType.IA;
    }

    @Override
    public void turn() {

    }

      /*private void iaTurn() {
        int max = -10000;
        int tmp;
        int maxX = 0, maxY = 0;

        Tile[][] tmpGrid = new Tile[3][3];

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                tmpGrid[x][y] = new Tile(x, y, grid[x][y].getType());

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                if(tmpGrid[x][y].getType().equals(Tile.TileType.NONE)) {
                    tmpGrid[x][y].setType(iaType);

                        tmp = min(tmpGrid, 4);

                    System.out.println(x + ", " + y + " : " + tmp);

                    if(tmp > max) {
                        max = tmp;

                        maxX = x;
                        maxY = y;
                    }

                    tmpGrid[x][y].setType(Tile.TileType.NONE);
                }

            }

        }

        grid[maxX][maxY].setType(iaType);
        Morpion.window().repaint();
        playerTurn = true;

        System.out.println("Playing on " + maxX + ", " + maxY);
    }

    private int max(Tile[][] tmpGrid, int profondeur){
        int max = -10000;
        int tmp;

        if(profondeur == 0 || evalWinner(tmpGrid) != null) {
            return eval(tmpGrid);
        }

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                if(tmpGrid[x][y].getType().equals(Tile.TileType.NONE)) {
                    tmpGrid[x][y].setType(playerType);

                    tmp = min(tmpGrid, profondeur - 1);

                    if(tmp > max)
                        max = tmp;

                    tmpGrid[x][y].setType(Tile.TileType.NONE);
                }

            }

        }

        return max;
    }

    private int min(Tile[][] tmpGrid, int profondeur){
        int min = 10000;
        int tmp;

        if(profondeur == 0 || evalWinner(tmpGrid) != null) {
            return eval(tmpGrid);
        }

        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                if(tmpGrid[x][y].getType().equals(Tile.TileType.NONE)) {
                    tmpGrid[x][y].setType(iaType);

                    tmp = max(tmpGrid, profondeur - 1);

                    if(tmp < min)
                        min = tmp;

                    tmpGrid[x][y].setType(Tile.TileType.NONE);
                }

            }

        }

        return min;
    }

    private int eval(Tile[][] tmpGrid) {
        int tokens = 0;

        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (!tmpGrid[x][y].getType().equals(Tile.TileType.NONE))
                    tokens++;

        Tile.TileType winner = evalWinner(tmpGrid);

        if(winner != null)
            if(winner.equals(Tile.TileType.NONE))
                return 0;
            else if(winner.equals(iaType))
                return 1000 - tokens;
            else if(winner.equals(playerType))
                return -1000 + tokens;

        Serie iaSerie = new Serie(0);
        Serie playerSerie = new Serie(0);

        calcSeries(tmpGrid, iaSerie, playerSerie, 2);

        return iaSerie.getValue() - playerSerie.getValue();

    }

    private Tile.TileType evalWinner(Tile[][] tmpGrid) {
        Serie iaSerie = new Serie(0);
        Serie playerSerie = new Serie(0);

        Tile.TileType winner = Tile.TileType.NONE;

        calcSeries(tmpGrid, iaSerie, playerSerie, 3);

        if(iaSerie.getValue() > 0)
            winner = iaType;
        else if(playerSerie.getValue() > 0)
            winner = playerType;
        else
            for(int x = 0; x < 3; x++)
                for(int y = 0; y < 3; y++)
                    if(tmpGrid[x][y].getType().equals(Tile.TileType.NONE))
                        winner = null;

        return winner;
    }*/

}
