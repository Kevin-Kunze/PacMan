package test;

import game.Game;
import game.data.Option;
import game.objects.creatures.Player;
import game.objects.creatures.enemy.ChasingEnemy;
import org.junit.jupiter.api.Test;

class ChasingEnemyTest {

    @Test
    void testToString() {
        Game game = new Game();
        Player player = new Player(game, 13.5, 10.5, 0.375, Option.GAME_SPEED);
        ChasingEnemy chasingEnemy = new ChasingEnemy(game, player, 12.5, 8.5, 0.375,
                Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[0]);
        String value = chasingEnemy.toString();
        System.out.println(value);
    }
}