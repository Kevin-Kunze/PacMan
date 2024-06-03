package game;

import game.data.Language;
import game.data.Option;
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
    private Timer timer;
    private ScheduledFuture<?> gameThread;
    private final int tileSize;
    private int time;
    private boolean won;

    public Game() {
        super("PacMan");

        tileSize = Option.TILE_SIZE;

        display = new Display(this);
        player = new Player(this, 13.5, 10.5, 0.375, Option.GAME_SPEED);
        gameMap = new GameMap();
        enemies = new Enemy[] {
                new ChasingEnemy(this, player, 12.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[0]),
                new CuttingEnemy(this, player, 13.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[1]),
                new RandomEnemy(this, player, 14.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[2])
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
        reset();
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
    }

    public void render(Graphics2D g) {
        g.setColor(Option.BACKGROUND_COLOR);
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

    public Menu getMenu() {
        return menu;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public int getTime() {
        return time;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Game game = (Game) object;
        return tileSize == game.tileSize && time == game.time && won == game.won
                && Objects.equals(display, game.display) && Objects.equals(gameMap, game.gameMap)
                && Objects.equals(player, game.player) && Objects.equals(menu, game.menu)
                && Objects.deepEquals(enemies, game.enemies) && Objects.equals(timer, game.timer)
                && Objects.equals(gameThread, game.gameThread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(display, gameMap, player, menu, Arrays.hashCode(enemies), timer, gameThread, tileSize, time, won);
    }

    @Override
    public String toString() {
        return "Game{" +
                "display=" + display +
                ", gameMap=" + gameMap +
                ", player=" + player +
                ", enemies=" + Arrays.toString(enemies) +
                ", timer=" + timer +
                ", gameThread=" + gameThread +
                ", tileSize=" + tileSize +
                ", time=" + time +
                ", won=" + won +
                '}';
    }
}
