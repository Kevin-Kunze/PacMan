package game.data;

import java.awt.*;
import java.util.Arrays;

public class Option {
    //colors for game
    public static final Color PLAYER_COLOR = Color.YELLOW;
    public static final Color[] ENEMY_COLOR = { Color.RED, Color.ORANGE, Color.GREEN};
    public static final Color BLOCK_COLOR = Color.BLUE;
    public static final Color DOT_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color WON_COLOR = Color.GREEN;
    public static final Color LOST_COLOR = Color.RED;

    public static final int TILE_SIZE = 32; //calculates how big the window is
    public static final double GAME_SPEED = 0.07; //how fast the Creatures move

    public static final String SCORE_LIST_JSON = "scoreList.json"; //where scoreList

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
                ", FILE_PATH='" + SCORE_LIST_JSON + '\'' +
                '}';
    }
}
