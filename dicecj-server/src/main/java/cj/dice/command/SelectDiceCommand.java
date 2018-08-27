package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;
import cj.dice.service.CoreService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SelectDiceCommand extends InputCommand {

    @Inject
    CoreService coreService;

    public SelectDiceCommand() {
        super("sel");
    }

    @Override
    public String execute(String userInput, Game game) throws InputException {
        if (!userInput.matches("[1-5]{1,5}")) {
            throw new InputException("please select any of the five die by 1 to 5");
        }
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            int numericValue = Character.getNumericValue(chars[x]);
            if (numericValue > 0 && numericValue <= game.getDice().size()) {
                game.getDice().get(numericValue - 1).toggleLock();
            }
        }
        return coreService.printDice(game);
    }

    @Override
    public String retrieveInstructions() {
        return "[" + getTrigger() + ":dieNumber(s)] select di(c)e";
    }

}