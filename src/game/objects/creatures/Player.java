package game.objects.creatures;

import game.Game;
import game.data.Option;
import game.objects.creatures.enemy.Enemy;
import game.objects.tiles.Dot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Player extends Creature implements KeyListener {
    public Player(Game game, double centerX, double centerY, double radius, double speed) {
        super(game, centerX, centerY, radius, speed, Option.PLAYER_COLOR);
    }

    @Override
    public  void tick() {
        super.tick();
        tickDotCollision();
    }

    @Override
    protected void tickPreferredDirection() {
        for(Enemy enemy : game.getEnemies()) {
            enemy.tickPreferredDirection();
        }
    }

    private void tickDotCollision() {
        int x = (int) centerX;
        int y = (int) centerY;

        if (game.getGameMap().getTile(x, y) instanceof Dot dot) {
            double dx = dot.getCenterX() - centerX;
            double dy = dot.getCenterY() - centerY;
            double r = dot.getRadius() + radius;

            if(dx * dx + dy * dy < r * r) {
                game.getGameMap().removeDot(x, y);
                if (game.getGameMap().getDotCount() == 0) {
                    game.setWon(true);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2;

        g.setColor(color);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //ignore
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                preferredDirectionX = 0;
                preferredDirectionY = -1;
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                preferredDirectionX = 0;
                preferredDirectionY = 1;
            }
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                preferredDirectionX = -1;
                preferredDirectionY = 0;
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                preferredDirectionX = 1;
                preferredDirectionY = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //ignore
    }

    @Override
    public String toString() {
        return "Player{" +
                "centerX=" + centerX +
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
