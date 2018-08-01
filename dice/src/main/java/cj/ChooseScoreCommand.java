package cj;

import java.util.List;
import java.util.Scanner;

public class ChooseScoreCommand implements Promptable {
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
    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) throws InputException {
        try {
            List<Score> scoringOptions = scoreboard.getOpenScores();
            ServiceSupport.getScoreboardSerivce().addScore(scoreboard, scoringOptions.get(Integer.valueOf(userInput)), dice);
            dice.forEach(Die::reset);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InputException("reset score selection: no score has been added to the scoreboard");
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
        return "[s] put score on scoreboard";
    }

    @Override
    public boolean isRoll() {
        return false;
    }
}
