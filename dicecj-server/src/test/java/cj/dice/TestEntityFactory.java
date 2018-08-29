package cj.dice;

import cj.dice.entity.Die;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class TestEntityFactory {
    public static List<Die> createDice(int v1, int v2, int v3, int v4, int v5) {
        Die d1 = new Die();
        d1.setValue(v1);
        Die d2 = new Die();
        d2.setValue(v2);
        Die d3 = new Die();
        d3.setValue(v3);
        Die d4 = new Die();
        d4.setValue(v4);
        Die d5 = new Die();
        d5.setValue(v5);

        List<Die> dice = new ArrayList<>();
        dice.add(d1);
        dice.add(d2);
        dice.add(d3);
        dice.add(d4);
        dice.add(d5);
        return dice;
    }

    public static <T extends Score> T createScore(Class<T> type, int value, int index) {
        T score = null;
        try {
            score = type.newInstance();
            score.setValue(value);
            score.setIndex(index);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static List<Score> createOpenScores(Score... scores) {
        List<Score> openScores = new ArrayList<>();
        for (Score s : scores) {
            openScores.add(s);
        }
        return openScores;
    }

    public static TreeSet<Score> createClosedScores(Score... scores) {
        TreeSet<Score> closedScores = new TreeSet<>();
        for (Score s : scores) {
            closedScores.add(s);
        }
        return closedScores;
    }

    public static Scoreboard createScoreboard(List<Score> openScores, TreeSet<Score> closedScores) {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setOpenScores(openScores);
        scoreboard.setClosedScores(closedScores);
        return scoreboard;
    }
}
