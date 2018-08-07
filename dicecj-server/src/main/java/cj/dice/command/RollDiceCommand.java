package cj.dice.command;

import cj.dice.entity.Die;
import cj.dice.entity.Turn;

public class RollDiceCommand implements InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Override
    public void execute(String userInput, Turn turn) {
        if (turn.getNumberOfRolls() >= ALLOWED_NUMBER_OF_ROLLS) {
            System.out.println("no more rolls left");
            return;
        }
        turn.getDice().forEach(Die::roll);
        turn.getDice().forEach(System.out::print);
        System.out.println();
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
