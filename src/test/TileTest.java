package test;

import game.objects.tiles.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;

class TileTest {

    @Test
    void testToString() {
        Tile tile = new Tile(0, 0) {
            @Override
            public void render(Graphics2D g, int tileSize) {

            }
        };
        String value = tile.toString();
        System.out.println(value);
    }
}