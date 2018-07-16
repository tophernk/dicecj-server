package cj;

import java.util.List;
import java.util.Scanner;

public class ChooseScoreCommand implements InputCommand {
    @Override
    public void execute(Player player, List<Die> dice, String userInput) {
        List<Score> scoringOptions = player.getScoreboard().retrieveScoringOptions();
        for (Score s : scoringOptions) {
            System.out.println(s.getName() + ": " + s.evaluate(dice) + " [" + scoringOptions.indexOf(s) + "]");
        }
        userInput = new Scanner(System.in).next();
        player.getScoreboard().addScore(scoringOptions.get(Integer.valueOf(userInput)), dice);
    }

    @Override
    public boolean isTurnEndCommand() {
        return true;
    }

    @Override
    public boolean evaluate(String userInput) {
        return userInput.equals("s");
    }

    @Override
    public String retrieveInstructions() {
        return "s to put score on scoreboard";
    }

}
