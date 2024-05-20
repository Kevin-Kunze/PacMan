package game;

import javax.swing.*;

public class Menu extends JFrame {
    private Game game;
    private JButton buttonStartGame;
    private JPanel display;

    public Menu(Game game, int width, int height) {
        this.game = game;
        buttonStartGame.addActionListener(e -> close());

        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().add(display);
    }

    private void close() {
        setVisible(false);
        game.startGameLoop();
    }

    public void open() {
        setVisible(true);
    }

    public JPanel getDisplay() {
        return display;
    }
}
