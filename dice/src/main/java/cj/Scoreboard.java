package cj;

import java.util.*;

public class Scoreboard {
    private static final int BONUS_VALUE = 35;
    private Map<Score, Integer> scoreList;

    public Scoreboard() {
        this.scoreList = new HashMap<>();

        scoreList.put(new FaceValueOfAKindScore(1, "Ones"), null);
        scoreList.put(new FaceValueOfAKindScore(2, "Twos"), null);
        scoreList.put(new FaceValueOfAKindScore(3, "Threes"), null);
        scoreList.put(new FaceValueOfAKindScore(4, "Fours"), null);
        scoreList.put(new FaceValueOfAKindScore(5, "Fives"), null);
        scoreList.put(new FaceValueOfAKindScore(6, "Sixes"), null);
        scoreList.put(new OfAKindScore(3, "Three of a kind"), null);
        scoreList.put(new OfAKindScore(4, "Four of a kind"), null);
        scoreList.put(new FullHouse(), null);
        scoreList.put(new Straight(4, 30, "Small Straight"), null);
        scoreList.put(new Straight(5, 40, "Big Straight"), null);
        scoreList.put(new OfAKindScore(5, "Five of a kind"), null);
        scoreList.put(new Chance(), null);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        scoreList.forEach((s, v) -> result.append(s.getName())
                .append(": ")
                .append(v)
                .append("\n"));
        result.append("Bonus: ")
                .append(calculateBonus())
                .append("\n")
                .append("Total: ")
                .append(getTotal())
                .append("\n");

        return result.toString();
    }

    public boolean addScore(Score score, List<Die> dice) {
        if (scoreList.get(score) != null) {
            return false;
        }
        scoreList.put(score, score.evaluate(dice));
        return true;
    }

    public boolean isComplete() {
        return scoreList.values().stream().allMatch(Objects::nonNull);
    }

    public List<Score> retrieveScoringOptions() {
        List<Score> scores = new ArrayList<>();
        scoreList.forEach((s, v) -> {
            if (v == null) {
                scores.add(s);
            }
        });
        return scores;
    }

    public int getTotal() {
        int total = 0;
        for (Integer value : scoreList.values()) {
            total += value;
        }
        return total + calculateBonus();
    }

    public int calculateBonus() {
        int upperTotal = 0;
        int requiredBonusValue = 0;
        for (Score s : scoreList.keySet()) {
            if (s instanceof FaceValueOfAKindScore) {
                requiredBonusValue += ((FaceValueOfAKindScore) s).getFaceValue() * 3;
                upperTotal += scoreList.get(s);
            }
        }
        return upperTotal >= requiredBonusValue ? BONUS_VALUE : 0;
    }
}
