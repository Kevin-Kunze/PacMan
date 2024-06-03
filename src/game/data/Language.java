package game.data;

public class Language {
    public static int COUNT = 2;

    private static final String title = "PacMan";
    private static final String[] error = new String[]{"Error!", "Fehler!"};
    private static final String[] languageName = new String[]{"English", "Deutsch"};
    private static final String[] play = new String[]{"Play", "Spielen"};
    private static final String[] playAgain = new String[]{"Play Again", "Nochmal spielen"};
    private static final String[] won = new String[]{"You Won!", "Du hast gewonnen!"};
    private static final String[] lost = new String[]{"You Lost!", "Du hast verloren!"};
    private static final String[] score = new String[]{"Score", "Punktzahl"};
    private static final String[] time = new String[]{"Time", "Zeit"};
    private static final String[] name = new String[]{"Name", "Name"};
    private static final String[] clearScoreList = new String[]{"Clear Scoreboard", "Rangliste löschen"};
    private static final String[] noPlayerName = new String[]{"Player has no name.", "Spieler hat keinen Namen."};

    public static String getError(int language) {
        if (language < 0 || language >= COUNT) return error[0];
        return error[language];
    }

    public static String getLanguageName(int language) {
        if (language < 0 || language >= COUNT) return languageName[0];
        if(++language >= COUNT) language = 0;
        return languageName[language];
    }

    public static String getTitle() {
        return title;
    }

    public static String getPlay(int language) {
        if (language < 0 || language >= COUNT) return play[0];
        return play[language];
    }

    public static String getPlayAgain(int language) {
        if (language < 0 || language >= COUNT) return playAgain[0];
        return playAgain[language];
    }

    public static String getWon(int language) {
        if (language < 0 || language >= COUNT) return won[0];
        return won[language];
    }

    public static String getLost(int language) {
        if (language < 0 || language >= COUNT) return lost[0];
        return lost[language];
    }

    public static String getScore(int language) {
        if (language < 0 || language >= COUNT) return score[0];
        return score[language];
    }

    public static String getTime(int language) {
        if (language < 0 || language >= COUNT) return time[0];
        return time[language];
    }

    public static String getName(int language) {
        if (language < 0 || language >= COUNT) return name[0];
        return name[language];
    }

    public static String getClearScoreList(int language) {
        if (language < 0 || language >= COUNT) return clearScoreList[0];
        return clearScoreList[language];
    }

    public static String getNoPlayerName(int language) {
        if (language < 0 || language >= COUNT) return noPlayerName[0];
        return noPlayerName[language];
    }
}
