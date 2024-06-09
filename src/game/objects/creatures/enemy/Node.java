package game.objects.creatures.enemy;

import game.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node>{
    private final int x;
    private final int y;
    private final Node previous;
    private final int distanceFromStart;
    private final int minDistanceToGoal;

    /**
     * exists for A* algorithm
     * @param x position x
     * @param y position y
     * @param previous previous node
     * @param distanceFromStart distance from start
     * @param goalX target position x
     * @param goalY target position y
     */
    public Node(int x, int y, Node previous, int distanceFromStart, int goalX, int goalY) {
        this.x = x;
        this.y = y;
        this.previous = previous;
        this.distanceFromStart = distanceFromStart;
        minDistanceToGoal = Math.abs(goalX - x) + Math.abs(goalY - y);
    }

    /**
     * get all neighbors of current node
     * (only direct neighbors, not diagonal ones)
     * @param gameMap reference for calculating the neighbors on game map
     * @param goalX target position x
     * @param goalY target position y
     * @return all neighbors in list
     */
    public List<Node> neighbors(GameMap gameMap, int goalX, int goalY) {
        List<Node> neighbors = new ArrayList<>();

        if (gameMap.isFree(x - 1, y)) {
            neighbors.add(new Node(x - 1, y, this, distanceFromStart + 1, goalX, goalY));
        }
        if (gameMap.isFree(x + 1, y)) {
            neighbors.add(new Node(x + 1, y, this, distanceFromStart + 1, goalX, goalY));
        }
        if (gameMap.isFree(x, y - 1)) {
            neighbors.add(new Node(x, y - 1, this, distanceFromStart + 1, goalX, goalY));
        }
        if (gameMap.isFree(x, y + 1)) {
            neighbors.add(new Node(x, y + 1, this, distanceFromStart + 1, goalX, goalY));
        }

        return neighbors;
    }

    /**
     * recursive call for calculating initial direction
     * @return second last node in path,
     * for choosing direction the best one
     */
    public Node initialDirection() {
        if(distanceFromStart <= 1) return this;
        return previous.initialDirection();
    }

    /**
     * @param x position x
     * @param y position y
     * @return if parameters position equals this position
     */
    public boolean isSamePosition(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(distanceFromStart + minDistanceToGoal, other.distanceFromStart + other.minDistanceToGoal);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Node node = (Node) object;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "distanceFromStart=" + distanceFromStart +
                ", x=" + x +
                ", y=" + y +
                ", previous=" + previous +
                ", minDistanceToGoal=" + minDistanceToGoal +
                '}';
    }
}
