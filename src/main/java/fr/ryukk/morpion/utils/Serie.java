package fr.ryukk.morpion.utils;

import fr.ryukk.morpion.game.Tile;

public final class Serie {

    private Tile.TileType tileType;

    public Serie(Tile.TileType tileType) {
        this.tileType = tileType;
    }

    public Tile.TileType getTileType() { return tileType; }

    public int calc(Tile[][] grid, int n) {
        int value = 0;

        int p1Count = 0;

        for(int x = 0; x < 3; x++) {
            if(grid[x][x].getTileType().equals(getTileType())) {
                p1Count++;

                if(p1Count == n)
                    value++;
            }
            else if(!grid[x][x].getTileType().equals(Tile.TileType.NONE)) {
                p1Count = 0;
            }

        }

        p1Count = 0;

        for(int x = 0; x < 3; x++) {
            if(grid[x][2 - x].getTileType().equals(getTileType())) {
                p1Count++;

                if(p1Count == n)
                    value++;
            }
            else if(!grid[x][x].getTileType().equals(Tile.TileType.NONE)) {
                p1Count = 0;
            }

        }

        for(int x = 0; x < 3; x++) {
            p1Count = 0;

            for(int y = 0; y < 3; y++) {
                if(grid[x][y].getTileType().equals(getTileType())) {
                    p1Count++;

                    if(p1Count == n)
                        value++;
                }
                else if(!grid[x][x].getTileType().equals(Tile.TileType.NONE)) {
                    p1Count = 0;
                }

            }

            p1Count = 0;

            for(int y = 0; y < 3; y++) {
                if(grid[y][x].getTileType().equals(getTileType())) {
                    p1Count++;

                    if(p1Count == n)
                        value++;
                }
                else if(!grid[x][x].getTileType().equals(Tile.TileType.NONE)) {
                    p1Count = 0;
                }

            }

        }

        return value;
    }

}