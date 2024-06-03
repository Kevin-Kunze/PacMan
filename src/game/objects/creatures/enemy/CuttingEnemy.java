package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

public class CuttingEnemy extends Enemy {

    public CuttingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    @Override
    protected void tickTarget() {
        targetX = (int) player.getCenterX();
        targetY = (int) player.getCenterY();
        int vx = player.getMovingDirectionX();
        int vy = player.getMovingDirectionY();

        if (vx != 0) {
            while (game.getGameMap().isFree(targetX + vx, targetY)) {
                targetX += vx;
            }
        } else if (vy != 0) {
            while (game.getGameMap().isFree(targetX, targetY + vy)) {
                targetY += vy;
            }
        }
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
        return "CuttingEnemy{" +
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
