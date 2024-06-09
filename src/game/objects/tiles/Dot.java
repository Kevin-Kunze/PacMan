package game.objects.tiles;

import game.data.Option;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Dot extends Tile {
    public Dot(int x, int y) {
        this(x, y, 0.125);
    }

    /**
     * @param x position x
     * @param y postion y
     * @param radius radius
     */
    protected Dot(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    protected final double radius;

    /**
     * render small circle with set color
     * @param g the Graphics context in which to render
     */
    @Override
    public void render(Graphics2D g) {
        double centerXOnScreen = getCenterX() * Option.TILE_SIZE;
        double centerYOnScreen = getCenterY() * Option.TILE_SIZE;
        double radiusOnScreen = radius * Option.TILE_SIZE;
        double diameterOnScreen = radiusOnScreen * 2.0;

        g.setColor(Option.DOT_COLOR);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen,
                diameterOnScreen, diameterOnScreen));
    }

    public double getCenterX() {
        return x + 0.5;
    }

    public double getCenterY() {
        return y + 0.5;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "radius=" + radius +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
