package cj.dice.service;

import cj.dice.entity.Die;
import cj.dice.entity.*;
import cj.dice.entity.score.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class ScoreboardSerivce {

    @Inject
    private ScoreboardDao scoreboardDao;

    private static final int BONUS_VALUE = 35;

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

    public String printScores(Scoreboard scoreboard, List<Die> dice) {
        SortedSet<Score> scores = new TreeSet<>(Comparator.comparingInt(Score::getIndex));
        scores.addAll(scoreboard.getOpenScores());
        if (scoreboard.getClosedScores() != null) {
            scores.addAll(scoreboard.getClosedScores());
        }

        StringBuilder result = new StringBuilder();

        scores.forEach(s -> result
                .append(s.getIndex())
                .append(" ")
                .append(s.getName())
                .append(": ")
                .append(printSingleScore(scoreboard, dice, s))
                .append("\n"));
        result.append("---------\nBonus: ")
                .append(calculateBonus(scoreboard) + " (" + calculateCurrentDiffToRegularBonus(scoreboard) + ")")
                .append("\n---------\n")
                .append("Total: ")
                .append(getTotal(scoreboard));

        return result.toString();
    }

    private Object printSingleScore(Scoreboard scoreboard, List<Die> dice, Score s) {
        return scoreboard.getOpenScores().contains(s) ?
                printOpenScore(dice, s)
                : s.getValue();
    }

    private String printOpenScore(List<Die> dice, Score s) {
        return dice.stream().anyMatch(Die::isValid) ? "(" + s.evaluate(dice) + ")" : "-";
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
        scoreboard.getOpenScores().add(new ThreeOfAKind());
        scoreboard.getOpenScores().add(new FourOfAKind());
        scoreboard.getOpenScores().add(new FullHouse());
        scoreboard.getOpenScores().add(new SmallStraight());
        scoreboard.getOpenScores().add(new Straight());
        scoreboard.getOpenScores().add(new Yahtzee());
        scoreboard.getOpenScores().add(new Chance());

        scoreboard.getOpenScores().forEach(s -> s.setIndex(scoreboard.getOpenScores().indexOf(s)));

        return scoreboard;
    }

    public String printOneLine(Scoreboard scoreboard) {
        return scoreboard.getPlayer().getName() +
                " (" + DateFormat.getDateInstance(DateFormat.SHORT).format(scoreboard.getDate()) + "): " +
                getTotal(scoreboard) + "\n";
    }

    public int calculateBonus(Scoreboard scoreboard) {
        if (scoreboard.getClosedScores() == null) {
            return 0;
        }
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
        if (scoreboard.getClosedScores() == null) {
            return 0;
        }
        Set<Score> closedFaceValueScores = scoreboard.getClosedScores().stream().filter(s -> s instanceof FaceValueOfAKindScore).collect(Collectors.toSet());
        int currentDiffToRegularBons = 0;
        for (Score score : closedFaceValueScores) {
            currentDiffToRegularBons += score.getValue() - ((FaceValueOfAKindScore) score).getFaceValue() * 3;
        }
        return currentDiffToRegularBons;
    }

    public List<Scoreboard> findScoreboardsByPlayer(Player player) {
        List<Scoreboard> scoreboardsByPlayer = scoreboardDao.findScoreboardsByPlayer(player);
        scoreboardsByPlayer.sort((s1, s2) -> Integer.compare(getTotal(s1), getTotal(s2)) * -1);
        return scoreboardsByPlayer;
    }

    public String printAndHighlightScoreboardsOfPlayer(Scoreboard scoreboard) {
        List<Scoreboard> topScoreboardsByPlayer = findScoreboardsByPlayer(scoreboard.getPlayer());
        StringBuilder result = new StringBuilder();
        for (Scoreboard sb : topScoreboardsByPlayer) {
            if (sb.getId() == scoreboard.getId()) {
                result.append("--> ");
            }
            result.append(topScoreboardsByPlayer.indexOf(sb) + " ");
            result.append(printOneLine(sb));
        }
        return result.toString();
    }

    public void save(Scoreboard scoreboard) {
        scoreboardDao.create(scoreboard);
    }
}
