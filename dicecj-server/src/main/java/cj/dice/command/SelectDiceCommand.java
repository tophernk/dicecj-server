package cj.dice.command;

import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.service.CoreService;

import javax.ejb.Stateless;

@Stateless
public class SelectDiceCommand implements InputCommand {
    @Override
    public String execute(String userInput, Game game) {
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            int numericValue = Character.getNumericValue(chars[x]);
            if (numericValue > 0 && numericValue <= CoreService.NUMBER_OF_DICE) {
                game.getDice().get(numericValue - 1).toggleLock();
            }
        }
        String result = "";
        for (Die d : game.getDice()) {
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