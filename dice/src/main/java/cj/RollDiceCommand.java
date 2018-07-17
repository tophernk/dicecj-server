package cj;

import java.util.List;

public class RollDiceCommand implements InputCommand {

    private static final int ALLOWED_NUMBER_OF_ROLLS = 3;

    @Override
    public void execute(Player player, List<Die> dice, String userInput) {
        dice.forEach(Die::roll);
        dice.forEach(System.out::print);
        System.out.println();
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean isExecutable(String userInput, int numberOfRolls) {
        return userInput.equals("r") && numberOfRolls < ALLOWED_NUMBER_OF_ROLLS;
    }

    @Override
    public String retrieveInstructions() {
        return "r to roll dice";
    }

    @Override
    public boolean isRoll() {
        return true;
    }
}
