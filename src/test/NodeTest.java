package test;

import game.Game;
import game.objects.creatures.enemy.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class NodeTest {
    Game game;
    Node node;

    @BeforeEach
    void setUp() {
        game = new Game();
        node = new Node(5, 5, null, 0, 13, 13);
    }

    @Test
    void neighbors() {
        List<Node> expectedValue = new ArrayList<>();
        expectedValue.add(new Node(6, 5, node, 1, 13, 13));
        expectedValue.add(new Node(5, 4, node, 1, 13, 13));
        List<Node> actualValue = node.neighbors(game.getGameMap(), 13, 13);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void initialDirection() {
        Node expectedValue = node;
        Node actualValue = node.initialDirection();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void isSamePositionTo() {
        int expectedValue = 0;
        int actualValue = node.compareTo(new Node(6, 5, node, 1, 13, 13));
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getX() {
        int expectedValue = 5;
        int actualValue = node.getX();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getY() {
        int expectedValue = 5;
        int actualValue = node.getY();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testIsSamePosition() {
        boolean expectedValue = true;
        boolean actualValue = node.isSamePosition(5, 5);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String value = node.toString();
        System.out.println(value);
    }
}