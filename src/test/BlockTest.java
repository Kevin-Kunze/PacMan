package test;

import game.objects.tiles.Block;
import org.junit.jupiter.api.Test;

class BlockTest {
    @Test
    void testToString() {
        Block block = new Block(5, 5);
        String value = block.toString();
        System.out.println(value);
    }
}