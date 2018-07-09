package cj;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private List<Score> scoreList;

    public Scoreboard() {
        this.scoreList = new ArrayList<>();

        scoreList.add(new FaceValueOfAKindScore(1, "Ones"));
        scoreList.add(new FaceValueOfAKindScore(2, "Twos"));
        scoreList.add(new FaceValueOfAKindScore(3, "Threes"));
        scoreList.add(new FaceValueOfAKindScore(4, "Fours"));
        scoreList.add(new FaceValueOfAKindScore(5, "Fives"));
        scoreList.add(new FaceValueOfAKindScore(6, "Sixes"));
        scoreList.add(new OfAKindScore(3, "Three of a kind"));
        scoreList.add(new OfAKindScore(4, "Four of a kind"));
        scoreList.add(new Straight(4, 30, "Small Straight"));
        scoreList.add(new Straight(5, 40, "Big Straight"));
        scoreList.add(new OfAKindScore(5, "Five of a kind"));
    }

    public void evaluate(List<Die> dice) {
        scoreList.forEach(s -> System.out.println(s.getName() + ": " + s.evaluate(dice)));
    }
}
