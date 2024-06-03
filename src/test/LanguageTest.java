package test;

import game.data.Language;
import org.junit.jupiter.api.Test;

class LanguageTest {

    @Test
    void testToString() {
        Language language = new Language();
        String value = language.toString();
        System.out.println(value);
    }
}