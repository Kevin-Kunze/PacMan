package game.objects.tiles;

import game.data.Options;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Dot extends Tile {
    public Dot(int x, int y) {
        this(x, y, 0.125);
    }

    protected Dot(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    protected final double radius;

    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = getCenterX() * tileSize;
        double centerYOnScreen = getCenterY() * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2.0;

        g.setColor(Options.getDotColor());
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
}
