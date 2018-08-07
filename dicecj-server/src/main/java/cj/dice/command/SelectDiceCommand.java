package cj.dice.command;

import cj.dice.service.CoreService;
import cj.dice.entity.Turn;

public class SelectDiceCommand implements InputCommand {
    @Override
    public void execute(String userInput, Turn turn) {
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            int numericValue = Character.getNumericValue(chars[x]);
            if (numericValue > 0 && numericValue <= CoreService.NUMBER_OF_DICE) {
                turn.getDice().get(numericValue - 1).toggleLock();
            }
        }
        turn.getDice().forEach(System.out::print);
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