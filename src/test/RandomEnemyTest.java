package test;

import game.Game;
import game.data.Option;
import game.objects.creatures.Player;
import game.objects.creatures.enemy.RandomEnemy;
import org.junit.jupiter.api.Test;

class RandomEnemyTest {

    @Test
    void testToString() {
        Game game = new Game();
        Player player = new Player(game, 13.5, 10.5, 0.375, Option.GAME_SPEED);
        RandomEnemy randomEnemy = new RandomEnemy(game, player, 12.5, 8.5, 0.375,
                Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[0]);
        String value = randomEnemy.toString();
        System.out.println(value);
    }
}