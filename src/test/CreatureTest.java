package test;

import game.Game;
import game.data.Option;
import game.objects.creatures.Creature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class CreatureTest {
    Game game;
    Creature creature;

    @BeforeEach
    void setUp() {
        game = new Game();
        creature = new Creature(game, 5.5, 5.5, 0.375, Option.GAME_SPEED * 0.85, Color.WHITE) {
            @Override
            protected void tickPreferredDirection() {

            }

            @Override
            public void render(Graphics2D g, int tileSize) {

            }
        };
    }

    @Test
    void getCenterX() {
        double expectedValue = 5.5;
        double actualValue = creature.getCenterX();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getCenterY() {
        double expectedValue = 5.5;
        double actualValue = creature.getCenterY();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getRadius() {
        double expectedValue = 0.375;
        double actualValue = creature.getRadius();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getMovingDirectionX() {
        int expectedValue = 0;
        int actualValue = creature.getMovingDirectionX();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getMovingDirectionY() {
        int expectedValue = 0;
        int actualValue = creature.getMovingDirectionY();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String value = creature.toString();
        System.out.println(value);
    }
}