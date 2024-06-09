package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import game.data.Language;
import game.data.Option;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Menu extends JFrame {
    private final Game game;
    Gson gson; //read, load JSON files
    private JButton buttonStartGame;
    private JPanel display;
    private JLabel labelTitle;
    private JButton buttonLanguage;
    private JLabel labelScoreList;
    private JTextField textFieldName;
    private JButton buttonClearScoreList;
    private JLabel labelScoreTime;
    private ScoreList scoreList; //scoreList on runtime
    private int language; //which language is displayed
    private boolean againScreen; //if menu is displayed after game is finished
    private boolean won; //if finished game was won

    /**
     * @param game   JFrame, where game is played
     * @param width  width of window
     * @param height height of window
     */
    public Menu(Game game, int width, int height) {
        //initialize JFrame
        super(Language.getTitle());

        this.game = game;
        language = 0; //DEFAULT value
        againScreen = false; //as it isn't opened

        //set labelTitle
        Font fontTitle = new Font("Title", Font.BOLD, 30);
        labelTitle.setText(Language.getTitle());
        labelTitle.setFont(fontTitle);

        //add action listener
        buttonStartGame.addActionListener(_ -> startGame());
        buttonLanguage.addActionListener(_ -> switchLanguage());
        buttonClearScoreList.addActionListener(_ -> clearScoreList());

        //initialize gson
        gson = new GsonBuilder().setPrettyPrinting().create();

        loadScoreList();
        setText();

        //set JFrame attributes
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(display);

        //add window listener (when closing)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                saveScoreList();
            }
        });

        //show menu
        activate();
    }

    /**
     * load scoreList from JSON File
     */
    public void loadScoreList() {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(Option.SCORE_LIST_JSON));
            scoreList = gson.fromJson(jsonReader, ScoreList.class);
        } catch (FileNotFoundException e) {
            //initialize scoreList empty, if no file is found
            scoreList = new ScoreList();
        }
    }

    /**
     * save scoreList to JSON File
     */
    public void saveScoreList() {
        try {
            FileWriter fileWriter = new FileWriter(Option.SCORE_LIST_JSON);
            gson.toJson(scoreList, fileWriter);

            //write file
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, Language.getError(language) + "\n"
                    + e.getMessage());
        }
    }

    /**
     * delete all elements from scoreList
     */
    private void clearScoreList() {
        scoreList.clear();
        setLabelScoreList(); //presumably to empty string
    }

    /**
     * check if player name is valid,
     * hide this window,
     * start game loop
     */
    private void startGame() {
        //checks if player name is empty, if some common html symbols are in the player name, if the length is valid
        if (textFieldName.getText().isEmpty()
                || textFieldName.getText().contains("\"")
                || textFieldName.getText().contains("<")
                || textFieldName.getText().contains(">")
                || textFieldName.getText().length() > 12) {
            JOptionPane.showMessageDialog(this, Language.getError(language) + "\n"
                    + Language.getInvalidPlayerName(language));
            return;
        }

        //close menu, start game
        game.startGameLoop();
        setVisible(false);
    }

    /**
     * add to scoreList,
     * update UI
     *
     * @param won if game was won
     */
    public void open(boolean won) {
        this.won = won;
        againScreen = true; //as menu is opened another time
        scoreList.add(textFieldName.getText(), game.getGameMap().getScore(), game.getTime()); //add to scoreList

        //update UI
        setPlayAgainText();
        setLabelScoreList();

        activate(); //make menu visible
    }

    /**
     * update static UI
     */
    private void setText() {
        buttonLanguage.setText(Language.getLanguageName(language));
        buttonStartGame.setText(Language.getPlay(language));
        buttonClearScoreList.setText(Language.getClearScoreList(language));
        setLabelScoreList();
    }

    /**
     * update dynamic UI,
     * should only be called, when a game is finished
     */
    private void setPlayAgainText() {
        //set text according to won state
        if (won) {
            labelTitle.setText(Language.getWon(language));
            labelTitle.setForeground(Option.WON_COLOR);
        } else {
            labelTitle.setText(Language.getLost(language));
            labelTitle.setForeground(Option.LOST_COLOR);
        }

        labelScoreTime.setText(Language.getScore(language) + ": " + game.getGameMap().getScore() + " "
                + Language.getTime(language) + ": " + game.getTime());
        buttonStartGame.setText(Language.getPlayAgain(language));
    }

    /**
     * set labelScoreList to content of scoreList in HTML format
     */
    private void setLabelScoreList() {
        if (scoreList.isEmpty()) {
            labelScoreList.setText("");
            return;
        }
        labelScoreList.setText(scoreList.getAsString(Language.getName(language), Language.getScore(language),
                Language.getTime(language)));
    }

    /**
     * make visible,
     * (possible to add other functionality)
     */
    private void activate() {
        setVisible(true);
    }

    /**
     * change Language
     */
    private void switchLanguage() {
        if (++language >= Language.COUNT) language = 0; //increment language in circle
        setText();
        if (againScreen) setPlayAgainText();
    }

    public int getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Menu menu = (Menu) object;
        return language == menu.language && againScreen == menu.againScreen && won == menu.won
                && Objects.equals(buttonStartGame.getText(), menu.buttonStartGame.getText())
                && Objects.equals(labelTitle.getText(), menu.labelTitle.getText())
                && Objects.equals(buttonLanguage.getText(), menu.buttonLanguage.getText())
                && Objects.equals(labelScoreList.getText(), menu.labelScoreList.getText())
                && Objects.equals(textFieldName.getText(), menu.textFieldName.getText())
                && Objects.equals(buttonClearScoreList.getText(), menu.buttonClearScoreList.getText())
                && Objects.equals(labelScoreTime.getText(), menu.labelScoreTime.getText())
                && Objects.equals(scoreList, menu.scoreList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buttonStartGame, display, labelTitle, buttonLanguage, labelScoreList, textFieldName, buttonClearScoreList, labelScoreTime, scoreList, language, againScreen, won);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "buttonStartGame=" + buttonStartGame +
                ", display=" + display +
                ", labelTitle=" + labelTitle +
                ", buttonLanguage=" + buttonLanguage +
                ", labelScoreList=" + labelScoreList +
                ", textFieldName=" + textFieldName +
                ", buttonClearScoreList=" + buttonClearScoreList +
                ", labelScoreTime=" + labelScoreTime +
                ", scoreList=" + scoreList +
                ", language=" + language +
                ", againScreen=" + againScreen +
                ", won=" + won +
                '}';
    }
}
