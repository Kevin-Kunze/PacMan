package game.data;

import java.awt.*;

public class Options {
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
}
