package game;

import game.data.Options;
import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

public class Game extends JFrame {
    private Display display;
    private GameMap gameMap;
    private Player player;
    private Menu menu;
    ScheduledFuture<?> gameThread;
    private final int tileSize;
    private boolean won;

    public Game() {
        super("PacMan");

        tileSize = Options.getTileSize();

        menu = new Menu(this);

        display = new Display(this);
        player = new Player(this, 13.5, 10.5, 0.375, Options.getPlayerSpeed());

        gameMap = new GameMap(tileSize);

        int width = gameMap.getWidth() * (tileSize + 1) - 10;
        int height = gameMap.getHeight() * (tileSize + 1) + 14;
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Game();
    }

    private void reset() {
        won = false;
        gameMap.reset();
        player.reset();
    }

    public void startGameLoop() {
        setVisible(true);

        addKeyListener(player);

        gameThread = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            tick();
            display.repaint();
        }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS);
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    private void tick() {
        if (won) {
            menu.setVisible(true);
            setVisible(false);
            removeKeyListener(player);
            gameThread.cancel(true);
            reset();
        }
        player.tick();
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(Options.getBackgroundColor());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        gameMap.render(g2d, tileSize);
        player.render(g2d, tileSize);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Display getDisplay() {
        return display;
    }

}
