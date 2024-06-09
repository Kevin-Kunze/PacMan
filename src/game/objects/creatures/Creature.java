package game.objects.creatures;

import game.Game;
import game.GameMap;
import game.objects.GameObject;

import java.awt.*;
import java.util.Objects;

/**
 * mobile game object,
 * player or enemy
 */
public abstract class Creature extends GameObject {
    protected final Game game;
    protected final double radius;
    protected final double speed;
    protected final Color color;
    private final double initialX;
    private final double initialY;
    protected double centerX;
    protected double centerY;
    protected int preferredDirectionX;
    protected int preferredDirectionY;
    protected int movingDirectionX;
    protected int movingDirectionY;

    public Creature(Game game, double centerX, double centerY, double radius, double speed, Color color) {
        this.game = game;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.speed = speed;
        this.color = color;

        initialX = centerX;
        initialY = centerY;
    }

    /**
     * reset creature to initial attributes
     */
    public void reset() {
        centerX = initialX;
        centerY = initialY;

        preferredDirectionX = 0;
        preferredDirectionY = 0;
        movingDirectionX = 0;
        movingDirectionY = 0;
    }

    /**
     * calculations on tick
     */
    public void tick() {
        tickMovingDirection();

        double newX = centerX + movingDirectionX * speed;
        double newY = centerY + movingDirectionY * speed;

        boolean crossedCenterX = Math.abs((centerX - 0.5) % 1.0 - (newX - 0.5) % 1.0) > 0.5;
        boolean crossedCenterY = Math.abs((centerY - 0.5) % 1.0 - (newY - 0.5) % 1.0) > 0.5;

        centerX = newX;
        centerY = newY;

        // when creature has crossed center (or is near center) make additional calculations
        if (crossedCenterX || crossedCenterY) {
            tickPreferredDirection();
            tickTurn(crossedCenterX, crossedCenterY);
        }

        tickWallCollisions();
    }

    /**
     * set movingDirection according to preferred direction
     */
    private void tickMovingDirection() {
        if (movingDirectionX == 0 && movingDirectionY == 0) {
            movingDirectionX = preferredDirectionX;
            movingDirectionY = preferredDirectionY;
        } else if (movingDirectionX != 0 && preferredDirectionX != 0) {
            movingDirectionX = preferredDirectionX;
        } else if (movingDirectionY != 0 && preferredDirectionY != 0) {
            movingDirectionY = preferredDirectionY;
        }

    }

    /**
     * calculated direction according to player for enemies
     */
    protected abstract void tickPreferredDirection();

    /**
     * turn in preferredDirection by setting movingDirection
     * @param crossedCenterX if on position x center was crossed
     * @param crossedCenterY if on position y center was crossed
     */
    private void tickTurn(boolean crossedCenterX, boolean crossedCenterY) {
        boolean turnXtoY = crossedCenterX && movingDirectionX != 0 && preferredDirectionY != 0
                && game.getGameMap().isFree((int) centerX, (int) (centerY + preferredDirectionY));
        boolean turnYtoX = crossedCenterY && movingDirectionY != 0 && preferredDirectionX != 0
                && game.getGameMap().isFree((int) (centerX + preferredDirectionX), (int) centerY);
        if (turnXtoY) {
            snapX();
            movingDirectionY = preferredDirectionY;
        }
        if (turnYtoX) {
            snapY();
            movingDirectionX = preferredDirectionX;
        }
    }

    /**
     * if stuck on wall, snap to position and set movingDirection to 0
     */
    private void tickWallCollisions() {
        GameMap gameMap = game.getGameMap();

        if ((movingDirectionX == 1 && !gameMap.isFree((int) (centerX + 0.5), (int) centerY))
                || (movingDirectionX == -1 && !gameMap.isFree((int) (centerX - 0.5), (int) centerY))) {
            snapX();
        }
        if ((movingDirectionY == 1 && !gameMap.isFree((int) centerX, (int) (centerY + 0.5)))
                || (movingDirectionY == -1 && !gameMap.isFree((int) centerX, (int) (centerY - 0.5)))) {
            snapY();
        }
    }

    /**
     * snap centerX,
     * set movingDirectionX to 0
     */
    private void snapX() {
        centerX = (int) centerX + 0.5;
        movingDirectionX = 0;
    }

    /**
     * snap centerY,
     * set movingDirectionY to 0
     */
    private void snapY() {
        centerY = (int) centerY + 0.5;
        movingDirectionY = 0;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }

    public int getMovingDirectionX() {
        return movingDirectionX;
    }

    public int getMovingDirectionY() {
        return movingDirectionY;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Creature creature = (Creature) object;
        return Double.compare(centerX, creature.centerX) == 0 && Double.compare(centerY, creature.centerY) == 0
                && Double.compare(radius, creature.radius) == 0 && Double.compare(speed, creature.speed) == 0
                && preferredDirectionX == creature.preferredDirectionX
                && preferredDirectionY == creature.preferredDirectionY
                && movingDirectionX == creature.movingDirectionX && movingDirectionY == creature.movingDirectionY
                && Double.compare(initialX, creature.initialX) == 0
                && Double.compare(initialY, creature.initialY) == 0
                && Objects.equals(color, creature.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centerX, centerY, radius, speed, color, preferredDirectionX, preferredDirectionY, movingDirectionX, movingDirectionY, initialX, initialY);
    }

    @Override
    public String toString() {
        return "Creature{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", radius=" + radius +
                ", speed=" + speed +
                ", color=" + color +
                ", preferredDirectionX=" + preferredDirectionX +
                ", preferredDirectionY=" + preferredDirectionY +
                ", movingDirectionX=" + movingDirectionX +
                ", movingDirectionY=" + movingDirectionY +
                ", initialX=" + initialX +
                ", initialY=" + initialY +
                '}';
    }
}
