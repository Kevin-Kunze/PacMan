package game;

import game.data.Language;
import game.data.Options;
import game.objects.creatures.Player;
import game.objects.creatures.enemy.ChasingEnemy;
import game.objects.creatures.enemy.CuttingEnemy;
import game.objects.creatures.enemy.Enemy;
import game.objects.creatures.enemy.RandomEnemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.*;

public class Game extends JFrame {
    private final Display display;
    private final GameMap gameMap;
    private final Player player;
    private final Menu menu;
    private final Enemy[] enemies;
    Timer timer;
    ScheduledFuture<?> gameThread;
    private final int tileSize;
    private int time;
    private boolean won;

    public Game() {
        super("PacMan");

        tileSize = Options.TILE_SIZE;

        display = new Display(this);
        player = new Player(this, 13.5, 10.5, 0.375, Options.GAME_SPEED);
        gameMap = new GameMap();
        enemies = new Enemy[] {
                new ChasingEnemy(this, player, 12.5, 8.5, 0.375, Options.GAME_SPEED * 0.85, Options.ENEMY_COLOR[0]),
                new CuttingEnemy(this, player, 13.5, 8.5, 0.375, Options.GAME_SPEED * 0.85, Options.ENEMY_COLOR[1]),
                new RandomEnemy(this, player, 14.5, 8.5, 0.375, Options.GAME_SPEED * 0.85, Options.ENEMY_COLOR[2])
        };

        int width = gameMap.getWidth() * (tileSize + 1) - 10;
        int height = gameMap.getHeight() * (tileSize + 1) + 14;
        menu = new Menu(this, width, height);

        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                menu.saveScoreList();
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }

    private void reset() {
        won = false;
        gameMap.reset();
        player.reset();
        for (Enemy enemy: enemies) {
            enemy.reset();
        }
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
        timer.scheduleAtFixedRate(timerTask, 1000L, 1000L);
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void loose() {
        wrapGame();
    }

    private void tick() {
        if (won) wrapGame();
        if (time >= 999) loose();
        player.tick();
        for(Enemy enemy : enemies) {
            enemy.tick();
        }
    }

    private void wrapGame() {
        menu.open(won);
        setVisible(false);
        removeKeyListener(player);
        timer.cancel();
        gameThread.cancel(true);
        reset();
    }

    public void render(Graphics2D g) {
        g.setColor(Options.BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        gameMap.render(g, tileSize);
        player.render(g, tileSize);
        for (Enemy enemy : enemies) {
            enemy.render(g, tileSize);
        }

        //set font and color
        g.setFont(new Font("DisplayFont", Font.BOLD, (int) (tileSize / 1.5)));
        g.setColor(Color.WHITE);

        //display score
        g.drawString(Language.getScore(menu.getLanguage()) + ": " + gameMap.getScore(), tileSize / 5, tileSize / 3 + 14);

        //display time
        g.drawString(Language.getTime(menu.getLanguage()) + ": " + time, tileSize * (gameMap.getWidth() - 3) - 10, tileSize / 3 + 14);

    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public int getTime() {
        return time;
    }
}
