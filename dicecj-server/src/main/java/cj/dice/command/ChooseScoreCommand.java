package cj.dice.command;

import cj.dice.entity.Die;
import cj.dice.InputException;
import cj.dice.entity.Score;
import cj.dice.entity.Scoreboard;
import cj.dice.service.ScoreboardSerivce;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Stateless
public class ChooseScoreCommand implements InputCommand {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    @Override
    public String execute(String userInput, Turn turn) throws InputException {
        if (turn.getDice().stream().anyMatch(d -> !d.isValid())) {
            throw new InputException("please (re)roll dice");
        }
        try {
            List<Score> scoringOptions = turn.getScoreboard().getOpenScores();
            Score score = scoringOptions.stream().filter(s -> s.getIndex() == Integer.valueOf(userInput.substring(1))).findAny().orElseThrow(IllegalArgumentException::new);
            scoreboardSerivce.addScore(turn.getScoreboard(), score, turn.getDice());
            turn.getDice().forEach(Die::reset);
            return score.getValue() + " added to " + score.getName();
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new InputException("invalid input");
        }
    }

    @Override
    public boolean isTurnEndCommand() {
        return true;
    }

    @Override
    public boolean isTrigger(String userInput) {
        return userInput.startsWith("s");
    }

    @Override
    public String retrieveInstructions() {
        return "[s] put entity on scoreboard";
    }

    @Override
    public boolean isRoll() {
        return false;
    }
}
