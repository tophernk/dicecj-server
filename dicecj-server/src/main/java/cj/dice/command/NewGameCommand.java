package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;
import cj.dice.entity.Player;
import cj.dice.service.CoreService;
import cj.dice.service.PlayerService;
import cj.dice.service.ScoreboardSerivce;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class NewGameCommand extends InputCommand {

    @Inject
    private PlayerService playerService;

    @Inject
    private CoreService coreService;

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    public NewGameCommand() {
        super("n");
    }

    @Override
    public String execute(String userInput, Game game) throws InputException {
        if (userInput == null) {
            throw new InputException("provide player name");
        }
        Player player = playerService.findOrCreatePlayer(userInput);
        Game newGame = coreService.initNewGame(player);
        String result = coreService.retrieveInstructions();
        return coreService.buildResult(newGame, result);
    }

    @Override
    public String retrieveInstructions() {
        return "[" + getTrigger() + ":playerName] new game";
    }

}
