package game.data;

import java.awt.*;

public class Options {
    private static final Color playerColor = Color.YELLOW;
    private static final Color blockColor = Color.BLUE;
    private static final Color dotColor = Color.WHITE;
    private static final Color backgroundColor = Color.BLACK;
    private static final int tileSize = 32;
    private static final double playerSpeed = 0.07;

    public static Color getPlayerColor () {
        return playerColor;
    }

    public static Color getBlockColor() {
        return blockColor;
    }

    public static Color getDotColor() {
        return dotColor;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static int getTileSize () {
        return tileSize;
    }

    public static double getPlayerSpeed () {
        return playerSpeed;
    }
}
