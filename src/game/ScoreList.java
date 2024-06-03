package game;

import game.data.Options;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreList extends ArrayList<ScoreList.Score> {
    public ScoreList() {
        super();
    }

    public void add(String name, int value, int time) {
        super.add(new Score(name, value, time));
        sort(new ScoreComparator());
        if (size() > Options.MAX_SCORE_LIST) {
            for (int i = Options.MAX_SCORE_LIST - 1; i < size(); i++) {
                remove(i);
            }
        }
    }

    public String getAsString(String languageName, String languageScore, String languageTime) {
        StringBuilder stringBuilder = new StringBuilder("<html><table>");
        stringBuilder.append("<tr><th><h3>");
        stringBuilder.append(languageName);
        stringBuilder.append("</h3></th><th><h3>");
        stringBuilder.append(languageScore);
        stringBuilder.append("</h3></th><th><h3>");
        stringBuilder.append(languageTime);
        stringBuilder.append("</h3></th></tr>");
        for (Score score : this) {
            stringBuilder.append("<tr><th>");
            stringBuilder.append(score.name);
            stringBuilder.append("</th><th>");
            stringBuilder.append(score.value);
            stringBuilder.append("</th><th>");
            stringBuilder.append(score.time);
            stringBuilder.append("</th></tr>");
        }
        stringBuilder.append("</table></html>");
        return stringBuilder.toString();
    }

    public static class Score {
        private final String name;
        private final int value;
        private final int time;

        public Score(String name, int value, int time) {
            this.name = name;
            this.value = value;
            this.time = time;
        }
    }

    public static class ScoreComparator implements Comparator<Score> {

        @Override
        public int compare(Score score1, Score score2) {
            if (score1.value != score2.value) return Integer.compare(score2.value, score1.value);
            return Integer.compare(score1.time, score2.time);
        }
    }
}
