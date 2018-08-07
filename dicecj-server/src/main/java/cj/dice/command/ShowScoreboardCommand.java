package cj.dice.command;

import cj.dice.service.ScoreboardSerivce;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ShowScoreboardCommand implements InputCommand {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    @Override
    public String execute(String userInput, Turn turn) {
        return scoreboardSerivce.printScores(turn.getScoreboard());
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
