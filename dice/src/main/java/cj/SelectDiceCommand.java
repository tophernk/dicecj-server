package cj;

import java.util.List;

public class SelectDiceCommand implements InputCommand {
    @Override
    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) {
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            int numericValue = Character.getNumericValue(chars[x]);
            if (numericValue > 0 && numericValue <= CoreService.NUMBER_OF_DICE) {
                dice.get(numericValue - 1).toggleLock();
            }
        }
        dice.forEach(System.out::print);
        System.out.println();
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean isTrigger(String userInput) {
        return userInput.matches("[0-9]+");
    }

    @Override
    public String retrieveInstructions() {
        return "[die number(s)] select di(c)e";
    }

    @Override
    public boolean isRoll() {
        return false;
    }

}