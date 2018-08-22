package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.service.CoreService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RollDiceCommand extends InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Inject
    private CoreService coreService;

    public RollDiceCommand() {
        super("r");
    }

    @Override
    public String execute(String userInput, Game game) throws InputException {
        if (game.getCurrentNumberOfRolls() >= ALLOWED_NUMBER_OF_ROLLS) {
            throw new InputException("no more rolls left");
        }
        game.getDice().forEach(Die::roll);
        return coreService.printDice(game);
    }

    @Override
    public String retrieveInstructions() {
        return "[r]oll dice";
    }

    @Override
    public boolean isRoll() {
        return true;
    }
}
