package game.data;

import java.awt.*;
import java.util.Arrays;

public class Option {
    public static final Color PLAYER_COLOR = Color.YELLOW;
    public static final Color[] ENEMY_COLOR = { Color.RED, Color.ORANGE, Color.GREEN};
    public static final Color BLOCK_COLOR = Color.BLUE;
    public static final Color DOT_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color WON_COLOR = Color.GREEN;
    public static final Color LOST_COLOR = Color.RED;
    public static final int TILE_SIZE = 32;
    public static final double GAME_SPEED = 0.07;

    public static final int MAX_SCORE_LIST = 12;

    public static final String FILE_PATH = "scoreList.json";

    @Override
    public String toString() {
        return "Options{" +
                "BACKGROUND_COLOR=" + BACKGROUND_COLOR +
                ", PLAYER_COLOR=" + PLAYER_COLOR +
                ", ENEMY_COLOR=" + Arrays.toString(ENEMY_COLOR) +
                ", BLOCK_COLOR=" + BLOCK_COLOR +
                ", DOT_COLOR=" + DOT_COLOR +
                ", WON_COLOR=" + WON_COLOR +
                ", LOST_COLOR=" + LOST_COLOR +
                ", TILE_SIZE=" + TILE_SIZE +
                ", GAME_SPEED=" + GAME_SPEED +
                ", MAX_SCORE_LIST=" + MAX_SCORE_LIST +
                ", FILE_PATH='" + FILE_PATH + '\'' +
                '}';
    }
}
