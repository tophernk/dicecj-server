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
        setTrigger("n:");
    }

    @Override
    public String execute(String userInput, Game game) throws InputException {
        Player player = playerService.findOrCreatePlayer(extractPlayerName(userInput));
        Game newGgame = coreService.initNewGame(player);
        String result = coreService.retrieveInstructions();
        return coreService.buildResult(newGgame, result);
    }

    private String extractPlayerName(String userInput) throws InputException {
        String[] split = splitInputByWhiteSpaces(userInput);
        if (split.length > 1) {
            return split[1];
        } else {
            throw new InputException("invalid input");
        }
    }

    @Override
    public String retrieveInstructions() {
        return "[" + getTrigger() + "playerName] new game";
    }

}
