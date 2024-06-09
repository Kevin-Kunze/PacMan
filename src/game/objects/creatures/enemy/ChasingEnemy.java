package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

/**
 * chases the player according to his actual position
 */
public class ChasingEnemy extends Enemy {
    public ChasingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    /**
     * get player position
     */
    @Override
    protected void tickTarget() {
        targetX = (int) player.getCenterX();
        targetY = (int) player.getCenterY();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "ChasingEnemy{" +
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
