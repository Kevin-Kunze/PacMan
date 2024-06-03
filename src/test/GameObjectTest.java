package test;

import game.objects.GameObject;
import org.junit.jupiter.api.Test;

import java.awt.*;

class GameObjectTest {

    @Test
    void testToString() {
        GameObject gameObject = new GameObject() {
            @Override
            public void render(Graphics2D g, int tileSize) {

            }
        };
        String value = gameObject.toString();
        System.out.println(value);
    }
}