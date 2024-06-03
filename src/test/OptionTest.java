package test;

import game.data.Option;
import org.junit.jupiter.api.Test;

class OptionTest {

    @Test
    void testToString() {
        Option option = new Option();
        String value = option.toString();
        System.out.println(value);
    }
}