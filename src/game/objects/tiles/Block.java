package game.objects.tiles;

import game.data.Option;

import java.awt.*;

public class Block extends Tile {
    public Block(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        g.setColor(Option.BLOCK_COLOR);
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }

    @Override
    public String toString() {
        return "Block{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
