package cj.dice.command;

import cj.dice.entity.Die;
import cj.dice.service.CoreService;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;

@Stateless
public class SelectDiceCommand implements InputCommand {
    @Override
    public String execute(String userInput, Turn turn) {
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            int numericValue = Character.getNumericValue(chars[x]);
            if (numericValue > 0 && numericValue <= CoreService.NUMBER_OF_DICE) {
                turn.getDice().get(numericValue - 1).toggleLock();
            }
        }
        String result = "";
        for (Die d : turn.getDice()) {
            result += d.toString();
        }
        return  result;
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