package game;

import game.data.Language;
import game.data.Options;
import game.objects.creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
    private final Display display;
    private final GameMap gameMap;
    private final Player player;
    private Menu menu;
    private final int tileSize;
    private boolean won;

    public Game() {
        super("PacMan");

        tileSize = Options.getTileSize();

        display = new Display(this);
        gameMap = new GameMap(tileSize);
        player = new Player(this, 13.5, 10.5, 0.375, Options.getPlayerSpeed());
        addKeyListener(player);
        menu = new Menu(this);
        getContentPane().add(menu.getPanel());
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        int width = gameMap.getWidth() * (tileSize + 1) - 10;
        int height = gameMap.getHeight() * (tileSize + 1) + 14;
        setSize(width, height);
        setResizable(true); //DEBUG: change to false afterwards
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

    public static class Display extends JPanel {
        private final Game game;

        public Display(Game game) {
            super();
            this.game = game;
            game.add(this);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            game.render(g2d);
        }
    }
}
