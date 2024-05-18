package game;

import game.data.Language;
import game.data.Options;
import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
    private Display display;
    private GameMap gameMap;
    private Player player;
    private Menu menu;
    private final int tileSize;
    private boolean won;

    public Game() {
        super("PacMan");

        tileSize = Options.getTileSize();

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        menu = new Menu(this);
        getContentPane().add(menu.getDisplay());

        display = new Display(this);
        player = new Player(this, 13.5, 10.5, 0.375, Options.getPlayerSpeed());
        addKeyListener(player);
        display.setFocusable(true);

        gameMap = new GameMap(tileSize);

        int width = gameMap.getWidth() * (tileSize + 1) - 10;
        int height = gameMap.getHeight() * (tileSize + 1) + 14;
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
        try {
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                tick();
                display.repaint();
            }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, Language.getStrError());
        }

    }

    public void setWon(boolean won) {
        this.won = won;
    }

    private void tick() {
        if (won) {
            JOptionPane.showMessageDialog(null, "You Won!");
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
