package game;

import javax.swing.*;

public class Menu {
    private Game game;
    private JButton btnStartGame;
    private JPanel panel;

    public Menu(Game game) {
        this.game = game;
        game.getDisplay().setVisible(false);
        btnStartGame.addActionListener(e -> close());
    }

    private void close() {
        panel.setVisible(false);
        game.getDisplay().setVisible(true);
        game.startGameLoop();
    }

    public JPanel getPanel() {
        return panel;
    }
}
