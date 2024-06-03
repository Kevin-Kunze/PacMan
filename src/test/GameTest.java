package test;

import game.Game;
import game.GameMap;
import game.Menu;
import game.data.Option;
import game.objects.creatures.Player;
import game.objects.creatures.enemy.ChasingEnemy;
import game.objects.creatures.enemy.CuttingEnemy;
import game.objects.creatures.enemy.Enemy;
import game.objects.creatures.enemy.RandomEnemy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void getGameMap() {
        GameMap expectedValue = new GameMap();
        GameMap actualValue = game.getGameMap();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getMenu() {
        int width = game.getGameMap().getWidth() * (Option.TILE_SIZE + 1) - 10;
        int height = game.getGameMap().getHeight() * (Option.TILE_SIZE + 1) + 14;
        Menu expectedValue = new Menu(game, width, height);
        Menu actualValue = game.getMenu();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getEnemies() {
        Player player = new Player(game, 13.5, 10.5, 0.375, Option.GAME_SPEED);
        Enemy[] expectedValue = new Enemy[]{
                new ChasingEnemy(game, player, 12.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[0]),
                new CuttingEnemy(game, player, 13.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[1]),
                new RandomEnemy(game, player, 14.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[2])
        };
        Enemy[] actualValue = game.getEnemies();
        Assertions.assertArrayEquals(expectedValue, actualValue);
    }

    @Test
    void getTime() {
        int expectedValue = 0;
        int actualValue = game.getTime();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String value = game.toString();
        System.out.println(value);
    }
}