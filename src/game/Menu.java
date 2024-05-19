package game;

import javax.swing.*;

public class Menu extends JFrame {
    private Game game;
    private JButton btnStartGame;
    private JPanel display;

    public Menu(Game game) {
        this.game = game;
        btnStartGame.addActionListener(e -> close());

        setSize(400, 300);
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
