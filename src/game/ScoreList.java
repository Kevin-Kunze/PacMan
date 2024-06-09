package game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class ScoreList extends ArrayList<ScoreList.Score> {
    public static final int MAX = 12;

    public ScoreList() {
        super();
    }

    /**
     * add a new Score to ScoreList,
     * sort,
     * trim down to 12 elements
     * @param name name of Score
     * @param value value of Score
     * @param time time of Score
     */
    public void add(String name, int value, int time) {
        super.add(new Score(name, value, time));
        sort();

        //remove the lowest score when size is over MAX_SCORE_LIST
        if (size() > MAX) {
            for (int i = MAX - 1; i < size(); i++) {
                remove(i);
            }
        }
    }

    public void sort() {
        super.sort(new ScoreComparator());
    }

    /**
     * @param languageName translation of name
     * @param languageScore translation of score
     * @param languageTime translation of time
     * @return HTML string containing all elements
     */
    public String getAsString(String languageName, String languageScore, String languageTime) {
        StringBuilder stringBuilder = new StringBuilder("<html><table><tr><th><h3>");
        stringBuilder.append(languageName).append("</h3></th><th><h3>");
        stringBuilder.append(languageScore).append("</h3></th><th><h3>");
        stringBuilder.append(languageTime).append("</h3></th></tr>");
        for (Score score : this) {
            stringBuilder.append("<tr><th>");
            stringBuilder.append(score.name).append("</th><th>");
            stringBuilder.append(score.value).append("</th><th>");
            stringBuilder.append(score.time).append("</th></tr>");
        }
        return stringBuilder.append("</table></html>").toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ScoreList{[");
        Iterator<Score> iterator = iterator();
        while(iterator.hasNext()) {
            stringBuilder.append(iterator.next());
            if(iterator.hasNext()) stringBuilder.append(", ");
        }
        return stringBuilder.append("]}").toString();
    }

    /**
     * used to save values regarding player score
     */
    public static class Score {
        private final String name;
        private final int value;
        private final int time;

        public Score(String name, int value, int time) {
            this.name = name;
            this.value = value;
            this.time = time;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Score score = (Score) object;
            return value == score.value && time == score.time && Objects.equals(name, score.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value, time);
        }

        @Override
        public String toString() {
            return "Score{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    ", time=" + time +
                    '}';
        }
    }

    /**
     * compare score:
     * value descending,
     * time ascending
     */
    public static class ScoreComparator implements Comparator<Score> {

        @Override
        public int compare(Score score1, Score score2) {
            if (score1.value != score2.value) return Integer.compare(score2.value, score1.value);
            return Integer.compare(score1.time, score2.time);
        }
    }
}
