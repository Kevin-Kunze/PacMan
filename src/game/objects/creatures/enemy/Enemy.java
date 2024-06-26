package game.objects.creatures.enemy;

import game.Game;
import game.data.Option;
import game.objects.creatures.Creature;
import game.objects.creatures.Player;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * opponent for the player,
 * chases some position
 */
public abstract class Enemy extends Creature {
    protected final Player player;
    protected int targetX;
    protected int targetY;

    public Enemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, centerX, centerY, radius, speed, color);
        this.player = player;
        targetX = (int) centerX;
        targetY = (int) centerY;
    }

    /**
     * calculate direction best for target position
     */
    @Override
    public void tickPreferredDirection() {
        tickTarget();

        Node aim = shortestDirectionTo(targetX, targetY);
        if (aim != null) {
            preferredDirectionX = Integer.signum(aim.getX() - (int) centerX);
            preferredDirectionY = Integer.signum(aim.getY() - (int) centerY);
        }
    }

    /**
     * implementation of A* algorithm
     * @param goalX target position x
     * @param goalY target position y
     * @return next step for enemy
     */
    private Node shortestDirectionTo(int goalX, int goalY) {
        Node startNode = new Node((int) centerX, (int) centerY, null, 0, goalX, goalY);

        Queue<Node> queue = new PriorityQueue<>(Node::compareTo);
        queue.add(startNode);

        Set<Node> visited = new HashSet<>();

        Node currentNode;
        while ((currentNode = queue.poll()) != null) {
            if(visited.contains(currentNode)) continue;
            if(currentNode.isSamePosition(goalX, goalY)) return currentNode.initialDirection();

            visited.add(currentNode);
            queue.addAll(currentNode.neighbors(game.getGameMap(), goalX, goalY));
        }

        return null;
    }

    /**
     * get target position
     */
    protected abstract void tickTarget();

    /**
     * calculations on tick
     */
    @Override
    public void tick() {
        super.tick();
        tickPlayerCollision();
    }

    /**
     * checks if creature collides with player
     * (is calculated as if both objects are rendered as circles)
     */
    private void tickPlayerCollision() {
        double dx = player.getCenterX() - centerX;
        double dy = player.getCenterY() - centerY;
        double r = player.getRadius() + radius;

        if(dx * dx + dy * dy < r * r) {
            game.loose();
        }
    }

    /**
     * render square with set color
     * @param g the Graphics context in which to render
     */
    @Override
    public void render(Graphics2D g) {
        double centerXOnScreen = centerX * Option.TILE_SIZE;
        double centerYOnScreen = centerY * Option.TILE_SIZE;
        double radiusOnScreen = radius * Option.TILE_SIZE;
        double sizeOnScreen = radiusOnScreen * 2.0;

        g.setColor(color);
        g.fill(new Rectangle2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, sizeOnScreen, sizeOnScreen));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Enemy enemy = (Enemy) object;
        return targetX == enemy.targetX && targetY == enemy.targetY
                && Objects.equals(player, enemy.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), player, targetX, targetY);
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "player=" + player +
                ", targetX=" + targetX +
                ", targetY=" + targetY +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", color=" + color +
                ", movingDirectionX=" + movingDirectionX +
                ", movingDirectionY=" + movingDirectionY +
                ", preferredDirectionX=" + preferredDirectionX +
                ", preferredDirectionY=" + preferredDirectionY +
                ", radius=" + radius +
                ", speed=" + speed +
                '}';
    }
}
