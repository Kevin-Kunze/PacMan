package test;

import game.ScoreList;
import game.data.Language;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreListTest {
    ScoreList scoreList;

    @BeforeEach
    void setUp() {
        scoreList = new ScoreList();
        scoreList.add("Player", 203, 60);
        scoreList.add("Player", 203, 61);
        scoreList.add("Player", 196, 55);
    }

    @Test
    void getAsString() {
        String expectedValue = "<html><table><tr><th><h3>Name</h3></th><th><h3>Score</h3></th><th><h3>Time</h3></th>" +
                "</tr><tr><th>Player</th><th>203</th><th>60</th></tr><tr><th>Player</th><th>203</th><th>61</th></tr>" +
                "<tr><th>Player</th><th>196</th><th>55</th></tr></table></html>";
        String actualValue = scoreList.getAsString(Language.getName(0), Language.getScore(0),
                Language.getTime(0));
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String value = scoreList.toString();
        System.out.println(value);
    }
}