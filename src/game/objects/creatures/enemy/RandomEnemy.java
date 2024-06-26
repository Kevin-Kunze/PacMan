package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

/**
 * chases random position
 */
public class RandomEnemy extends Enemy {
    private final Random random;

    public RandomEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
        random = new Random();
    }

    /**
     * get random position, that is free
     */
    @Override
    protected void tickTarget() {
        if ((int) centerX == targetX && (int) centerY == targetY) {
            int nextTargetX = random.nextInt(game.getGameMap().getWidth());
            int nextTargetY = random.nextInt(game.getGameMap().getHeight());

            if (!(game.getGameMap().isFree(nextTargetX, nextTargetY))) {
                return;
            }

            targetX = nextTargetX;
            targetY = nextTargetY;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String toString() {
        return "RandomEnemy{" +
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
