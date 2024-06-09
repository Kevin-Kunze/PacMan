package game.objects.tiles;

import game.objects.GameObject;

import java.util.Objects;


public abstract class Tile extends GameObject {
    protected final int x;
    protected final int y;

    /**
     * @param x position x
     * @param y position y
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Tile tile = (Tile) object;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
