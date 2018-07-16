package cj;

import java.util.List;

public class ShowScoreboardCommand implements InputCommand {
    @Override
    public void execute(Player player, List<Die> dice, String userInput) {
        System.out.println(player.getScoreboard());
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean evaluate(String userInput) {
        return userInput.equals("p");
    }

    @Override
    public String retrieveInstructions() {
        return "p to print scoreboard";
    }
}
