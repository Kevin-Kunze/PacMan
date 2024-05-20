package game;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private final Game game;
    private JButton buttonStartGame;
    private JPanel display;
    private JLabel labelTitle;
    private JLabel labelScore;
    private JLabel labelTime;

    public Menu(Game game, int width, int height) {
        super("PacMan");
        this.game = game;

        Font fontTitle = new Font("Title", Font.BOLD, 30);
        labelTitle.setText("PacMan");
        labelTitle.setFont(fontTitle);
        buttonStartGame.setText("Play");
        buttonStartGame.addActionListener(_ -> close());

        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(display);
        setVisible(true);
    }

    private void close() {
        setVisible(false);
        game.startGameLoop();
    }

    public void open(boolean won, int score, int time) {
        if (won) {
            labelTitle.setText("You Won!");
            labelTitle.setForeground(Color.GREEN);
        } else {
            labelTitle.setText("You Lost!");
            labelTitle.setForeground(Color.RED);
        }
        labelScore.setText("Score: " + score);
        labelTime.setText("Time: " + time);
        buttonStartGame.setText("Play Again");
        setVisible(true);
    }
}
