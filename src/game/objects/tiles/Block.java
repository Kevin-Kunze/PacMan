package game.objects.tiles;

import game.data.Options;

import java.awt.*;

public class Block extends Tile {
    public Block(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        g.setColor(Options.getBlockColor());
        g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}
