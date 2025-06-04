package fr.ryukk.morpion.game.player;

import fr.ryukk.morpion.game.Grid;
import fr.ryukk.morpion.game.Tile;
import fr.ryukk.morpion.utils.Serie;

public class AIPlayer extends Player {

    private Grid grid;
    private Tile.TileType opponentTileType;

    public AIPlayer(Tile.TileType type) {
        super("Alfred De Musset", type);
        this.playerType = Player.PlayerType.AI;
    }

    public void setGameContext(Grid grid, Tile.TileType opponentTileType) {
        this.grid = grid;
        this.opponentTileType = opponentTileType;
    }

    // Helper method to convert Tile[][] to a temporary Grid object for Serie.calc()
    private Grid convertToGrid(Tile[][] tileArray) {
        Grid tempGrid = new Grid(3, 3); // Assuming 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tileArray[i][j] != null) { // Ensure tileArray element is not null
                    tempGrid.get(i, j).setTileType(tileArray[i][j].getTileType());
                } else {
                    // This case should ideally not happen if tileArray is always fully initialized
                    tempGrid.get(i, j).setTileType(Tile.TileType.NONE);
                }
            }
        }
        return tempGrid;
    }

    @Override
    public void turn() {
        calculateNextMove();
    }

    private void calculateNextMove() {
        int maxValue = -10000;
        int tmp;
        int maxX = 0, maxY = 0;

        Tile[][] simulationGrid = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                simulationGrid[i][j] = new Tile(i, j);
                simulationGrid[i][j].setTileType(this.grid.get(i, j).getTileType());
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (simulationGrid[x][y].getTileType().equals(Tile.TileType.NONE)) {
                    simulationGrid[x][y].setTileType(this.tileType);
                    tmp = min(simulationGrid, 4);
                    if (tmp > maxValue) {
                        maxValue = tmp;
                        maxX = x;
                        maxY = y;
                    }
                    simulationGrid[x][y].setTileType(Tile.TileType.NONE);
                }
            }
        }
        this.grid.get(maxX, maxY).setTileType(this.tileType);
    }

    private int max(Tile[][] tmpGrid, int profondeur) {
        int maxValue = -10000;
        Tile.TileType winner = evalWinner(tmpGrid);
        if (profondeur == 0 || winner != null) {
            return eval(tmpGrid);
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (tmpGrid[x][y].getTileType().equals(Tile.TileType.NONE)) {
                    tmpGrid[x][y].setTileType(this.tileType);
                    int tmp = min(tmpGrid, profondeur - 1);
                    if (tmp > maxValue)
                        maxValue = tmp;
                    tmpGrid[x][y].setTileType(Tile.TileType.NONE);
                }
            }
        }
        return maxValue;
    }

    private int min(Tile[][] tmpGrid, int profondeur) {
        int minValue = 10000;
        Tile.TileType winner = evalWinner(tmpGrid);
        if (profondeur == 0 || winner != null) {
            return eval(tmpGrid);
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (tmpGrid[x][y].getTileType().equals(Tile.TileType.NONE)) {
                    tmpGrid[x][y].setTileType(this.opponentTileType);
                    int tmp = max(tmpGrid, profondeur - 1);
                    if (tmp < minValue)
                        minValue = tmp;
                    tmpGrid[x][y].setTileType(Tile.TileType.NONE);
                }
            }
        }
        return minValue;
    }

    private int eval(Tile[][] tmpGrid) {
        int tokens = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!tmpGrid[x][y].getTileType().equals(Tile.TileType.NONE)) {
                    tokens++;
                }
            }
        }

        Tile.TileType winner = evalWinner(tmpGrid);

        if (winner != null) {
            if (winner.equals(Tile.TileType.NONE))
                return 0; // Draw
            else if (winner.equals(this.tileType))
                return 1000 - tokens; // AI wins
            else if (winner.equals(this.opponentTileType))
                return -1000 + tokens; // Opponent wins
        }

        // Heuristic evaluation using Serie.calc for series of length 2
        Grid tempEvalGrid = convertToGrid(tmpGrid);
        Serie aiSerie = new Serie(this.tileType);
        Serie opponentSerie = new Serie(this.opponentTileType);

        int aiScore = aiSerie.calc(tempEvalGrid, 2);
        int opponentScore = opponentSerie.calc(tempEvalGrid, 2);

        return aiScore - opponentScore;
    }

    private Tile.TileType evalWinner(Tile[][] tmpGrid) {
        Grid tempWinnerGrid = convertToGrid(tmpGrid);
        Serie aiSerie = new Serie(this.tileType);
        if (aiSerie.calc(tempWinnerGrid, 3) > 0) {
            return this.tileType;
        }

        Serie opponentSerie = new Serie(this.opponentTileType);
        if (opponentSerie.calc(tempWinnerGrid, 3) > 0) {
            return this.opponentTileType;
        }

        boolean draw = true;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (tmpGrid[x][y].getTileType() == Tile.TileType.NONE) {
                    draw = false;
                    break;
                }
            }
            if (!draw) break;
        }
        if (draw) {
            return Tile.TileType.NONE; // Indicates a draw
        }

        return null; // Indicates game not over and no winner yet
    }
}
