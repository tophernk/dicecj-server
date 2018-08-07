package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Turn;

public interface InputCommand {

    public void execute(String userInput, Turn turn) throws InputException;

    public boolean isTurnEndCommand();

    public boolean isTrigger(String userInput);

    public String retrieveInstructions();

    public boolean isRoll();
}
