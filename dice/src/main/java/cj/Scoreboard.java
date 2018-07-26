package cj;

import javax.persistence.*;
import java.util.*;

@Entity
public class Scoreboard {
    private static final int BONUS_VALUE = 35;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SCOREBOARD_SCORE", joinColumns = @JoinColumn(name = "SCOREBOARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "SCORE_ID"))
    @OrderBy(value = "index")
    private SortedSet<Score> closedScores;

    @Transient
    private List<Score> openScores;

    public Scoreboard() {
        this.closedScores = new TreeSet<>(Comparator.comparingInt(Score::getIndex));
        this.openScores = new ArrayList<>();

        openScores.add(new FaceValueOfAKindScore(1, "Ones"));
        openScores.add(new FaceValueOfAKindScore(2, "Twos"));
        openScores.add(new FaceValueOfAKindScore(3, "Threes"));
        openScores.add(new FaceValueOfAKindScore(4, "Fours"));
        openScores.add(new FaceValueOfAKindScore(5, "Fives"));
        openScores.add(new FaceValueOfAKindScore(6, "Sixes"));
        openScores.add(new OfAKindScore(3, "Three of a kind"));
        openScores.add(new OfAKindScore(4, "Four of a kind"));
        openScores.add(new FullHouse());
        openScores.add(new Straight(4, 30, "Small Straight"));
        openScores.add(new Straight(5, 40, "Big Straight"));
        openScores.add(new OfAKindScore(5, 50, "Five of a kind"));
        openScores.add(new Chance());

        openScores.forEach(s -> s.setIndex(openScores.indexOf(s)));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        openScores.forEach((s) -> result.append(s.getName())
                .append(": ")
                .append(s.getValue())
                .append("\n"));
        result.append("---------\nBonus: ")
                .append(calculateBonus())
                .append("\n---------\n")
                .append("Total: ")
                .append(getTotal());

        return result.toString();
    }

    public boolean addScore(Score score, List<Die> dice) {
        if (!openScores.contains(score)) {
            return false;
        }
        closedScores.add(score);
        openScores.remove(score);
        score.setValue(score.evaluate(dice));
        return true;
    }

    public boolean isComplete() {
        return openScores.isEmpty();
    }

    public List<Score> retrieveScoringOptions() {
        return openScores;
    }

    public int getTotal() {
        int total = 0;
        for (Score score : closedScores) {
            total += score.getValue();
        }
        return total + calculateBonus();
    }

    public int calculateBonus() {
        int upperTotal = 0;
        int requiredBonusValue = 0;
        for (Score s : closedScores) {
            if (s instanceof FaceValueOfAKindScore) {
                requiredBonusValue += ((FaceValueOfAKindScore) s).getFaceValue() * 3;
                upperTotal += s.getValue();
            }
        }
        return upperTotal >= requiredBonusValue ? BONUS_VALUE : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
