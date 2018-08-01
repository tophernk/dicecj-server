package cj;

import java.util.List;

public interface InputCommand {

    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) throws InputException;

    public boolean isTurnEndCommand();

    public boolean isTrigger(String userInput);

    public String retrieveInstructions();

    public boolean isRoll();
}
