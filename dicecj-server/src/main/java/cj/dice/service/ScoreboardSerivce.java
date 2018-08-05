package cj.dice.service;

import cj.dice.Die;
import cj.dice.entity.*;

import javax.ejb.Stateless;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class ScoreboardSerivce {

    private static final int BONUS_VALUE = 35;

    public List<Scoreboard> findScoreboards() {
        return null;
    }

    public boolean addScore(Scoreboard scoreboard, Score score, List<Die> dice) {
        if (!scoreboard.getOpenScores().contains(score)) {
            return false;
        }
        scoreboard.getClosedScores().add(score);
        scoreboard.getOpenScores().remove(score);
        score.setValue(score.evaluate(dice));
        return true;
    }

    public int getTotal(Scoreboard scoreboard) {
        int total = 0;
        for (Score score : scoreboard.getClosedScores()) {
            total += score.getValue();
        }
        return total + calculateBonus(scoreboard);
    }

    public void printScores(Scoreboard scoreboard) {
        SortedSet<Score> scores = new TreeSet<>(Comparator.comparingInt(Score::getIndex));
        scores.addAll(scoreboard.getOpenScores());
        scores.addAll(scoreboard.getClosedScores());

        StringBuilder result = new StringBuilder();

        scores.forEach((s) -> result.append(s.getName())
                .append(": ")
                .append(s.getValue())
                .append("\n"));
        result.append("---------\nBonus: ")
                .append(calculateBonus(scoreboard) + " (" + calculateCurrentDiffToRegularBonus(scoreboard) + ")")
                .append("\n---------\n")
                .append("Total: ")
                .append(getTotal(scoreboard));

        System.out.println(result.toString());
    }

    public Scoreboard buildScoreboard(Player player) {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.setPlayer(player);
        scoreboard.setDate(new Date());

        scoreboard.setClosedScores(new TreeSet<>());
        scoreboard.setOpenScores(new ArrayList<>());

        scoreboard.getOpenScores().add(new OneFaceScore());
        scoreboard.getOpenScores().add(new TwoFaceScore());
        scoreboard.getOpenScores().add(new ThreeFaceScore());
        scoreboard.getOpenScores().add(new FourFaceScore());
        scoreboard.getOpenScores().add(new FiveFaceScore());
        scoreboard.getOpenScores().add(new SixFaceScore());
        scoreboard.getOpenScores().add(new OfAKindScore(3, "Three of a kind"));
        scoreboard.getOpenScores().add(new OfAKindScore(4, "Four of a kind"));
        scoreboard.getOpenScores().add(new FullHouse());
        scoreboard.getOpenScores().add(new Straight(4, 30, "Small Straight"));
        scoreboard.getOpenScores().add(new Straight(5, 40, "Straight"));
        scoreboard.getOpenScores().add(new OfAKindScore(5, 50, "Five of a kind"));
        scoreboard.getOpenScores().add(new Chance());

        scoreboard.getOpenScores().forEach(s -> s.setIndex(scoreboard.getOpenScores().indexOf(s)));

        return  scoreboard;
    }

    public void printOneLine(Scoreboard scoreboard) {
        System.out.println("Date: " + DateFormat.getDateInstance(DateFormat.SHORT).format(scoreboard.getDate())
                + " Player: " + scoreboard.getPlayer().getName() + " Total: " + getTotal(scoreboard));
    }

    public int calculateBonus(Scoreboard scoreboard) {
        SortedSet<Score> scores = new TreeSet<>(Comparator.comparingInt(Score::getIndex));
        if (scoreboard.getOpenScores() != null) {
            scores.addAll(scoreboard.getOpenScores());
        }
        scores.addAll(scoreboard.getClosedScores());

        int upperTotal = 0;
        int requiredBonusValue = 0;

        Set<Score> allFaceValueScores = scores.stream().filter(s -> s instanceof FaceValueOfAKindScore).collect(Collectors.toSet());
        for (Score s : allFaceValueScores) {
            requiredBonusValue += ((FaceValueOfAKindScore) s).getFaceValue() * 3;
            upperTotal += s.getValue();
        }
        return upperTotal >= requiredBonusValue ? BONUS_VALUE : 0;
    }

    public int calculateCurrentDiffToRegularBonus(Scoreboard scoreboard) {
        Set<Score> closedFaceValueScores = scoreboard.getClosedScores().stream().filter(s -> s instanceof FaceValueOfAKindScore).collect(Collectors.toSet());
        int currentDiffToRegularBons = 0;
        for (Score score : closedFaceValueScores) {
            currentDiffToRegularBons += score.getValue() - ((FaceValueOfAKindScore) score).getFaceValue() * 3;
        }
        return currentDiffToRegularBons;
    }

    public List<Scoreboard> findScoreboardsByPlayer(Player player) {
        List<Scoreboard> scoreboardsByPlayer = new ScoreboardDao().findScoreboardsByPlayer(player);
        scoreboardsByPlayer.sort((s1, s2) -> Integer.compare(getTotal(s1), getTotal(s2)) * -1);
        return scoreboardsByPlayer;
    }

    public void printAndHighlightScoreboardByPlayer(Scoreboard scoreboard, Player player) {
        List<Scoreboard> topScoreboardsByPlayer = findScoreboardsByPlayer(player);
        for (Scoreboard sb : topScoreboardsByPlayer) {
            if (sb.getId() == scoreboard.getId()) {
                System.out.print("--> ");
            }
            System.out.print(topScoreboardsByPlayer.indexOf(sb) + " ");
            printOneLine(sb);
        }
    }

    public void save(Scoreboard scoreboard) {
        new ScoreboardDao().create(scoreboard);
    }
}
