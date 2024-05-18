package game;

import javax.swing.*;

public class Menu {
    private Game game;
    private JButton btnStartGame;
    private JPanel display;

    public Menu(Game game) {
        this.game = game;
        btnStartGame.addActionListener(e -> close());
    }

    private void close() {
        display.setVisible(false);
        game.startGameLoop();
    }

    public JPanel getDisplay() {
        return display;
    }
}
