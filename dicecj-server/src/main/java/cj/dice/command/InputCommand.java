package cj.dice.command;

import cj.dice.InputException;
import cj.dice.service.TurnState;

public interface InputCommand {

    public void execute(String userInput, TurnState turnState) throws InputException;

    public boolean isTurnEndCommand();

    public boolean isTrigger(String userInput);

    public String retrieveInstructions();

    public boolean isRoll();
}
