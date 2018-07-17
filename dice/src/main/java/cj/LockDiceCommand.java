package cj;

import java.util.List;

public class LockDiceCommand implements InputCommand {
    @Override
    public void execute(Player player, List<Die> dice, String userInput) {
        dice.forEach(Die::unlock);
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            dice.get(Character.getNumericValue(chars[x]) - 1).lock();
        }
        dice.forEach(System.out::print);
        System.out.println();
    }

    @Override
    public boolean isTurnEndCommand() {
        return false;
    }

    @Override
    public boolean isExecutable(String userInput, int numberOfRolls) {
        return userInput.matches("[0-9]+");
    }

    @Override
    public String retrieveInstructions() {
        return "die number(s) to lock di(c)e";
    }

    @Override
    public boolean isRoll() {
        return false;
    }

}