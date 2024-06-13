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
    private int time;
    private boolean won;
    private boolean gameEnded;

    public Game() {
        //initialize JFrame
        super(Language.getTitle());

        //initialize objects
        display = new Display(this);
        player = new Player(this, 13.5, 10.5, 0.375, Option.GAME_SPEED);
        gameMap = new GameMap();
        enemies = new Enemy[] {
                new ChasingEnemy(this, player, 12.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[0]),
                new CuttingEnemy(this, player, 13.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[1]),
                new RandomEnemy(this, player, 14.5, 8.5, 0.375, Option.GAME_SPEED * 0.85, Option.ENEMY_COLOR[2])
        };

        //calculate width and height
        int width = gameMap.getWidth() * (Option.TILE_SIZE + 1) - 10;
        int height = gameMap.getHeight() * (Option.TILE_SIZE + 1) + 14;

        //initialize menu
        menu = new Menu(this, width, height);

        //set JFrame attributess
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //add window listener (when closing)
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

    /**
     * when game is finished, reset all attributes to before
     */
    private void reset() {
        won = false;
        gameEnded = false;
        gameMap.reset();
        player.reset();
        for (Enemy enemy: enemies) {
            enemy.reset();
        }
    }

    /**
     * ready attributes for game start
     */
    public void startGameLoop() {
        reset();
        addKeyListener(player); //activate control
        startTime();

        setVisible(true);

        gameThread = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            tick(); //tick calculations
            display.repaint(); //render
        }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS);
    }

    /**
     * start time with delay of 1s
     */
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
        //possibility to add further functionality
    }

    /**
     * calculate paths,
     * check if game has ended
     */
    private void tick() {
        if (won) wrapGame();
        if (time >= 999) loose();
        player.tick();
        for(Enemy enemy : enemies) {
            if(!gameEnded) enemy.tick();
        }
    }

    /**
     * ready game for opening menu,
     * open menu
     */
    private void wrapGame() {
        gameEnded = true;
        removeKeyListener(player);
        timer.cancel();
        gameThread.cancel(true);
        menu.open(won);
        setVisible(false);
    }

    /**
     * render all objects
     * @param g the graphics content in which to paint
     */
    public void render(Graphics2D g) {
        //render objects
        g.setColor(Option.BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        gameMap.render(g);
        player.render(g);
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }

        //set font and color for score and time
        g.setFont(new Font("DisplayFont", Font.BOLD, (int) (Option.TILE_SIZE / 1.5)));
        g.setColor(Color.WHITE);

        //display score
        g.drawString(Language.getScore(menu.getLanguage()) + ": " + gameMap.getScore(), Option.TILE_SIZE / 5,
                Option.TILE_SIZE / 3 + 14);

        //display time
        g.drawString(Language.getTime(menu.getLanguage()) + ": " + time,
                Option.TILE_SIZE * (gameMap.getWidth() - 3) - 10, Option.TILE_SIZE / 3 + 14);

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
        return time == game.time && won == game.won
                && Objects.equals(display, game.display) && Objects.equals(gameMap, game.gameMap)
                && Objects.equals(player, game.player) && Objects.equals(menu, game.menu)
                && Objects.deepEquals(enemies, game.enemies) && Objects.equals(timer, game.timer)
                && Objects.equals(gameThread, game.gameThread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(display, gameMap, player, menu, Arrays.hashCode(enemies), timer, gameThread, time, won);
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
                ", time=" + time +
                ", won=" + won +
                '}';
    }
}
