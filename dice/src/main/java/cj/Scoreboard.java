package cj;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private List<Score> scoreList;

    public Scoreboard() {
        this.scoreList = new ArrayList<>();

        scoreList.add(new Ones());
        scoreList.add(new Twos());
        scoreList.add(new Threes());
        scoreList.add(new Fours());
        scoreList.add(new Fives());
        scoreList.add(new Sixes());
        scoreList.add(new ThreeOfAKind());
        scoreList.add(new FourOfAKind());
        scoreList.add(new FiveOfAKind());
    }

    public void evaluate(List<Die> dice) {
        scoreList.forEach(s -> System.out.println(s.getClass().getName() + " " + s.evaluate(dice)));
    }
}
