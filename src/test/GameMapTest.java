package test;

import game.Game;
import game.objects.tiles.Dot;
import game.objects.tiles.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameMapTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void isFree() {
        boolean expectedValue = true;
        boolean actualValue = game.getGameMap().isFree(5, 5);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getWidth() {
        int expectedValue = 27;
        int actualValue = game.getGameMap().getWidth();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getHeight() {
        int expectedValue = 18;
        int actualValue = game.getGameMap().getHeight();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getTile() {
        Tile expectedValue = new Dot(5, 5);
        Tile actualValue = game.getGameMap().getTile(5, 5);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getDotCount() {
        int expectedValue = 203;
        int actualValue = game.getGameMap().getDotCount();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getScore() {
        int expectedValue = 0;
        int actualValue = game.getGameMap().getScore();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String value = game.getGameMap().toString();
        System.out.println(value);
    }
}