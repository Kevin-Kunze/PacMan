package game.objects.tiles;

import game.objects.GameObject;

public abstract class Tile extends GameObject {
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected final int x;
    protected final int y;
}
