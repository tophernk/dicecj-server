package cj;

import java.util.List;

public class CancelCommand implements InputCommand {

    private Promptable baseCommand;

    public CancelCommand(Promptable baseCommand) {
        this.baseCommand = baseCommand;
    }
    @Override
    public void execute(Scoreboard scoreboard, List<Die> dice, String userInput, int numberOfRolls) throws InputException {
        String userInputFromPrompt = baseCommand.prompt(scoreboard, dice);
        if (userInputFromPrompt.equals("c")) {
            throw new InputException("command has been canceled");
        }
        baseCommand.execute(scoreboard, dice, userInputFromPrompt, numberOfRolls);
    }

    @Override
    public boolean isTurnEndCommand() {
        return baseCommand.isTurnEndCommand();
    }

    @Override
    public boolean isTrigger(String userInput) {
        return baseCommand.isTrigger(userInput);
    }

    @Override
    public String retrieveInstructions() {
        return baseCommand.retrieveInstructions() + " [cancelable]";
    }

    @Override
    public boolean isRoll() {
        return baseCommand.isRoll();
    }

}
