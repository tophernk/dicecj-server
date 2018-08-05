package cj.dice.command;

import cj.dice.service.ScoreboardSerivce;
import cj.dice.service.ServiceSupport;
import cj.dice.service.TurnState;

import javax.inject.Inject;

public class ShowScoreboardCommand implements InputCommand {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    @Override
    public void execute(String userInput, TurnState turnState) {
        scoreboardSerivce.printScores(turnState.getScoreboard());
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
