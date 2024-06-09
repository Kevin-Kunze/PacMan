package game.objects.tiles;

import game.data.Option;

import java.awt.*;

public class Block extends Tile {
    /**
     * @param x position x
     * @param y position y
     */
    public Block(int x, int y) {
        super(x, y);
    }

    /**
     * render filling square with set color
     * @param g the Graphics context in which to render
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Option.BLOCK_COLOR);
        g.fillRect(x * Option.TILE_SIZE, y * Option.TILE_SIZE, Option.TILE_SIZE, Option.TILE_SIZE);
    }

    @Override
    public String toString() {
        return "Block{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
