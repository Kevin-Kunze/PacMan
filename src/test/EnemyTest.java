package test;

import game.Game;
import org.junit.jupiter.api.Test;

class EnemyTest {

    @Test
    void testToString() {
        Game game = new Game();
        String value = game.getEnemies()[0].toString();
        System.out.println(value);
    }
}