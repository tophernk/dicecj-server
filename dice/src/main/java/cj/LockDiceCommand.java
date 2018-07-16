package cj;

import java.util.List;

public class LockDiceCommand implements InputCommand {
    @Override
    public void execute(Player player, List<Die> dice, String userInput) {
        if (userInput.equals("r")) {
            return;
        }
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            dice.get(Character.getNumericValue(chars[x]) - 1).lock();
        }
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean evaluate(String userInput) {
        return userInput.equals("r")|| userInput.matches("[0-9]+");
    }

    @Override
    public String retrieveInstructions() {
        return "die number(s) to lock di(c)e || r to reroll all dice";
    }

}