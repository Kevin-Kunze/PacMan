package test;

import game.Game;
import game.Menu;
import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void testToString() {
        Game game = new Game();
        Menu menu = game.getMenu();
        String value = menu.toString();
        System.out.println(value);
    }
}