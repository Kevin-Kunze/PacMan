package game;

import game.objects.GameObject;
import game.objects.tiles.Air;
import game.objects.tiles.Block;
import game.objects.tiles.Dot;
import game.objects.tiles.Tile;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class GameMap extends GameObject {
    //default map, where game map get loaded from
    private static final int[][] DEFAULT_MAP = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1},
            {1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 0, 2, 2, 2, 0, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 2, 2, 1, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 2, 1},
            {1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1},
            {1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1},
            {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
            {1, 2, 1, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1},
            {1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    //game map at run time
    private final Tile[][] tiles;

    //used for registering end of game and calculating score
    private int initialDotCount;
    private int dotCount;

    public GameMap() {
        tiles = new Tile[DEFAULT_MAP.length][DEFAULT_MAP[0].length];
        reset();
    }

    /**
     * set dotCount and initialDotCount to Dots on tiles
     */
    private void countDots() {
        int sum = 0;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile instanceof Dot) sum++;
            }
        }
        initialDotCount = sum;
        dotCount = sum;
    }

    /**
     * set attributes as they were before
     */
    public void reset() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                tiles[y][x] = switch (DEFAULT_MAP[y][x]) {
                    case 1 -> new Block(x, y);
                    case 2 -> new Dot(x, y);
                    default -> new Air(x, y);
                };
            }
        }
        countDots();
    }

    @Override
    public void render(Graphics2D g) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.render(g);
            }
        }
    }

    /**
     * @param x position x
     * @param y position y
     * @return is instance in game map a Dot or Air
     */
    public boolean isFree(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) return false;
        return !(tiles[y][x] instanceof Block);
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    /**
     * remove Dot at position
     * @param x position x
     * @param y position y
     */
    public void removeDot(int x, int y) {
        if (tiles[y][x] instanceof Dot) {
            tiles[y][x] = new Air(x, y);
            dotCount--;
        }
    }

    public int getDotCount() {
        return dotCount;
    }

    /**
     * @return calculated score
     */
    public int getScore() {
        return initialDotCount - dotCount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GameMap gameMap = (GameMap) object;
        return initialDotCount == gameMap.initialDotCount && dotCount == gameMap.dotCount
                && Arrays.deepEquals(tiles, gameMap.tiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(tiles), initialDotCount, dotCount);
    }

    @Override
    public String toString() {
        return "GameMap{" +
                "tiles=" + Arrays.deepToString(tiles) +
                ", initialDotCount=" + initialDotCount +
                ", dotCount=" + dotCount +
                '}';
    }
}
