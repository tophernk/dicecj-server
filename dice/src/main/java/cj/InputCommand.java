package cj;

import java.util.List;

public interface InputCommand {

    public void execute(Player player, List<Die> dice, String userInput);

    public boolean isTurnEndCommand();

    public boolean isExecutable(String userInput, int numberOfRolls);

    public String retrieveInstructions();

    public boolean isRoll();
}
