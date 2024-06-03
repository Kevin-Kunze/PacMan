package test;

import game.objects.tiles.Dot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DotTest {
    Dot dot;

    @BeforeEach
    void setUp() {
        dot = new Dot(5, 5);
    }

    @Test
    void getCenterX() {
        double expectedValue = 5.5;
        double actualValue = dot.getCenterX();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getCenterY() {
        double expectedValue = 5.5;
        double actualValue = dot.getCenterY();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getRadius() {
        double expectedValue = 0.125;
        double actualValue = dot.getRadius();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String value = dot.toString();
        System.out.println(value);
    }
}