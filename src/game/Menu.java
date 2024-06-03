package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import game.data.Language;
import game.data.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Menu extends JFrame {
    private final Game game;
    private JButton buttonStartGame;
    private JPanel display;
    private JLabel labelTitle;
    private JButton buttonLanguage;
    private JLabel labelScoreList;
    private JTextField textFieldName;
    private JButton buttonClearScoreList;
    private JLabel labelScoreTime;
    private ScoreList scoreList;
    private int language;
    private boolean againScreen;
    private boolean won;

    public Menu(Game game, int width, int height) {
        super(Language.getTitle());
        this.game = game;

        language = 0;
        againScreen = false;

        Font fontTitle = new Font("Title", Font.BOLD, 30);
        labelTitle.setText(Language.getTitle());
        labelTitle.setFont(fontTitle);
        buttonStartGame.addActionListener(_ -> close());
        buttonLanguage.addActionListener(_ -> switchLanguage());
        buttonClearScoreList.addActionListener(_ -> clearScoreList());

        loadScoreList();
        setText();

        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(display);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                saveScoreList();
            }
        });

        activate();
    }

    public void loadScoreList() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader jsonReader;
        try {
            jsonReader = new JsonReader(new FileReader(Options.FILE_PATH));
            scoreList = gson.fromJson(jsonReader, ScoreList.class);
        } catch (FileNotFoundException e) {
            scoreList = new ScoreList();
        }
    }

    public void saveScoreList() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(Options.FILE_PATH);
            gson.toJson(scoreList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, Language.getError(language) + "\n"
                    + e.getMessage());
        }
    }

    private void clearScoreList() {
        scoreList.clear();
        setLabelScoreList();
    }

    private void close() {
        if(textFieldName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, Language.getError(language) + "\n"
                    + Language.getNoPlayerName(language));
            return;
        }
        setVisible(false);
        game.startGameLoop();
    }

    public void open(boolean won) {
        this.won = won;
        againScreen = true;
        scoreList.add(textFieldName.getText(), game.getGameMap().getScore(), game.getTime());
        setPlayAgainText();
        setLabelScoreList();
        activate();
    }

    private void setText() {
        buttonLanguage.setText(Language.getLanguageName(language));
        buttonStartGame.setText(Language.getPlay(language));
        buttonClearScoreList.setText(Language.getClearScoreList(language));
        setLabelScoreList();
    }

    private void setPlayAgainText() {
        if (won) {
            labelTitle.setText(Language.getWon(language));
            labelTitle.setForeground(Options.WON_COLOR);
        } else {
            labelTitle.setText(Language.getLost(language));
            labelTitle.setForeground(Options.LOST_COLOR);
        }
        labelScoreTime.setText(Language.getScore(language) + ": " + game.getGameMap().getScore() + " "
                + Language.getTime(language) + ": " + game.getTime());
        buttonStartGame.setText(Language.getPlayAgain(language));
    }

    private void setLabelScoreList() {
        if (scoreList.isEmpty()) {
            labelScoreList.setText("");
            return;
        }
        labelScoreList.setText(scoreList.getAsString(Language.getName(language), Language.getScore(language),
                Language.getTime(language)));
    }

    private void activate() {
        setVisible(true);
    }

    private void switchLanguage() {
        if (++language >= Language.COUNT) language = 0;
        setText();
        if (againScreen) setPlayAgainText();
    }

    public int getLanguage() {
        return language;
    }
}
