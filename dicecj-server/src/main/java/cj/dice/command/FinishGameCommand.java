package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;
import cj.dice.service.CoreService;
import cj.dice.service.ScoreboardSerivce;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FinishGameCommand extends InputCommand {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    public FinishGameCommand() {
        super("f");
    }

    @Override
    public String execute(String userInput, Game game) throws InputException {
        return scoreboardSerivce.printAndHighlightScoreboardsOfPlayer(game.getScoreboard());
    }

    @Override
    public String retrieveInstructions() {
        return "[f]inish game";
    }

}
