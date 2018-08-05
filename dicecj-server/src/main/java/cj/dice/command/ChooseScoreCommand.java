package cj.dice.command;

import cj.dice.Die;
import cj.dice.InputException;
import cj.dice.entity.Score;
import cj.dice.entity.Scoreboard;
import cj.dice.service.ScoreboardSerivce;
import cj.dice.service.ServiceSupport;
import cj.dice.service.TurnState;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

@Stateless
public class ChooseScoreCommand implements Promptable {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    @Override
    public String prompt(Scoreboard scoreboard, List<Die> dice) throws InputException {
        if (dice.stream().anyMatch(d -> !d.isValid())) {
            throw new InputException("please (re)roll dice");
        }
        List<Score> scoringOptions = scoreboard.getOpenScores();
        for (Score s : scoringOptions) {
            System.out.println(s.getName() + ": " + s.evaluate(dice) + " [" + scoringOptions.indexOf(s) + "]");
        }
        return new Scanner(System.in).next();
    }

    @Override
    public void execute(String userInput, TurnState turnState) throws InputException {
        try {
            List<Score> scoringOptions = turnState.getScoreboard().getOpenScores();
            scoreboardSerivce.addScore(turnState.getScoreboard(), scoringOptions.get(Integer.valueOf(userInput)), turnState.getDice());
            turnState.getDice().forEach(Die::reset);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InputException("reset entity selection: no entity has been added to the scoreboard");
        }
    }

    @Override
    public boolean isTurnEndCommand() {
        return true;
    }

    @Override
    public boolean isTrigger(String userInput) {
        return userInput.equals("s");
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
