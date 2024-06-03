package test;

import game.objects.tiles.Air;
import org.junit.jupiter.api.Test;

class AirTest {

    @Test
    void testToString() {
        Air air = new Air(5, 5);
        String value = air.toString();
        System.out.println(value);
    }
}