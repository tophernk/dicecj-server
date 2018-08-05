package cj.dice.command;

import cj.dice.Die;
import cj.dice.entity.Scoreboard;
import cj.dice.service.ServiceSupport;

import java.util.List;

public class ShowScoreboardCommand implements InputCommand {
    @Override
    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) {
        ServiceSupport.getScoreboardSerivce().printScores(scoreboard);
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean isTrigger(String userInput) {
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