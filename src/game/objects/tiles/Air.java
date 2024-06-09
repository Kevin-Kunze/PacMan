package game.objects.tiles;

import java.awt.*;

public class Air extends Tile {

    /**
     * @param x position x
     * @param y position y
     */
    public Air(int x, int y) {
        super(x, y);
    }

    /**
     * because it's air, nothing gets rendered
     * @param g the Graphics context in which to render
     */
    @Override
    public void render(Graphics2D g) {
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
