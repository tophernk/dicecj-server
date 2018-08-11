package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;

public interface InputCommand {

    public String execute(String userInput, Game game) throws InputException;

    public boolean isTurnEndCommand();

    public boolean isTrigger(String userInput);

    public String retrieveInstructions();

    public boolean isRoll();
}
