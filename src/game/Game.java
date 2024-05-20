package game;

import game.data.Options;
import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class Game extends JFrame {
    private Display display;
    private GameMap gameMap;
    private Player player;
    private Menu menu;
    Timer timer;
    ScheduledFuture<?> gameThread;
    private final int tileSize;
    private int time;
    private boolean won;

    public Game() {
        super("PacMan");

        tileSize = Options.getTileSize();

        display = new Display(this);
        player = new Player(this, 13.5, 10.5, 0.375, Options.getPlayerSpeed());

        gameMap = new GameMap(tileSize);

        int width = gameMap.getWidth() * (tileSize + 1) - 10;
        int height = gameMap.getHeight() * (tileSize + 1) + 14;
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        menu = new Menu(this, width, height);
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

        startTime();

        gameThread = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            tick();
            display.repaint();
        }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS);
    }

    private void startTime() {
        time = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0L, 1000L);
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    private void tick() {
        if (won) {
            menu.setVisible(true);
            setVisible(false);
            removeKeyListener(player);
            timer.cancel();
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

        //set font and color
        g2d.setFont(new Font("DisplayFont", Font.BOLD, (int) (tileSize / 1.5)));
        g2d.setColor(Color.WHITE);

        //display score
        int score = gameMap.getInitialDotCount() - gameMap.getDotCount();
        g2d.drawString("Score: " + String.valueOf(score), (int) (tileSize / 5), tileSize / 3 + 14);

        //display time
        g2d.drawString("Time: " + String.valueOf(time), tileSize* (gameMap.getWidth() - 3), tileSize / 3 + 14);

    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Display getDisplay() {
        return display;
    }

    public int getTime() {
        return time;
    }

}
