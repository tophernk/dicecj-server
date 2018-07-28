package cj;

import java.util.List;

public class ShowScoreboardCommand implements InputCommand {
    @Override
    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput) {
        System.out.println(scoreboard);
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean isExecutable(String userInput, int numberOfRolls) {
        return userInput.equals("p");
    }

    @Override
    public String retrieveInstructions() {
        return "[p] print scoreboard";
    }

    @Override
    public boolean isRoll() {
        return false;
    }
}
