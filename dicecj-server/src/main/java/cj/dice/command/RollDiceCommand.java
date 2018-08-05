package cj.dice.command;

import cj.dice.Die;
import cj.dice.service.TurnState;

public class RollDiceCommand implements InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Override
    public void execute(String userInput, TurnState turnState) {
        if (turnState.getNumberOfRolls() >= ALLOWED_NUMBER_OF_ROLLS) {
            System.out.println("no more rolls left");
            return;
        }
        turnState.getDice().forEach(Die::roll);
        turnState.getDice().forEach(System.out::print);
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
