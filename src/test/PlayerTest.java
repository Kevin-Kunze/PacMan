package test;

import game.Game;
import game.data.Option;
import game.objects.creatures.Player;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void testToString() {
        Game game = new Game();
        Player player = new Player(game, 13.5, 10.5, 0.375, Option.GAME_SPEED);
        String value = player.toString();
        System.out.println(value);
    }
}