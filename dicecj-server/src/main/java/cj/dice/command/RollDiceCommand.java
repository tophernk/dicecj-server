package cj.dice.command;

import cj.dice.Die;
import cj.dice.entity.Scoreboard;

import java.util.List;

public class RollDiceCommand implements InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Override
    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) {
        if (numberOfRolls >= ALLOWED_NUMBER_OF_ROLLS) {
            System.out.println("no more rolls left");
            return;
        }
        dice.forEach(Die::roll);
        dice.forEach(System.out::print);
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
