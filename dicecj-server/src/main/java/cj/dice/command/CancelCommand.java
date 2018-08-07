package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;

@Stateless
public class CancelCommand implements InputCommand {

    private Promptable baseCommand;

    public CancelCommand() {
    }

    public CancelCommand(Promptable baseCommand) {
        this.baseCommand = baseCommand;
    }
    @Override
    public String execute(String userInput, Turn turn) throws InputException {
        String userInputFromPrompt = baseCommand.prompt(turn.getScoreboard(), turn.getDice());
        if (userInputFromPrompt.equals("c")) {
            throw new InputException("command has been canceled");
        }
        return baseCommand.execute(userInputFromPrompt, turn);
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

    public Promptable getBaseCommand() {
        return baseCommand;
    }

    public void setBaseCommand(Promptable baseCommand) {
        this.baseCommand = baseCommand;
    }
}
