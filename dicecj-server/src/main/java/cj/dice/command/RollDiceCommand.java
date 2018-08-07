package cj.dice.command;

import cj.dice.entity.Die;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;

@Stateless
public class RollDiceCommand implements InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Override
    public String execute(String userInput, Turn turn) {
        if (turn.getNumberOfRolls() >= ALLOWED_NUMBER_OF_ROLLS) {
            return "no more rolls left";
        }
        turn.getDice().forEach(Die::roll);
        String result = "";
        for (Die d : turn.getDice()) {
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
