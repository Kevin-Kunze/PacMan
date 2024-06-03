package game.objects.tiles;

import java.awt.*;

public class Air extends Tile {
    public Air(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        //do not render
    }

    @Override
    public String toString() {
        return "Air{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
