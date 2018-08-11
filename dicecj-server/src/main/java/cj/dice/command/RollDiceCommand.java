package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Die;
import cj.dice.entity.Game;

import javax.ejb.Stateless;

@Stateless
public class RollDiceCommand implements InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Override
    public String execute(String userInput, Game game) throws InputException {
        if (game.getCurrentNumberOfRolls() >= ALLOWED_NUMBER_OF_ROLLS) {
            throw new InputException("no more rolls left");
        }
        game.getDice().forEach(Die::roll);
        String result = "";
        for (Die d : game.getDice()) {
            result += d.toString();
        }
        return result;
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean isTrigger(String userInput) {
        return userInput.equals("r");
    }

    @Override
    public String retrieveInstructions() {
        return "[r] roll dice";
    }

    @Override
    public boolean isRoll() {
        return true;
    }
}
