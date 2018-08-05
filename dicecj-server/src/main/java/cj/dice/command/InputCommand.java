package cj.dice.command;

import cj.dice.Die;
import cj.dice.InputException;
import cj.dice.entity.Scoreboard;

import java.util.List;

public interface InputCommand {

    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) throws InputException;

    public boolean isTurnEndCommand();

    public boolean isTrigger(String userInput);

    public String retrieveInstructions();

    public boolean isRoll();
}
